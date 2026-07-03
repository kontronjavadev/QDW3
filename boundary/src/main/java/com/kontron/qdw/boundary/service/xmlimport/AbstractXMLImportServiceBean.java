package com.kontron.qdw.boundary.service.xmlimport;

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
import com.kontron.qdw.boundary.service.XMLDataImportUtils;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportProcessedWithErrors;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.ejb.Stateless;
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
@Stateless
public abstract class AbstractXMLImportServiceBean<ROOT, ELEM> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String SCHEMA_PATH = "/schema/";

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";

    private static final String ENCODING = Constants.UTF_8;

    // Definition of simple filter to get only *.xml files
    private static final FilenameFilter SIMPLE_XML_FILTER = FileType.XML.getFilenameFilterAllWithExtension();

    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);

    @PersistenceContext
    private EntityManager em;



    /** Perform import */
    protected ITaskNodeLog runImport(String entityName, String folderSubPath, String schemaName, ImportType importType,
            Class<ROOT> xmlRootClazz, Function<ROOT, List<ELEM>> getElementsFunction) {
        return runImport(entityName, folderSubPath, schemaName, importType, xmlRootClazz, getElementsFunction, true);
    }

    /** Perform import */
    protected ITaskNodeLog runImportNoBulk(String entityName, String folderSubPath, String schemaName, ImportType importType,
            Class<ROOT> xmlRootClazz, Function<ROOT, List<ELEM>> getElementsFunction) {
        return runImport(entityName, folderSubPath, schemaName, importType, xmlRootClazz, getElementsFunction, false);
    }

    /** Perform import */
    private ITaskNodeLog runImport(String entityName, String folderSubPath, String schemaName, ImportType importType,
            Class<ROOT> xmlRootClazz, Function<ROOT, List<ELEM>> getElementsFunction, boolean withBulk) {
        String importDir = exchangePath + folderSubPath;

        TaskNodeLog tsk = new TaskNodeLog("import " + entityName, "import " + entityName /* + " in folder " + importDir*/);

        String[] importFileNames = new File(importDir).list(SIMPLE_XML_FILTER);
        if (importFileNames.length == 0) {
            tsk.finishTask();
            return tsk;
        }


        Unmarshaller unmarshaller;
        try {
            URL fileURL = getClass().getResource(SCHEMA_PATH + schemaName);
            unmarshaller = JAXBContext.newInstance(xmlRootClazz).createUnmarshaller();
            SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = sf.newSchema(fileURL);
            unmarshaller.setSchema(schema);
        }
        catch (Exception e) {
            TaskLeafLog tskUnmarshall = tsk.createNewSubTaskLeaf("initializing unmarshaller");
            tskUnmarshall.finishTaskWithError(e);
            tsk.abortTask();
            return tsk;
        }


        List<String> orderedImportFileNames = com.kontron.util.file.FileUtil.getOrderedSAPImportFileNames(importFileNames, importType);

        // Read all xml files from given path
        logger.info("{} files found for " + entityName + " import.", orderedImportFileNames);
        for (String importFileName : orderedImportFileNames) {
            // importFile(importFileName, tsk, importDir, unmarshaller);
            importFile(entityName, folderSubPath, importFileName, tsk, importDir, unmarshaller, getElementsFunction, withBulk);
        }


        tsk.finishTask();
        return tsk;
    }



    private void importFile(String entityName, String folderSubPath, String importFileName, TaskNodeLog tsk, String importDir,
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
        BulkProcess bulkProcess = new BulkProcess(importedElements.size());
        if (!withBulk) {
            // alles auf einmal, also bulkSize auf Gesamtgröße setzen
            bulkProcess.bulkSize = importedElements.size();
        }


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


    abstract void importBulk(String importFileName, TaskNodeLog tsk, List<ELEM> importedElements, List<String> errorList,
            BulkProcess bulkProcess) throws Exception;



    class BulkProcess {
        private final int listSize;

        private float cnt = 0;
        private int progressStep = 5;
        private int progress = progressStep;

        private int bulkSize = 2000;
        private int bulkFromIdx = 0;
        private int bulkToIdx;


        BulkProcess(int listSize) {
            this.listSize = listSize;
            bulkToIdx = Math.min(listSize, bulkSize);
        }


        void nextCnt() {
            cnt++;
        }

        void nextBulk() {
            bulkFromIdx = bulkToIdx;
            bulkToIdx = Math.min(listSize, bulkFromIdx + bulkSize);
        }

        void logProcess(Logger logger) {
            if (cnt / listSize * 100 > progress) {
                progress = ((int) (cnt / listSize * 100) / progressStep) * progressStep;
                logger.info(progress + "% done");
                progress += progressStep;
            }
        }

        void logProcessBulkLevel(Logger logger) {
            if ((float) bulkFromIdx / listSize * 100 > progress) {
                progress = ((int) ((float) bulkFromIdx / listSize * 100) / progressStep) * progressStep;
                logger.info(progress + "% done");
                progress += progressStep;
            }
        }


        public int getBulkFromIdx() {
            return bulkFromIdx;
        }

        public int getBulkToIdx() {
            return bulkToIdx;
        }

        public int getListSize() {
            return listSize;
        }

        public int getProgress() {
            return progress;
        }

        public void setProgress(int progress) {
            this.progress = progress;
        }

        public int getProgressStep() {
            return progressStep;
        }

    }

}
