package com.kontron.qdw.boundary.service.xmlimport;

import java.util.List;

import com.kontron.qdw.boundary.service.mapping.supplier.SupplierXMLElement;
import com.kontron.qdw.boundary.service.mapping.supplier.SupplierXMLRoot;
import com.kontron.qdw.domain.base.Supplier;
import com.kontron.qdw.repository.base.CountryRepository;
import com.kontron.qdw.repository.base.SupplierRepository;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

/**
 * Import der Supplier-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class XMLSupplierImportServiceBean extends AbstractXMLImportServiceBean<SupplierXMLRoot, SupplierXMLElement> {

    private static final String ENTITY_NAME = "supplier";
    private static final String FOLDER_SUB_PATH = "supplier";
    private static final String SCHEMA_NAME = "Supplier.xsd";

    @EJB
    private SupplierRepository supplierManager;
    @EJB
    private CountryRepository countryManager;



    /** Perform import */
    @PermitAll
    public ITaskNodeLog runImport() {
        return super.runImportNoBulk(ENTITY_NAME, FOLDER_SUB_PATH, SCHEMA_NAME, ImportType.QDW_SUPPLIERS, SupplierXMLRoot::getSupplierList);
    }


    @Override
    protected void importBulk(String importFileName, TaskNodeLog tsk, List<SupplierXMLElement> importedSuppliers, List<String> errorList,
            BulkProcess bulkProcess) throws Exception {
        // keine Bulk-Verarbeitung,es wird alles komplett übernommen!
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
    }

}
