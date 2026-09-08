package com.kontron.qdw.boundary.service.tracebomimport;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.kontron.common.mail.MailMessage;
import com.kontron.qdw.boundary.service.mapping.tracebom.TraceBoMHeaderTypeIF;
import com.kontron.qdw.boundary.service.mapping.tracebom.TraceBoMItemTypeIF;
import com.kontron.qdw.boundary.service.mapping.tracebom.TraceBoMTypeIF;
import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.boundary.util.MailServiceFacade;
import com.kontron.qdw.domain.base.Supplier;
import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialRevision;
import com.kontron.qdw.domain.serial.IllegalTraceBoMItem;
import com.kontron.qdw.domain.serial.SerialObject;
import com.kontron.qdw.domain.serial.TraceBoM;
import com.kontron.qdw.domain.serial.TraceBoMItem;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.serial.IllegalTraceBoMItemRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository;
import com.kontron.qdw.repository.serial.TraceBoMItemRepository;
import com.kontron.qdw.repository.serial.TraceBoMRepository;
import com.kontron.util.text.StringUtil;

import jakarta.ejb.EJB;

/**
 * Abstrakte Basis-Klasse für Import der Trace-BoM-Dateien, die die Fertiger in verschiedenen Verzeichnissen auf dem sftp bereitstellen.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
public abstract class AbstractTBImportServiceBean<TB extends TraceBoMTypeIF<TBI>, TBH extends TraceBoMHeaderTypeIF, TBI extends TraceBoMItemTypeIF> {
    private static final String DEFAULT_PLANT_CODE = "6000";
    private static final String REVISION_NO_SUFFIX = " ALT(01)";
    private static final double TRACE_BOM_WARNING_THRESHOLD = 10.0;

    private static final DateTimeFormatter FLEXIBLE_FORMATTER = new DateTimeFormatterBuilder()
            .appendPattern("[dd.MM.yyyy HH:mm[:ss]]")
            .appendPattern("[yyyy/MM/dd HH:mm[:ss]]")
            .appendPattern("[yyyy-MM-dd HH:mm[:ss]]")
            .appendPattern("[dd-MM-yyyy HH:mm[:ss]]")
            .toFormatter();


    @EJB
    private MaterialRevisionRepository materialRevisionManager;
    @EJB
    private MaterialRepository materialManager;
    @EJB
    private PlantRepository plantManager;
    @EJB
    private SerialObjectRepository serObjManager;
    @EJB
    private TraceBoMRepository trBoMManager;
    @EJB
    private TraceBoMItemRepository trBoMItemManager;
    @EJB
    private IllegalTraceBoMItemRepository illTrBoMItemManager;



    /**
     * Find material revision and tries to create it if not be found.
     *
     * @throws Exception if revision need to be created but material cannot be found
     */
    MaterialRevision findMaterialRevision(String materialNumber, String revisionNumber) throws Exception {
        // TODO: traceBoM muss mit plant der Revision geliefert werden
        // Hinweis: im alten Code wurde eine Liste bis zwei Einträgen gesucht, um feststellen zu können, ob die Revisionsnummer eindeutig ist.
        // Dazu gibt es einen Datenbankconstraint. Es wird nun jedoch auch nach Revisionen mit Zeitstempel gesucht, um die letzte Revision zu erhalten.
        MaterialRevision materialRevision = materialRevisionManager.getLastMaterialRevisionByMatNr(
                materialNumber, DEFAULT_PLANT_CODE, revisionNumber);


        if (materialRevision == null) {
            materialRevision = new MaterialRevision();
            materialRevision.setRevisionNumber(revisionNumber);
            materialRevision.setMaterial(materialManager.findByMaterialNumber(materialNumber));
            materialRevision.setPlant(plantManager.getReference(DEFAULT_PLANT_CODE));

            if (materialRevision.getMaterial() != null) {
                materialRevision = materialRevisionManager.persist(materialRevision, true, true);
            }
            else {
                throw new Exception("Material '" + materialNumber + "' does not exist, therefore revision '"
                        + revisionNumber + "' could not be created.");
            }
        }

        return materialRevision;
    }

    /**
     * Find serial object and tries to create it if not be found.
     *
     * @throws Exception if search was not unique
     */
    SerialObject findSerialObject(String serialNumber, String custSerialNumber, Material material,
            String prodOrderNr, LocalDate parsedProdDate) throws Exception {
        String serNo = StringUtil.removeLeadingZeroIfNumber(serialNumber);

        SerialObject serialObject;
        try {
            serialObject = serObjManager.findBySerialNumberAndMaterialNr(serNo, material.getMaterialNumber());
        }
        catch (IllegalStateException ise) {
            throw new Exception("SerialObject for serial number " + serNo
                    + " and material number " + material.getMaterialNumber() + " is not unique.");
        }

        if (serialObject == null) {
            serialObject = new SerialObject();

            serialObject.setSerialNumber(serNo);
            serialObject.setMaterial(material);
            serialObject.setCustomerSerialNumber(StringUtil.removeLeadingZeroIfNumber(custSerialNumber));
            serialObject.setProductionOrderNumber(prodOrderNr);

            serialObject.setAssemblyDate(parsedProdDate);

            serialObject = serObjManager.persist(serialObject, true, true);
        }

        return serialObject;
    }



    /** Create a new trace BoM */
    TraceBoM createTraceBoM(TB trBoMImported, TBH trBoMHeaderImported, Supplier supplier,
            LocalDate parsedProdDate, MaterialRevision materialRevision, List<String> illegalRatioMsgs) {
        TraceBoM traceBoM = new TraceBoM();
        traceBoM.setDeliveryNoteNumber(trBoMHeaderImported.getDeliveryNoteNumber());
        traceBoM.setLotNumber(trBoMHeaderImported.getLotNumber());
        traceBoM.setOrderNumber(trBoMHeaderImported.getOrderNumber());
        traceBoM.setSupplier(supplier);
        traceBoM.setProductionDate(parsedProdDate);
        traceBoM.setMaterialRevision(materialRevision);

        traceBoM = trBoMManager.persist(traceBoM, true, true);

        // Add all trace BoM items to trace BoM
        importTraceBoMItems(traceBoM, trBoMImported, trBoMHeaderImported, illegalRatioMsgs);
        return traceBoM;
    }

    /** Import trace BoM items */
    private void importTraceBoMItems(TraceBoM trBoMPersisted, TB trBoMImported, TBH trBoMHeaderImported,
            List<String> illegalRatioMsgs) {
        int totalSize = trBoMImported.getTraceBoMItems().size();
        int illegalItemCount = 0;
        for (TBI trBoMItemImported : trBoMImported.getTraceBoMItems()) {
            String matSapNrImported = trBoMItemImported.getMaterialSapNumber().replace("-", "");
            Material material = materialManager.findBySapNumber(matSapNrImported);

            if (material == null) {
                IllegalTraceBoMItem illegalItem = new IllegalTraceBoMItem();
                illegalItem.setTraceBom(trBoMPersisted);
                illegalItem.setMaterialNumber(matSapNrImported);
                illegalItem.setManufacturer(trBoMItemImported.getManufacturerName());
                illegalItem.setManufacturerRevision("");
                illegalItem.setOrderCode(trBoMItemImported.getOrderCode());
                illegalItem.setDateCode(trBoMItemImported.getDateCode());

                illTrBoMItemManager.persist(illegalItem, false, false);
                illegalItemCount++;
            }
            else {
                TraceBoMItem traceBoMItem = new TraceBoMItem();
                traceBoMItem.setTraceBom(trBoMPersisted);
                traceBoMItem.setMaterial(material);
                traceBoMItem.setQuantity(trBoMItemImported.getQuantity());
                traceBoMItem.setManufacturerName(trBoMItemImported.getManufacturerName());
                traceBoMItem.setManufacturerRevision("");
                traceBoMItem.setOrderCode(trBoMItemImported.getOrderCode());
                traceBoMItem.setDateCode(trBoMItemImported.getDateCode());
                traceBoMItem.setInfoField1(trBoMItemImported.getInfoField1());
                traceBoMItem.setInfoField2(trBoMItemImported.getInfoField2());
                traceBoMItem.setInfoField3("");
                traceBoMItem.setInfoField4("");

                trBoMItemManager.persist(traceBoMItem, false, false);
            }

            /**********************************************************
             * CURRENTLY MULTI-LEVEL for new TraceBoM IS NOT SUPPORTED
             * - SHOULD BE IMPLEMENTED HERE
             **********************************************************/
        } // end for rootMappingObject.getTraceBoMItems()

        if (totalSize > 0) {
            double illegalRatio = 100f * illegalItemCount / (double) totalSize;

            // Send mail to responsible persons that number of illegal items exceeds threshold!
            if (illegalRatio >= TRACE_BOM_WARNING_THRESHOLD) {
                illegalRatioMsgs.add(createIllegalRatioMsg(trBoMHeaderImported, illegalRatio));
            }
        }
    }

    private String createIllegalRatioMsg(TBH trBoMHeaderImported, double illegalRatio) {
        String illegalRatioString = new DecimalFormat("0.00").format(illegalRatio);

        StringBuilder msg = new StringBuilder();
        msg.append("Illegal material ratio: ").append(illegalRatioString).append("%\n");
        msg.append("Delivery note no.: ").append(trBoMHeaderImported.getDeliveryNoteNumber()).append("\n");
        msg.append("Lot no.: ").append(trBoMHeaderImported.getLotNumber()).append("\n");
        msg.append("Order no.: ").append(trBoMHeaderImported.getOrderNumber()).append("\n");
        msg.append("Supplier: ").append(trBoMHeaderImported.getSupplierCode()).append("\n");

        return msg.toString();
    }

    void sendIllegalRatioMail(TBH trBoMHeaderImported, List<String> illegalRatioMsgs) {
        String subject = Constants.APP_ENV + "Illegal material ratio warning for delivery note no. "
                + trBoMHeaderImported.getDeliveryNoteNumber();
        List<String> receivers = Arrays.stream(Constants.getMailRecipientIllegalRatioWarning().split(";"))
                .map(String::trim)
                .collect(Collectors.toList());

        try {
            MailMessage msg = new MailMessage();
            msg.setTo(receivers);
            msg.setSubject(subject);
            msg.setMessage(String.join("\n", illegalRatioMsgs));

            MailServiceFacade.sendMail(msg);
        }
        catch (Exception mailException) {
            mailException.printStackTrace();
        }
    }



    LocalDate parseToLocalDate(String dateText) throws Exception {
        if (StringUtils.isBlank(dateText)) {
            throw new Exception("Production date is not set.");
        }
        try {
            return LocalDateTime.parse(dateText, FLEXIBLE_FORMATTER).toLocalDate();
        }
        catch (DateTimeParseException dtpe) {
            throw new Exception("Production date '" + dateText + "' has no accepted format.");
        }
    }

    String correctRevNr(String revNoImported) {
        if (revNoImported.contains(REVISION_NO_SUFFIX)) {
            return revNoImported;
        }
        return revNoImported + REVISION_NO_SUFFIX;
    }

}
