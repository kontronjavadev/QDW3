package com.kontron.qdw.boundary.service.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.lang3.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.kontron.constants.file.FileType;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportProcessedWithErrors;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import net.sourceforge.jbizmo.commons.property.PropertyService;

/**
 * Abstrakte Basis-Klasse für Import von XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
public abstract class AbstractImportServiceBean<ROOT, ELEM> implements TaskCall {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String SCHEMA_PATH = "/schema/";

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";

    private static final String ENCODING = Constants.UTF_8;

    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);

    @PersistenceContext
    private EntityManager em;



    /** Init Task */
    @Override
    @PermitAll
    public TaskNodeLog initTask() {
        return new TaskNodeLog("import " + getEntityName());
    }

    /** Perform import */
    @Override
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void execTask(TaskNodeLog ownTask) {
        List<String> orderedImportFileNames = getOrderedImportFileNames();
        if (orderedImportFileNames.isEmpty()) {
            ownTask.finishTask();
            return;
        }


        SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
        URL fileURL = getClass().getResource(SCHEMA_PATH + getSchemaName());
        Unmarshaller unmarshaller;
        try {
            unmarshaller = JAXBContext.newInstance(getXmlRootClazz()).createUnmarshaller();
            Schema schema = sf.newSchema(fileURL);
            unmarshaller.setSchema(schema);
        }
        catch (Exception e) {
            TaskLeafLog tskUnmarshall = ownTask.createNewSubTaskLeaf("initializing unmarshaller");
            tskUnmarshall.finishTaskWithError(e);
            ownTask.abortTask();
            return;
        }


        // Read all xml files from given path
        logger.info("{} files found for " + getEntityName() + " import.", orderedImportFileNames);
        for (String importFileName : orderedImportFileNames) {
            importFile(importFileName, ownTask, unmarshaller);
        }


        ownTask.finishTask();
        return;
    }



    protected void importFile(String importFileName, TaskNodeLog tsk, Unmarshaller unmarshaller) {
        logger.info("Lese " + getEntityName() + "-Import Datei '{}'", importFileName);

        List<ELEM> importedElements;
        try {
            importedElements = unmarshal(unmarshaller, importFileName, tsk);
        }
        catch (Exception e) {
            // add error to response and continue with next file
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
            return;
        }


        List<String> errorList = new ArrayList<>();
        int bulkSize = bulkSize();
        if (!execInBulks()) {
            // alles auf einmal, also bulkSize auf Gesamtgröße setzen
            bulkSize = importedElements.size();
        }
        BulkProcess bulkProcess = new BulkProcess(importedElements.size(), bulkSize);


        while (bulkProcess.getBulkToIdx() - bulkProcess.getBulkFromIdx() > 0) {
            try {
                importBulk(importFileName, tsk, importedElements, errorList, bulkProcess);
                em.flush();
            }
            catch (Exception e) {
                logger.error("failed", e);
                tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
                tsk.abortTask();
                return;
            }
            finally {
                em.clear();
            }

            bulkProcess.nextBulk();
        } // end bulk
        logger.info("100% done");

        if (errorList.isEmpty()) {
            tsk.addSubTask(new FileImportSuccessfulLog(importFileName, importedElements.size()));
        }
        else {
            tsk.addSubTask(new FileImportProcessedWithErrors(importFileName, errorList, importedElements.size()));
        }

        try {
            XMLDataImportUtils.moveFileToArchive(getFolderSubPath(), importFileName);
        }
        catch (Exception e) {
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, "Failed moving file to import archive", importFileName, e));
            tsk.abortTask();
            return;
        }
    }

    protected List<ELEM> unmarshal(Unmarshaller unmarshaller, String importFileName, TaskNodeLog tsk) throws Exception {
        // parse xml file into list of entities
        try (FileInputStream is = new FileInputStream(new File(getImportDir(), importFileName));
                InputStreamReader isr = new InputStreamReader(is, ENCODING)) {
            InputSource isrc = new InputSource(isr);
            isrc.setEncoding(ENCODING);
            @SuppressWarnings("unchecked")
            ROOT xmlRoot = (ROOT) unmarshaller.unmarshal(isrc);
            return getGetElementsFunction().apply(xmlRoot);
        }
    }


    protected abstract void importBulk(String importFileName, TaskNodeLog tsk, List<ELEM> importedElements, List<String> errorList,
            BulkProcess bulkProcess) throws Exception;



    private String getImportDir() {
        return exchangePath + getFolderSubPath();
    }

    protected List<String> getOrderedImportFileNames() {
        List<String> importFileNames = Arrays.asList(new File(getImportDir()).list(getImportFilenameFilter()));
        if (importFileNames.isEmpty()) {
            return importFileNames;
        }
        return com.kontron.util.file.FileUtil.getOrderedSAPImportFileNames(importFileNames, getImportType());
    }

    protected FilenameFilter getImportFilenameFilter() {
        return (File dir, String name) -> Strings.CI.endsWith(name, FileType.XML.getFilenameExtension())
                && Strings.CI.startsWith(name, getImportType().getPrefix());
    }

    protected int bulkSize() {
        return BulkProcess.DEFAULT_BULK_SIZE;
    }

    protected boolean execInBulks() {
        return true;
    }

    protected abstract String getEntityName();

    protected abstract String getFolderSubPath();

    protected abstract String getSchemaName();

    protected abstract ImportType getImportType();

    protected abstract Class<ROOT> getXmlRootClazz();

    protected abstract Function<ROOT, List<ELEM>> getGetElementsFunction();


}
