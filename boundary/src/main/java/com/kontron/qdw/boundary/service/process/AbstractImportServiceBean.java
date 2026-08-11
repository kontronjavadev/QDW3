package com.kontron.qdw.boundary.service.process;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

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

    // Definition of simple filter to get only *.xml files
    private static final FilenameFilter SIMPLE_XML_FILTER = FileType.XML.getFilenameFilterAllWithExtension();

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
        String[] importFileNames = new File(getImportDir()).list(SIMPLE_XML_FILTER);
        if (importFileNames.length == 0) {
            ownTask.finishTask();
            return;
        }


        Unmarshaller unmarshaller;
        try {
            URL fileURL = getClass().getResource(SCHEMA_PATH + getSchemaName());
            unmarshaller = JAXBContext.newInstance(getXmlRootClazz()).createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(fileURL);
            unmarshaller.setSchema(schema);
        }
        catch (Exception e) {
            TaskLeafLog tskUnmarshall = ownTask.createNewSubTaskLeaf("initializing unmarshaller");
            tskUnmarshall.finishTaskWithError(e);
            ownTask.abortTask();
            return;
        }


        List<String> orderedImportFileNames = com.kontron.util.file.FileUtil.getOrderedSAPImportFileNames(importFileNames, getImportType());

        // Read all xml files from given path
        logger.info("{} files found for " + getEntityName() + " import.", orderedImportFileNames);
        for (String importFileName : orderedImportFileNames) {
            importFile(getEntityName(), getFolderSubPath(), importFileName, ownTask, getImportDir(),
                    unmarshaller, getGetElementsFunction(), isWithBulk());
        }


        ownTask.finishTask();
        return;
    }



    protected void importFile(String entityName, String folderSubPath, String importFileName, TaskNodeLog tsk, String importDir,
            Unmarshaller unmarshaller,
            Function<ROOT, List<ELEM>> getElementsFunction, boolean withBulk) {
        logger.info("Lese " + entityName + "-Import Datei '{}'", importFileName);

        List<ELEM> importedElements;
        // parse xml file into list of entities
        try (InputStream is = new FileInputStream(new File(importDir, importFileName));
                InputStreamReader isr = new InputStreamReader(is, ENCODING)) {
            InputSource isrc = new InputSource(isr);
            isrc.setEncoding(ENCODING);
            @SuppressWarnings("unchecked")
            ROOT xmlRoot = (ROOT) unmarshaller.unmarshal(isrc);
            importedElements = getElementsFunction.apply(xmlRoot);
        }
        catch (Exception e) {
            // add error to response and continue with next file
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
            return;
        }


        List<String> errorList = new ArrayList<>();
        int bulkSize = BulkProcess.DEFAULT_BULK_SIZE;
        if (!withBulk) {
            // alles auf einmal, also bulkSize auf Gesamtgröße setzen
            bulkSize = importedElements.size();
        }
        BulkProcess bulkProcess = new BulkProcess(importedElements.size(), bulkSize);


        while (bulkProcess.getBulkToIdx() - bulkProcess.getBulkFromIdx() > 0) {
            try {
                importBulk(importFileName, tsk, importedElements, errorList, bulkProcess);
            }
            catch (Exception e) {
                logger.error("failed", e);
                tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
                tsk.abortTask();
                return;
            }

            bulkProcess.nextBulk();
            em.flush();
            em.clear();
        } // end bulk
        logger.info("100% done");

        if (errorList.isEmpty()) {
            tsk.addSubTask(new FileImportSuccessfulLog(importFileName, importedElements.size()));
        }
        else {
            tsk.addSubTask(new FileImportProcessedWithErrors(importFileName, errorList, importedElements.size()));
        }

        try {
            XMLDataImportUtils.moveFileToArchive(folderSubPath, importFileName);
        }
        catch (Exception e) {
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, "Failed moving file to import archive", importFileName, e));
            tsk.abortTask();
            return;
        }
    }


    protected abstract void importBulk(String importFileName, TaskNodeLog tsk, List<ELEM> importedElements, List<String> errorList,
            BulkProcess bulkProcess) throws Exception;



    private String getImportDir() {
        return exchangePath + getFolderSubPath();
    }

    protected boolean isWithBulk() {
        return true;
    }

    protected abstract String getEntityName();

    protected abstract String getFolderSubPath();

    protected abstract String getSchemaName();

    protected abstract ImportType getImportType();

    protected abstract Class<ROOT> getXmlRootClazz();

    protected abstract Function<ROOT, List<ELEM>> getGetElementsFunction();


}
