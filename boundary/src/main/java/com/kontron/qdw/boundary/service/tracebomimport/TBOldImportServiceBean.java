package com.kontron.qdw.boundary.service.tracebomimport;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.StringReader;
import java.lang.invoke.MethodHandles;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.common.mail.MailMessage;
import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMHeaderType;
import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMItemMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMRevisionMappingType;
import com.kontron.qdw.boundary.service.mapping.tracebomalt.TraceBoMRootMappingType;
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
import com.kontron.qdw.repository.base.SupplierRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.serial.IllegalTraceBoMItemRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository;
import com.kontron.qdw.repository.serial.TraceBoMItemRepository;
import com.kontron.qdw.repository.serial.TraceBoMRepository;
import com.kontron.util.log.FileImportAbortedWithErrorsLog;
import com.kontron.util.log.FileImportSuccessfulLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import jakarta.ejb.EJB;
import jakarta.ejb.SessionContext;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Unmarshaller;

/**
 * Import der Trace-BoM-Dateien, die die Fertiger in verschiedenen Verzeichnissen auf dem sftp bereitstellen.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
@Stateless
public class TBOldImportServiceBean extends AbstractTBImportServiceBean {
    /*
     * Timeout konfigurieren:
     * standalone.xml, <subsystem xmlns="urn:jboss:domain:transactions:6.0">:
     * <coordinator-environment ... default-timeout="14400"/>
     * Angabe in Sekunden; 4 Stunden = 60*60*4 = 14400
     */

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private static final Charset ENCODING = Constants.CHARSET_UTF_8;

    private static final double TRACE_BOM_WARNING_THRESHOLD = 10.0;


    @EJB
    private SupplierRepository supplierManager;
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

    @PersistenceContext
    private EntityManager em;
    @Resource
    private SessionContext ctx;



    // @EJB
    // private SchedulerServiceBean schedulerService;
    // @EJB
    // private RmaImportServiceBean rmaImportServiceBean;
    // @EJB
    // private SvcMsgImportServiceBean svcMsgImportServiceBean;
    // @EJB
    // private SvcMsgRebuildMaterializedDeltaServiceBean svcMsgRebuildServiceBean;



    /** Create file for logistic */
    TraceBoMRootMappingType createLogisticXMLFile(TaskNodeLog folderTask, File localFolder, File sourceFile,
            FolderConfig folderConfig)
            throws ImportAbortedException {
        String correctedContent;
        try (BufferedReader input = new BufferedReader(new FileReader(sourceFile, ENCODING))) {
            // Datei vollständig in StringBuilder einlesen, damit der Inhalt vorab korrigiert werden kann
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = input.readLine()) != null) {
                content.append(line);
            }

            // Some KDMS files have obscure characters at the beginning!
            correctedContent = content.substring(content.indexOf("<"));
        }
        catch (Exception e) { // FileNotFoundException, IOException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "File cannot be opened to correct the content"));
        }


        Unmarshaller unmarshaller;
        try {
            unmarshaller = JAXBContext.newInstance(TraceBoMRootMappingType.class).createUnmarshaller();
        }
        catch (Exception e) { // JAXBException, IllegalArgumentException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "Error initializing unmarshaller"));
        }


        TraceBoMRootMappingType rootMappingObject;
        try (StringReader inputReader = new StringReader(correctedContent)) {
            rootMappingObject = (TraceBoMRootMappingType) unmarshaller.unmarshal(inputReader);
        }
        catch (Exception e) { // JAXBException, UnmarshalException, IllegalArgumentException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "Error unmarshalling file"));
        }


        List<TraceBoMMappingType> traceBoMs = rootMappingObject.getSerialObjects();
        TraceBoMHeaderType header = rootMappingObject.getHeader();

        if (traceBoMs.isEmpty()) {
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "No trace BoMs"));
        }

        String outputFileName = sourceFile.getName().substring(0, (sourceFile.getName().length() - 4));
        if (!outputFileName.contains(header.getDeliveryNoteNumber())) {
            outputFileName = outputFileName + "_" + header.getDeliveryNoteNumber();
        }
        outputFileName = localFolder.getName() + "_" + outputFileName + ".xml";


        StringBuilder output = new StringBuilder(1024);
        output.append("<?xml version=\"1.0\" standalone=\"yes\" ?>\n");

        // open list
        output.append("<SHIPPING_LISTS>\n<SHIP_LIST>\n");

        // Create header data
        output.append("<HEADER>\n");
        output.append("<LSNR>").append(header.getDeliveryNoteNumber()).append("</LSNR>\n");
        output.append("<LotNr>").append(header.getLotNumber()).append("</LotNr>\n");
        output.append("<CE_Nr>").append(header.getMaterialRevision().getRevisionNumber()).append("</CE_Nr>\n");
        output.append("<BELEG_Nr>").append(header.getOrderNumber()).append("</BELEG_Nr>\n");
        output.append("</HEADER>\n");

        // Create serObj. data
        output.append("<DATA>\n");
        // Iterate over all serial numbers
        for (TraceBoMMappingType traceBoM : traceBoMs) {
            output.append("<SNRSET>\n");

            output.append("<SNR>").append(traceBoM.getSerialNumber()).append("</SNR>\n");
            if (StringUtils.isEmpty(traceBoM.getCustomerSerialNumber())) {
                output.append("<SNR_CUST/>\n");
            }
            else {
                output.append("<SNR_CUST>").append(traceBoM.getCustomerSerialNumber()).append("</SNR_CUST>\n");
            }
            output.append("<SNR_SYS1/>\n");
            output.append("<SNR_SYS2/>\n");
            output.append("<SNR_SYS3/>\n");
            output.append("<SNR_SYS4/>\n");
            output.append("<SNR_SYS5/>\n");

            output.append("</SNRSET>\n");
        }
        output.append("</DATA>\n");

        // close list
        output.append("</SHIP_LIST>\n</SHIPPING_LISTS>\n");


        try (BufferedWriter writer = new BufferedWriter(new FileWriter(folderConfig.logisticTraceBoMFolder + File.separator + outputFileName))) {
            writer.write(output.toString());
        }
        catch (Exception e) { // IOException
            throw new ImportAbortedException(new FileImportAbortedWithErrorsLog(localFolder.getName() + File.separator + sourceFile.getName(),
                    "Error creating logistic XML file by Trace BoM file (old structure)"));
        }


        // Logistic file has been created successfully!
        folderTask.addSubTask(new FileImportSuccessfulLog(localFolder.getName() + File.separator + sourceFile.getName(),
                traceBoMs.size()));
        return rootMappingObject;
    }



    /** @return success */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ImportResult saveTraceBoM(File sourceFile, TraceBoMRootMappingType trBoMRootImported) {
        long start = System.currentTimeMillis();
        try {
            // Import trace BoM
            Supplier supplier = supplierManager.findById(trBoMRootImported.getHeader().getSupplierCode());

            TraceBoMRevisionMappingType trBoMRevisionImported = trBoMRootImported.getHeader().getMaterialRevision();

            // Some CMs only deliver the Rev6 field. In order to find a proper revision the alternative number must be added!
            String revisionNo = correctRevNr(trBoMRevisionImported.getRevisionNumber());

            MaterialRevision materialRevision = findMaterialRevision(trBoMRevisionImported.getMaterialNumber(), revisionNo);
            Material material = materialRevision.getMaterial();

            LocalDate parsedProdDate = parseToLocalDate(trBoMRootImported.getHeader().getProductionDate());

            Map<TraceBoMMappingType, TraceBoM> persistedBoMPerImportedBoM = new HashMap<>();

            for (TraceBoMMappingType trBoMImported : trBoMRootImported.getSerialObjects()) {
                SerialObject serialObject = findSerialObject(trBoMImported.getSerialNumber(), trBoMImported.getCustomerSerialNumber(),
                        material, trBoMRootImported.getHeader().getOrderNumber(), parsedProdDate);

                // First we check if the current BoM has been already persisted!
                TraceBoM persistedBoM = persistedBoMPerImportedBoM.get(trBoMImported);
                if (persistedBoM != null) {
                    serialObject.setTraceBom(persistedBoM);
                }

                if (persistedBoM == null) {
                    // Create a new trace BoM
                    TraceBoM traceBoM = new TraceBoM();
                    traceBoM.setDeliveryNoteNumber(trBoMRootImported.getHeader().getDeliveryNoteNumber());

                    traceBoM.setProductionDate(parsedProdDate);

                    traceBoM.setLotNumber(trBoMRootImported.getHeader().getLotNumber());
                    traceBoM.setOrderNumber(trBoMRootImported.getHeader().getOrderNumber());
                    traceBoM.setSupplier(supplier);
                    traceBoM.setMaterialRevision(materialRevision);

                    traceBoM = trBoMManager.persist(traceBoM, true, true);

                    // Save all trace BoMs that have been persisted
                    persistedBoMPerImportedBoM.put(trBoMImported, traceBoM);

                    serialObject.setTraceBom(traceBoM);

                    // Add all trace BoM items to trace BoM
                    importTraceBoMItems(traceBoM, trBoMImported, trBoMRootImported.getHeader());
                }
            }

            em.flush();
            return ImportResult.ok();
        }
        catch (Exception e) {
            ctx.setRollbackOnly();
            String errorMsg = "Error while processing trace file " + sourceFile.getName() + ": " + e.getMessage();
            logger.error(errorMsg);

            return ImportResult.fail(errorMsg);
        }
    }



    /**
     * Import trace BoM item objects
     *
     * @param traceBoM
     * @param rootMappingObject
     * @param bomHeader
     */
    private void importTraceBoMItems(TraceBoM traceBoM, TraceBoMMappingType rootMappingObject, TraceBoMHeaderType bomHeader) {
        int totalSize = rootMappingObject.getTraceBoMItems().size();
        int illegalItemCount = 0;
        for (TraceBoMItemMappingType mappingObject : rootMappingObject.getTraceBoMItems()) {
            String matSapNrImported = mappingObject.getMaterialSapNumber().replace("-", "");
            Material material = materialManager.findBySapNumber(matSapNrImported);


            if (material == null) {
                IllegalTraceBoMItem illegalItem = new IllegalTraceBoMItem();
                illegalItem.setTraceBom(traceBoM);
                illegalItem.setMaterialNumber(matSapNrImported);
                illegalItem.setManufacturer(mappingObject.getManufacturerName());
                illegalItem.setManufacturerRevision(mappingObject.getManufacturerRevision());
                illegalItem.setOrderCode(mappingObject.getOrderCode());
                illegalItem.setDateCode(mappingObject.getDateCode());

                illTrBoMItemManager.persist(illegalItem, false, false);
                illegalItemCount++;
            }
            else {
                TraceBoMItem traceBoMItem = new TraceBoMItem();
                traceBoMItem.setTraceBom(traceBoM);
                traceBoMItem.setMaterial(material);
                traceBoMItem.setQuantity(mappingObject.getQuantity());
                traceBoMItem.setManufacturerName(mappingObject.getManufacturerName());
                traceBoMItem.setManufacturerRevision(mappingObject.getManufacturerRevision());
                traceBoMItem.setOrderCode(mappingObject.getOrderCode());
                traceBoMItem.setDateCode(mappingObject.getDateCode());
                traceBoMItem.setInfoField1(mappingObject.getInfoField1());
                traceBoMItem.setInfoField2(mappingObject.getInfoField2());
                traceBoMItem.setInfoField3(mappingObject.getInfoField3());
                traceBoMItem.setInfoField4(mappingObject.getInfoField4());

                trBoMItemManager.persist(traceBoMItem, false, false);
            }
        } // end for rootMappingObject.getTraceBoMItems()

        if (totalSize > 0) {
            double illegalRatio = 100f * illegalItemCount / (double) totalSize;

            // Send mail to responsible persons that number of illegal items exceeds threshold!
            if (illegalRatio >= TRACE_BOM_WARNING_THRESHOLD) {
                sendMail(bomHeader, illegalRatio);
            }
        }
    }

    private void sendMail(TraceBoMHeaderType bomHeader, double illegalRatio) {
        DecimalFormat df = new DecimalFormat("0.00");
        StringBuilder messageText = new StringBuilder();
        String subject = Constants.APP_ENV + "Illegal material ratio warning for delivery note no. "
                + bomHeader.getDeliveryNoteNumber();
        Collection<String> receivers = getIllegalRatioWarningRecipients();

        messageText.append("Illegal material ratio: " + df.format(illegalRatio) + "%\n");
        messageText.append("Delivery note no.: " + bomHeader.getDeliveryNoteNumber() + "\n");
        messageText.append("Lot no.: " + bomHeader.getLotNumber() + "\n");
        messageText.append("Order no.: " + bomHeader.getOrderNumber() + "\n");
        messageText.append("Supplier: " + bomHeader.getSupplierCode() + "\n");

        Collection<String> to = new ArrayList<>();
        for (String s : receivers) {
            to.add(s);
        }

        try {
            MailMessage msg = new MailMessage();
            msg.setTo(to);
            msg.setSubject(Constants.APP_ENV + subject);
            msg.setMessage(messageText.toString());

            MailServiceFacade.sendMail(msg);
        }
        catch (Exception mailException) {
            mailException.printStackTrace();
        }
    }

}
