package com.kontron.qdw.boundary.service.xmlimport;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.InputSource;

import com.kontron.qdw.boundary.material.MaterialRevisionBoundaryService;
import com.kontron.qdw.boundary.service.XMLDataImportUtils;
import com.kontron.qdw.boundary.service.mapping.shipment.ShipmentMappingType;
import com.kontron.qdw.boundary.service.mapping.shipment.ShipmentRootMappingType;
import com.kontron.qdw.boundary.util.Constants;
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
import com.kontron.util.log.FileImportProcessedWithErrors;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.ITaskNodeLog;
import com.kontron.util.log.TaskNodeLog;
import com.kontron.util.text.StringUtil;

import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.xml.bind.Unmarshaller;

/**
 * Import der Shipment-XML-Dateien, die der Downloader bereitstellt.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class XMLShipmentImportServiceBean extends AbstractXMLImportServiceBean {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String ENTITY_NAME = "shipment";
    private static final String FOLDER_SUB_PATH = "shipment";
    private static final String SCHEMA_NAME = "Shipment.xsd";


    private static final String ENCODING = Constants.UTF_8;

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
        return super.runImport(ENTITY_NAME, FOLDER_SUB_PATH, SCHEMA_NAME, ImportType.QDW_SHIPMENT);
    }



    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void importFile(String importFileName, TaskNodeLog tsk, String importDir, Unmarshaller unmarshaller) {
        logger.info("Lese BoM-Import Datei '{}'", importFileName);

        List<ShipmentMappingType> importedShipments;
        // parse xml file into list of entities
        try (InputStream is = new FileInputStream(new File(importDir, importFileName));
                InputStreamReader isr = new InputStreamReader(is, ENCODING)) {
            InputSource isrc = new InputSource(isr);
            isrc.setEncoding(ENCODING);
            ShipmentRootMappingType xmlRoot = (ShipmentRootMappingType) unmarshaller.unmarshal(isrc);
            importedShipments = xmlRoot.getShipments();
        }
        catch (Exception e) {
            // add error to response and continue with next file
            tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, e));
            return;
        }


        List<String> errorList = new ArrayList<>();
        float cnt = 0;
        int progressStep = 5;
        int progress = progressStep;

        int listSize = importedShipments.size();
        int bulkSize = 2000;
        int bulkFromIdx = 0;
        int bulkToIdx = Math.min(listSize, bulkSize);


        while (bulkToIdx - bulkFromIdx > 0) {
            // aktuell verarbeiteter Batch
            List<ShipmentMappingType> curBatch = importedShipments.subList(bulkFromIdx, bulkToIdx);
            batchNormalisieren(curBatch);
            curBatch = batchFiltern(curBatch);


            for (ShipmentMappingType shipment : curBatch) {
                if (cnt / listSize * 100 > progress) {
                    progress = ((int) (cnt / listSize * 100) / progressStep) * progressStep;
                    logger.info(progress + "% done");
                    progress += progressStep;
                }

                try {
                    importEntry(shipment, importFileName, errorList);
                }
                catch (Exception e) {
                    logger.error("failed", e);
                    tsk.addSubTask(new FileImportAbortedWithErrorsLog(importFileName, shipment.getMaterialSapNumber(), e));
                    tsk.abortTask();
                    return;
                }

                cnt++;
            }

            bulkFromIdx = bulkToIdx;
            bulkToIdx = Math.min(listSize, bulkFromIdx + bulkSize);
            em.flush();
            em.clear();
        } // end bulk
        logger.info("100% done");

        if (errorList.isEmpty()) {
            tsk.addSubTask(new FileImportSuccessfulLog(importFileName, importedShipments.size()));
        }
        else {
            tsk.addSubTask(new FileImportProcessedWithErrors(importFileName, errorList, importedShipments.size()));
        }

        XMLDataImportUtils.moveFileToArchive(FOLDER_SUB_PATH, importFileName);
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
