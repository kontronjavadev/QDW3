package com.kontron.qdw.repository.serial;

import com.kontron.qdw.domain.serial.*;
import java.util.*;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.service.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class SerialObjectRepository extends AbstractRepository<SerialObject, Long> {
    @Generated
    private static final String PARAM_MATERIAL = "material";
    @Generated
    private static final String PARAM_ID = "id";
    @Generated
    private static final String PARAM_SERIALNUMBER = "serialNumber";
    @Generated
    private final ArrivalRepository arrivalManager;
    @Generated
    private final ShipmentRepository shipmentManager;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectRepository() {
        this.arrivalManager = null;
        this.shipmentManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param arrivalManager
     * @param shipmentManager
     */
    @Inject
    @Generated
    public SerialObjectRepository(ArrivalRepository arrivalManager, ShipmentRepository shipmentManager) {
        this.arrivalManager = arrivalManager;
        this.shipmentManager = shipmentManager;
    }

    /**
     * Find a persistent serial object by using the primary key of the provided object
     * @param serialObject
     * @return the serial object or null if the object could not be found
     */
    @Generated
    public SerialObject findById(SerialObject serialObject) {
        return findById(serialObject.getId());
    }

    /**
     * Merge the serial object object
     * @param serialObject
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged serial object object
     */
    @Generated
    public SerialObject merge(SerialObject serialObject, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByIdAndSerialNumberAndMaterialId(serialObject.getId(), serialObject.getSerialNumber(), serialObject.getMaterial()))
            throw new UniqueConstraintViolationException("Serial object with serial number '" + serialObject.getSerialNumber() + "' and material '"
                    + serialObject.getMaterial().getId() + "' already exists!");

        return merge(serialObject, performFlush);
    }

    /**
     * Persist the serial object object
     * @param serialObject
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted serial object object
     */
    @Generated
    public SerialObject persist(SerialObject serialObject, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsBySerialNumberAndMaterialId(serialObject.getSerialNumber(), serialObject.getMaterial()))
            throw new UniqueConstraintViolationException("Serial object with serial number '" + serialObject.getSerialNumber() + "' and material '"
                    + serialObject.getMaterial().getId() + "' already exists!");

        return persist(serialObject, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given serial object
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new serial object
     */
    @Generated
    public SerialObject copy(SerialObject sourceObject, SerialObject targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new SerialObject();
            targetObject.setParentObject(sourceObject.getParentObject());
        }

        targetObject.setSerialNumber(sourceObject.getSerialNumber());
        targetObject.setAssemblyDate(sourceObject.getAssemblyDate());
        targetObject.setCustomerSerialNumber(sourceObject.getCustomerSerialNumber());
        targetObject.setProductionOrderNumber(sourceObject.getProductionOrderNumber());
        targetObject.setMaterial(sourceObject.getMaterial());
        targetObject.setTraceBom(sourceObject.getTraceBom());

        targetObject = persist(targetObject, true, false, false);

        for (final SerialObject serialObject : sourceObject.getSerialObjects()) {
            var newSerialObject = new SerialObject();
            newSerialObject.setParentObject(targetObject);

            newSerialObject = copy(serialObject, newSerialObject, loggedOnUserId);
            targetObject.getSerialObjects().add(newSerialObject);
        }

        for (final Arrival arrival : sourceObject.getArrivals()) {
            var newArrival = new Arrival();
            newArrival.setSerialObject(targetObject);

            newArrival = arrivalManager.copy(arrival, newArrival, loggedOnUserId);
            targetObject.getArrivals().add(newArrival);
        }

        for (final Shipment shipment : sourceObject.getShipments()) {
            var newShipment = new Shipment();
            newShipment.setSerialObject(targetObject);

            newShipment = shipmentManager.copy(shipment, newShipment, loggedOnUserId);
            targetObject.getShipments().add(newShipment);
        }

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Check if the given serial object already exists
     * @param serialNumber
     * @param material
     * @return true if the serial object already exists
     */
    @Generated
    public boolean existsBySerialNumberAndMaterialId(String serialNumber, Material material) {
        if (serialNumber == null)
            throw new IllegalArgumentException("Parameter \"serialNumber\" must not be null!");

        if (material == null)
            throw new IllegalArgumentException("Parameter \"material\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(SerialObject.NQ_UK_EXISTS_BY_SERIALNUMBER_AND_MATERIAL, Long.class);
        query.setParameter(PARAM_SERIALNUMBER, serialNumber);
        query.setParameter(PARAM_MATERIAL, material.getId());

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given serial object already exists
     * @param id
     * @param serialNumber
     * @param material
     * @return true if the serial object already exists
     */
    @Generated
    public boolean existsByIdAndSerialNumberAndMaterialId(long id, String serialNumber, Material material) {
        if (serialNumber == null)
            throw new IllegalArgumentException("Parameter \"serialNumber\" must not be null!");

        if (material == null)
            throw new IllegalArgumentException("Parameter \"material\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(SerialObject.NQ_UK_EXISTS_BY_SERIALNUMBER_AND_MATERIAL_AND_ID, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_ID, id);
        query.setParameter(PARAM_SERIALNUMBER, serialNumber);
        query.setParameter(PARAM_MATERIAL, material.getId());

        return query.getSingleResult() != 0;
    }

    /**
     * Search for serial object objects by using the provided parameters
     * @param serialNumber
     * @param material
     * @return a list that contains all serial object objects that match the provided filter criteria
     */
    @Generated
    public List<SerialObject> searchBySerialNumberAndMaterialId(String serialNumber, Material material) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(SerialObject.NQ_UK_SEARCH_BY_SERIALNUMBER_AND_MATERIAL, SerialObject.class);
        query.setParameter(PARAM_SERIALNUMBER, serialNumber);
        query.setParameter(PARAM_MATERIAL, material.getId());

        return query.getResultList();
    }

    /**
     * Find a persistent serial object object by using the provided parameters
     * @param serialNumber
     * @param material
     * @return the serial object object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public SerialObject findBySerialNumberAndMaterialId(String serialNumber, Material material) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(SerialObject.NQ_UK_FIND_BY_SERIALNUMBER_AND_MATERIAL, SerialObject.class);
        query.setParameter(PARAM_SERIALNUMBER, serialNumber);
        query.setParameter(PARAM_MATERIAL, material.getId());

        final List<SerialObject> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Get the material of this serial object
     * @param id
     * @return the material of this serial object, or null if it could not be found
     */
    @Generated
    public Material getMaterial(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(SerialObject.NQ_GET_MATERIAL, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the serial object of this serial object
     * @param id
     * @return the serial object of this serial object, or null if it could not be found
     */
    @Generated
    public SerialObject getParentObject(long id) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(SerialObject.NQ_GET_PARENTOBJECT, SerialObject.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all serial objects of this serial object
     * @param id
     * @return a list of serial objects of this serial object
     */
    @Generated
    public List<SerialObject> getSerialObjects(long id) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(SerialObject.NQ_GET_SERIALOBJECTS, SerialObject.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Get all assembly records of this serial object
     * @param id
     * @return a list of assembly records of this serial object
     */
    @Generated
    public List<AssemblyRecord> getAssemblyRecords(long id) {
        final TypedQuery<AssemblyRecord> query = em.createNamedQuery(SerialObject.NQ_GET_ASSEMBLYRECORDS, AssemblyRecord.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Get all arrivals of this serial object
     * @param id
     * @return a list of arrivals of this serial object
     */
    @Generated
    public List<Arrival> getArrivals(long id) {
        final TypedQuery<Arrival> query = em.createNamedQuery(SerialObject.NQ_GET_ARRIVALS, Arrival.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Get all shipments of this serial object
     * @param id
     * @return a list of shipments of this serial object
     */
    @Generated
    public List<Shipment> getShipments(long id) {
        final TypedQuery<Shipment> query = em.createNamedQuery(SerialObject.NQ_GET_SHIPMENTS, Shipment.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Get the trace BoM of this serial object
     * @param id
     * @return the trace BoM of this serial object, or null if it could not be found
     */
    @Generated
    public TraceBoM getTraceBom(long id) {
        final TypedQuery<TraceBoM> query = em.createNamedQuery(SerialObject.NQ_GET_TRACEBOM, TraceBoM.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all service messages of this serial object
     * @param id
     * @return a list of service messages of this serial object
     */
    @Generated
    public List<ServiceMessage> getServiceMessages(long id) {
        final TypedQuery<ServiceMessage> query = em.createNamedQuery(SerialObject.NQ_GET_SERVICEMESSAGES, ServiceMessage.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Change the 'material' attribute of this serial object
     * @param id
     * @param material
     * @throws UniqueConstraintViolationException if the unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterial(long id, Material material) {
        final SerialObject bean = findById(id, true);

        // Perform unique key check
        if (existsByIdAndSerialNumberAndMaterialId(bean.getId(), bean.getSerialNumber(), material))
            throw new UniqueConstraintViolationException("A unique key constraint check for serial object with ID '" + id + "' has failed!");

        bean.setMaterial(material);
    }

    /**
     * Change the 'parentObject' attribute of this serial object
     * @param id
     * @param parentObject
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setParentObject(long id, SerialObject parentObject) {
        final SerialObject bean = findById(id, true);

        bean.setParentObject(parentObject);
    }

    /**
     * Change the 'traceBom' attribute of this serial object
     * @param id
     * @param traceBom
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setTraceBom(long id, TraceBoM traceBom) {
        final SerialObject bean = findById(id, true);

        bean.setTraceBom(traceBom);
    }

}