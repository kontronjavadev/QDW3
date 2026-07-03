package com.kontron.qdw.boundary.service.xmlimport;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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
        return super.runImport(ENTITY_NAME, FOLDER_SUB_PATH, SCHEMA_NAME, ImportType.QDW_SUPPLIERS,
                SupplierXMLRoot.class, SupplierXMLRoot::getSupplierList);
    }


    @Override
    protected void importBulk(String importFileName, TaskNodeLog tsk, List<SupplierXMLElement> importedSuppliers, List<String> errorList,
            BulkProcess bulkProcess) throws Exception {
        bulkProcess.logProcessBulkLevel(logger);

        // aktuell verarbeiteter Batch
        List<SupplierXMLElement> curBatch = importedSuppliers.subList(bulkProcess.getBulkFromIdx(), bulkProcess.getBulkToIdx());
        batchNormalisieren(curBatch);

        Map<String, Supplier> existingSupplierMap = supplierManager.findByIds(curBatch.stream()
                .map(SupplierXMLElement::getCode)
                .collect(Collectors.toSet()));

        for (SupplierXMLElement supplier : curBatch) {
            // Bearbeitung istunspektakulär und muss nicht in eine eigene Methode ausgelagert werden.
            // So sparen wir den Overhead, mit jedem Aufruf die Suplier-Liste auf den Stack zu legen.
            String currentSupplierCode = supplier.getCode();
            Supplier existingSupplier = existingSupplierMap.get(currentSupplierCode);
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

    private void batchNormalisieren(List<SupplierXMLElement> curBatch) {
        curBatch.forEach(importedSupplier -> importedSupplier.setCode(StringUtil.removeLeadingZero(importedSupplier.getCode())));
    }

}
