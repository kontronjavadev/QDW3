package com.kontron.qdw.boundary.service.xmlimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

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
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.xml.bind.Unmarshaller;

/**
 * Import der Supplier-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class XMLSupplierImportServiceBean extends AbstractXMLImportServiceBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ENTITY_NAME = "supplier";
    private static final String FOLDER_SUB_PATH = "supplier";
    private static final String SCHEMA_NAME = "Supplier.xsd";

    private static final String ENCODING = Constants.UTF_8;

    @EJB
    private SupplierRepository supplierManager;
    @EJB
    private CountryRepository countryManager;



    /** Perform import */
    @PermitAll
    public ITaskNodeLog runImport() {
        return super.runImport(ENTITY_NAME, FOLDER_SUB_PATH, SCHEMA_NAME, ImportType.QDW_SUPPLIERS);
    }



    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void importFile(String importFileName, TaskNodeLog tsk, String importDir, Unmarshaller unmarshaller) {
        logger.info("Lese " + ENTITY_NAME + "-Import Datei '{}'", importFileName);

        List<SupplierXMLElement> importedSuppliers;
        // parse xml file into list of entities
        try (FileInputStream fis = new FileInputStream(new File(importDir, importFileName));
                InputStreamReader isr = new InputStreamReader(fis, ENCODING)) {
            InputSource isrc = new InputSource(isr);
            isrc.setEncoding(ENCODING);
            SupplierXMLRoot xmlRoot = (SupplierXMLRoot) unmarshaller.unmarshal(isrc);
            importedSuppliers = xmlRoot.getSupplierList();
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
            XMLDataImportUtils.moveFileToArchive(FOLDER_SUB_PATH, importFileName);
        }
        catch (Exception e) {
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
        }
    }

}
