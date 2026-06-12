package com.kontron.qdw.boundary.service.xmlimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.net.URL;
import java.util.List;

import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.kontron.constants.file.FileType;
import com.kontron.qdw.boundary.service.XMLDataImportUtils;
import com.kontron.qdw.boundary.service.mapping.supplier.SupplierXMLElement;
import com.kontron.qdw.boundary.service.mapping.supplier.SupplierXMLRoot;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.domain.base.Supplier;
import com.kontron.qdw.repository.base.CountryRepository;
import com.kontron.qdw.repository.base.SupplierRepository;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;
import net.sourceforge.jbizmo.commons.property.PropertyService;

/**
 * Import der Supplier-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class XMLSupplierImportServiceBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String SUPPLIER_SCHEMA_PATH = "/schema/Supplier.xsd";

    private static final String SUPPLIER_SUB_PATH = "supplier";

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";

    private static final String ENCODING = Constants.UTF_8;

    // Definition of simple filter to get only *.xml files
    private static final FilenameFilter SIMPLE_XML_FILTER = FileType.XML.getFilenameFilterAllWithExtension();

    @EJB
    private SupplierRepository supplierManager;
    @EJB
    private CountryRepository countryManager;

    private String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);



    /** Perform supplier import */
    @PermitAll
    public ITaskNodeLog runImport() {
        String supplierDir = exchangePath + SUPPLIER_SUB_PATH;

        TaskNodeLog tsk = new TaskNodeLog("import supplier", "import supplier in folder " + supplierDir);

        String[] importFileNames = new File(supplierDir).list(SIMPLE_XML_FILTER);
        if (importFileNames.length == 0) {
            tsk.finishTask();
            return tsk;
        }


        Unmarshaller unmarshaller;
        try {
            URL fileURL = getClass().getResource(SUPPLIER_SCHEMA_PATH);
            unmarshaller = JAXBContext.newInstance(SupplierXMLRoot.class).createUnmarshaller();

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


        List<String> orderedImportFileNames = com.kontron.util.file.FileUtil.getOrderedSAPImportFileNames(importFileNames, ImportType.GPE_SUPPLIERS);

        for (String importFileName : orderedImportFileNames) {
            importFile(importFileName, tsk, supplierDir, unmarshaller);
        }

        tsk.finishTask();
        return tsk;
    }



    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    private void importFile(String importFileName, TaskNodeLog tsk, String supplierDir, Unmarshaller unmarshaller) {
        logger.info(String.format("Lese Supplier-Import Datei '%s'", importFileName));
        List<SupplierXMLElement> importedSuppliers;
        // parse xml file into list of entities
        try (FileInputStream fis = new FileInputStream(new File(supplierDir, importFileName));
                InputStreamReader isr = new InputStreamReader(fis, ENCODING)) {
            InputSource isrc = new InputSource(isr);
            isrc.setEncoding(ENCODING);
            SupplierXMLRoot suppliers = (SupplierXMLRoot) unmarshaller.unmarshal(isrc);
            importedSuppliers = suppliers.getSupplierList();
        }
        catch (Exception e) {
            // add error to response and continue with next file
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
            return;
        }

        try {
            // parsed xml as list of entities
            for (SupplierXMLElement supplier : importedSuppliers) {
                // We must remove leading zeros!
                String currentSupplierCode = StringUtil.removeLeadingZero(supplier.getCode());

                Supplier existingSupplier = supplierManager.findById(currentSupplierCode);

                if (existingSupplier != null) {
                    existingSupplier.setName(supplier.getName() + " (" + currentSupplierCode + ")");
                    existingSupplier.setCity(supplier.getCity());
                    existingSupplier.setStreet(supplier.getStreet());
                    existingSupplier.setZipCode(supplier.getZip());
                }
                else {
                    Supplier newSupplier = new Supplier(currentSupplierCode);
                    newSupplier.setName(supplier.getName() + " (" + currentSupplierCode + ")");
                    newSupplier.setCity(supplier.getCity());
                    newSupplier.setStreet(supplier.getStreet());
                    newSupplier.setZipCode(supplier.getZip());
                    newSupplier.setCountry(countryManager.findById(supplier.getCountryCode()));

                    supplierManager.persist(newSupplier, true, false, false);
                }
            } // end for supplier

            // add success to response
            tsk.addSubTask(new FileImportSuccessfulLog(importFileName, importedSuppliers.size()));
            XMLDataImportUtils.moveFileToArchive(SUPPLIER_SUB_PATH, importFileName);
        }
        catch (Exception e) {
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
        }
    }

}
