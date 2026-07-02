package com.kontron.qdw.boundary.service.xmlimport;

import java.io.File;
import java.io.FilenameFilter;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.List;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.constants.file.FileType;
import com.kontron.qdw.boundary.service.mapping.bom.BoMXMLRoot;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
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
public abstract class AbstractXMLImportServiceBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String SCHEMA_PATH = "/schema/";

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";

    // Definition of simple filter to get only *.xml files
    private static final FilenameFilter SIMPLE_XML_FILTER = FileType.XML.getFilenameFilterAllWithExtension();

    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);



    /** Perform import */
    protected ITaskNodeLog runImport(String entityName, String folderSubPath, String schemaName, ImportType importType) {
        String importDir = exchangePath + folderSubPath;

        TaskNodeLog tsk = new TaskNodeLog("import " + entityName, "import " + entityName + " in folder " + importDir);

        String[] importFileNames = new File(importDir).list(SIMPLE_XML_FILTER);
        if (importFileNames.length == 0) {
            tsk.finishTask();
            return tsk;
        }


        Unmarshaller unmarshaller;
        try {
            URL fileURL = getClass().getResource(SCHEMA_PATH + schemaName);
            unmarshaller = JAXBContext.newInstance(BoMXMLRoot.class).createUnmarshaller();
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
            importFile(importFileName, tsk, importDir, unmarshaller);
        }


        tsk.finishTask();
        return tsk;
    }



    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    abstract void importFile(String importFileName, TaskNodeLog tsk, String importDir, Unmarshaller unmarshaller);

}
