package com.kontron.qdw.boundary.service.tracebomimport;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

import org.apache.commons.lang3.StringUtils;

import com.kontron.qdw.domain.material.Material;
import com.kontron.qdw.domain.material.MaterialRevision;
import com.kontron.qdw.domain.serial.SerialObject;
import com.kontron.qdw.repository.base.PlantRepository;
import com.kontron.qdw.repository.base.SupplierRepository;
import com.kontron.qdw.repository.material.MaterialRepository;
import com.kontron.qdw.repository.material.MaterialRevisionRepository;
import com.kontron.qdw.repository.serial.SerialObjectRepository;
import com.kontron.util.text.StringUtil;

import jakarta.ejb.EJB;

/**
 * Abstrakte Basis-Klasse für Import der Trace-BoM-Dateien, die die Fertiger in verschiedenen Verzeichnissen auf dem sftp bereitstellen.
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
public abstract class AbstractTBImportServiceBean {
    // private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    // private static final Charset ENCODING = Constants.CHARSET_UTF_8;
    //
    // private static final String SCHEMA_PATH = "/schema/";
    // private static final String SCHEMA_NAME = "TraceBoM.xsd";
    //
    private static final String DEFAULT_PLANT_CODE = "6000";
    // private static final String REVISION_NO_SUFFIX = " ALT(01)";

    private static final DateTimeFormatter FLEXIBLE_FORMATTER = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"))
            .appendOptional(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"))
            .appendOptional(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))
            .toFormatter();


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

    // @PersistenceContext
    // private EntityManager em;
    // @Resource
    // private SessionContext ctx;



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
                throw new Exception("Material " + materialNumber + " does not exist, therefore revision "
                        + revisionNumber + " could not be created.");
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



    LocalDate parseToLocalDate(String dateText) throws Exception {
        if (StringUtils.isBlank(dateText)) {
            throw new Exception("Production date is not set.");
        }
        try {
            return LocalDateTime.parse(dateText, FLEXIBLE_FORMATTER).toLocalDate();
        }
        catch (DateTimeParseException dtpe) {
            throw new Exception("Production date " + dateText + " has no accepted format.");
        }
    }

}
