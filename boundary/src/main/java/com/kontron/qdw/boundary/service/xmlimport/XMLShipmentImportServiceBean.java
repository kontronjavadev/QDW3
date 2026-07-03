package com.kontron.qdw.boundary.service.xmlimport;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.material.MaterialRevisionBoundaryService;
import com.kontron.qdw.boundary.service.mapping.shipment.ShipmentMappingType;
import com.kontron.qdw.boundary.service.mapping.shipment.ShipmentRootMappingType;
import com.kontron.qdw.repository.base.MovementTypeRepository;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.base.SupplierRepository;
import com.kontron.qdw.repository.material.BoMItemRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.serial.ArrivalRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository;
import com.kontron.util.file.FileUtil.ImportType;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

/**
 * Import der Shipment-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class XMLShipmentImportServiceBean extends AbstractXMLImportServiceBean<ShipmentRootMappingType, ShipmentMappingType> {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ENTITY_NAME = "shipment";
    private static final String FOLDER_SUB_PATH = "shipment";
    private static final String SCHEMA_NAME = "Shipment.xsd";

    private static final String CANCELED_SHIPMENT_MOVEMENT_TYPE_1 = "602";


    @EJB
    private ArrivalRepository arrivalManager;
    @EJB
    private SupplierRepository supplierManager;
    @EJB
    private MovementTypeRepository movementTypeManager;
    @EJB
    private SerialObjectRepository serialObjectManager;

    @EJB
    private BoMItemRepository bomManager;
    @EJB
    private MaterialRevisionRepository materialRevisionManager;
    @EJB
    private MaterialRevisionBoundaryService materialRevisionService;
    @EJB
    private MaterialRepository materialManager;
    @EJB
    private PlantRepository plantManager;

    @PersistenceContext
    private EntityManager em;



    /** Perform import */
    @PermitAll
    public ITaskNodeLog runImport() {
        return super.runImport(ENTITY_NAME, FOLDER_SUB_PATH, SCHEMA_NAME, ImportType.QDW_SHIPMENT,
                ShipmentRootMappingType.class, ShipmentRootMappingType::getShipments);
    }



    @Override
    protected void importBulk(String importFileName, TaskNodeLog tsk, List<ShipmentMappingType> importedShipments, List<String> errorList,
            BulkProcess bulkProcess) throws Exception {
        // aktuell verarbeiteter Batch
        List<ShipmentMappingType> curBatch = importedShipments.subList(bulkProcess.getBulkFromIdx(), bulkProcess.getBulkToIdx());
        batchNormalisieren(curBatch);
        curBatch = batchFiltern(curBatch);


        for (ShipmentMappingType shipment : curBatch) {
            bulkProcess.logProcess(logger);

            try {
                importEntry(shipment, importFileName, errorList);
            }
            catch (Exception e) {
                logger.error("failed", e);
                tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, shipment.getMaterialSapNumber(), e));
                tsk.abortTask();
                throw e;
            }

            bulkProcess.nextCnt();
        }
    }



    private void importEntry(ShipmentMappingType importedShipment, String importFileName, List<String> errorList) {

    }



    private void batchNormalisieren(List<ShipmentMappingType> curBatch) {
        curBatch.forEach(importedShipment -> {
            importedShipment.setMaterialSapNumber(StringUtil.removeLeadingZero(importedShipment.getMaterialSapNumber()));
            importedShipment.setOrderNumber(StringUtil.removeLeadingZero(importedShipment.getOrderNumber()));
            importedShipment.setSerialNumber(StringUtil.removeLeadingZeroIfNumber(importedShipment.getSerialNumber()));
            importedShipment.setCustomerCode(StringUtil.removeLeadingZero(importedShipment.getCustomerCode()));
            importedShipment.setSerialObjectSapNumber(importedShipment.getMaterialSapNumber());
            importedShipment.setId(RegExUtils.replaceAll(importedShipment.getId(), "[A-Z]", ""));
        });
    }

    private List<ShipmentMappingType> batchFiltern(List<ShipmentMappingType> curBatch) {
        curBatch = curBatch.stream()
                .filter(importedShipment -> StringUtils.isNotEmpty(importedShipment.getSerialNumber()))
                .filter(importedShipment -> StringUtils.isNotEmpty(importedShipment.getId()))
                // CANCELED_SHIPMENT_MOVEMENT_TYPE_1 must be taken over without order number because
                // SAP is not able to transfer this number in any circumstance
                .filter(importedShipment -> StringUtils.isNotEmpty(importedShipment.getOrderNumber())
                        || Objects.equals(importedShipment.getMovementTypeCode(), CANCELED_SHIPMENT_MOVEMENT_TYPE_1))
                .collect(Collectors.toList());
        return curBatch;
    }

}
