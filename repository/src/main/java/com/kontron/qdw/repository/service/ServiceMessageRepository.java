package com.kontron.qdw.repository.service;

import com.kontron.qdw.domain.serial.*;
import java.util.*;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.service.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class ServiceMessageRepository extends AbstractRepository<ServiceMessage, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent service message by using the primary key of the provided object
     * @param serviceMessage
     * @return the service message or null if the object could not be found
     */
    @Generated
    public ServiceMessage findById(ServiceMessage serviceMessage) {
        return findById(serviceMessage.getId());
    }

    /**
     * Create a deep copy of the given service message
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new service message
     */
    @Generated
    public ServiceMessage copy(ServiceMessage sourceObject, ServiceMessage targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new ServiceMessage();
            targetObject.setSerialObject(sourceObject.getSerialObject());
            targetObject.setServiceOrder(sourceObject.getServiceOrder());
        }

        targetObject.setAnalysisText(sourceObject.getAnalysisText());
        targetObject.setBasicFinishDate(sourceObject.getBasicFinishDate());
        targetObject.setBasicStartDate(sourceObject.getBasicStartDate());
        targetObject.setCauseText(sourceObject.getCauseText());
        targetObject.setCustomerFailure(sourceObject.isCustomerFailure());
        targetObject.setCustomerReport(sourceObject.getCustomerReport());
        targetObject.setDefectComponent(sourceObject.getDefectComponent());
        targetObject.setDeliveryNoteNumber(sourceObject.getDeliveryNoteNumber());
        targetObject.setDesignator(sourceObject.getDesignator());
        targetObject.setEpidemicFailure(sourceObject.isEpidemicFailure());
        targetObject.setErrorId(sourceObject.getErrorId());
        targetObject.setExternalReport(sourceObject.getExternalReport());
        targetObject.setInternalArrivalDate(sourceObject.getInternalArrivalDate());
        targetObject.setInternalReport(sourceObject.getInternalReport());
        targetObject.setInternalShipmentDate(sourceObject.getInternalShipmentDate());
        targetObject.setOrigin(sourceObject.getOrigin());
        targetObject.setRebuildFlag(sourceObject.getRebuildFlag());
        targetObject.setRepairDescription(sourceObject.getRepairDescription());
        targetObject.setServiceMessageId(sourceObject.getServiceMessageId());
        targetObject.setExternalSupplier(sourceObject.getExternalSupplier());
        targetObject.setMaterialRevision(sourceObject.getMaterialRevision());
        targetObject.setPlant(sourceObject.getPlant());
        targetObject.setFaultAnalysis(sourceObject.getFaultAnalysis());
        targetObject.setrMAType(sourceObject.getrMAType());
        targetObject.setRepairErrorCode(sourceObject.getRepairErrorCode());
        targetObject.setRepairLocation(sourceObject.getRepairLocation());
        targetObject.setRepairService(sourceObject.getRepairService());
        targetObject.setRepairState(sourceObject.getRepairState());
        targetObject.setRepairTask(sourceObject.getRepairTask());

        targetObject = persist(targetObject, false, false);

        for (final Material material : sourceObject.getFailureMaterials())
            targetObject.getFailureMaterials().add(material);

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Get the supplier of this service message
     * @param id
     * @return the supplier of this service message, or null if it could not be found
     */
    @Generated
    public Supplier getExternalSupplier(long id) {
        final TypedQuery<Supplier> query = em.createNamedQuery(ServiceMessage.NQ_GET_EXTERNALSUPPLIER, Supplier.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the material revision of this service message
     * @param id
     * @return the material revision of this service message, or null if it could not be found
     */
    @Generated
    public MaterialRevision getMaterialRevision(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(ServiceMessage.NQ_GET_MATERIALREVISION, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the plant of this service message
     * @param id
     * @return the plant of this service message, or null if it could not be found
     */
    @Generated
    public Plant getPlant(long id) {
        final TypedQuery<Plant> query = em.createNamedQuery(ServiceMessage.NQ_GET_PLANT, Plant.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the serial object of this service message
     * @param id
     * @return the serial object of this service message, or null if it could not be found
     */
    @Generated
    public SerialObject getSerialObject(long id) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(ServiceMessage.NQ_GET_SERIALOBJECT, SerialObject.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the service order of this service message
     * @param id
     * @return the service order of this service message, or null if it could not be found
     */
    @Generated
    public ServiceOrder getServiceOrder(long id) {
        final TypedQuery<ServiceOrder> query = em.createNamedQuery(ServiceMessage.NQ_GET_SERVICEORDER, ServiceOrder.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all materials of this service message
     * @param id
     * @return a list of materials of this service message
     */
    @Generated
    public List<Material> getFailureMaterials(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(ServiceMessage.NQ_GET_FAILUREMATERIALS, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Get the fault analysis of this service message
     * @param id
     * @return the fault analysis of this service message, or null if it could not be found
     */
    @Generated
    public FaultAnalysis getFaultAnalysis(long id) {
        final TypedQuery<FaultAnalysis> query = em.createNamedQuery(ServiceMessage.NQ_GET_FAULTANALYSIS, FaultAnalysis.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the RMA type of this service message
     * @param id
     * @return the RMA type of this service message, or null if it could not be found
     */
    @Generated
    public RMAType getRMAType(long id) {
        final TypedQuery<RMAType> query = em.createNamedQuery(ServiceMessage.NQ_GET_RMATYPE, RMAType.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the repair error code of this service message
     * @param id
     * @return the repair error code of this service message, or null if it could not be found
     */
    @Generated
    public RepairErrorCode getRepairErrorCode(long id) {
        final TypedQuery<RepairErrorCode> query = em.createNamedQuery(ServiceMessage.NQ_GET_REPAIRERRORCODE, RepairErrorCode.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the repair location of this service message
     * @param id
     * @return the repair location of this service message, or null if it could not be found
     */
    @Generated
    public RepairLocation getRepairLocation(long id) {
        final TypedQuery<RepairLocation> query = em.createNamedQuery(ServiceMessage.NQ_GET_REPAIRLOCATION, RepairLocation.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the repair service of this service message
     * @param id
     * @return the repair service of this service message, or null if it could not be found
     */
    @Generated
    public RepairService getRepairService(long id) {
        final TypedQuery<RepairService> query = em.createNamedQuery(ServiceMessage.NQ_GET_REPAIRSERVICE, RepairService.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the repair state of this service message
     * @param id
     * @return the repair state of this service message, or null if it could not be found
     */
    @Generated
    public RepairState getRepairState(long id) {
        final TypedQuery<RepairState> query = em.createNamedQuery(ServiceMessage.NQ_GET_REPAIRSTATE, RepairState.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the repair task of this service message
     * @param id
     * @return the repair task of this service message, or null if it could not be found
     */
    @Generated
    public RepairTask getRepairTask(long id) {
        final TypedQuery<RepairTask> query = em.createNamedQuery(ServiceMessage.NQ_GET_REPAIRTASK, RepairTask.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all X2 messages of this service message
     * @param id
     * @return a list of X2 messages of this service message
     */
    @Generated
    public List<X2Message> getX2Messages(long id) {
        final TypedQuery<X2Message> query = em.createNamedQuery(ServiceMessage.NQ_GET_X2MESSAGES, X2Message.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Change the 'externalSupplier' attribute of this service message
     * @param id
     * @param externalSupplier
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setExternalSupplier(long id, Supplier externalSupplier) {
        final ServiceMessage bean = findById(id, true);

        bean.setExternalSupplier(externalSupplier);
    }

    /**
     * Change the 'materialRevision' attribute of this service message
     * @param id
     * @param materialRevision
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialRevision(long id, MaterialRevision materialRevision) {
        final ServiceMessage bean = findById(id, true);

        bean.setMaterialRevision(materialRevision);
    }

    /**
     * Change the 'plant' attribute of this service message
     * @param id
     * @param plant
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setPlant(long id, Plant plant) {
        final ServiceMessage bean = findById(id, true);

        bean.setPlant(plant);
    }

    /**
     * Change the 'serialObject' attribute of this service message
     * @param id
     * @param serialObject
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSerialObject(long id, SerialObject serialObject) {
        final ServiceMessage bean = findById(id, true);

        bean.setSerialObject(serialObject);
    }

    /**
     * Change the 'serviceOrder' attribute of this service message
     * @param id
     * @param serviceOrder
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setServiceOrder(long id, ServiceOrder serviceOrder) {
        final ServiceMessage bean = findById(id, true);

        bean.setServiceOrder(serviceOrder);
    }

    /**
     * Change the 'faultAnalysis' attribute of this service message
     * @param id
     * @param faultAnalysis
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setFaultAnalysis(long id, FaultAnalysis faultAnalysis) {
        final ServiceMessage bean = findById(id, true);

        bean.setFaultAnalysis(faultAnalysis);
    }

    /**
     * Change the 'rMAType' attribute of this service message
     * @param id
     * @param rMAType
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setRMAType(long id, RMAType rMAType) {
        final ServiceMessage bean = findById(id, true);

        bean.setrMAType(rMAType);
    }

    /**
     * Change the 'repairErrorCode' attribute of this service message
     * @param id
     * @param repairErrorCode
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setRepairErrorCode(long id, RepairErrorCode repairErrorCode) {
        final ServiceMessage bean = findById(id, true);

        bean.setRepairErrorCode(repairErrorCode);
    }

    /**
     * Change the 'repairLocation' attribute of this service message
     * @param id
     * @param repairLocation
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setRepairLocation(long id, RepairLocation repairLocation) {
        final ServiceMessage bean = findById(id, true);

        bean.setRepairLocation(repairLocation);
    }

    /**
     * Change the 'repairService' attribute of this service message
     * @param id
     * @param repairService
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setRepairService(long id, RepairService repairService) {
        final ServiceMessage bean = findById(id, true);

        bean.setRepairService(repairService);
    }

    /**
     * Change the 'repairState' attribute of this service message
     * @param id
     * @param repairState
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setRepairState(long id, RepairState repairState) {
        final ServiceMessage bean = findById(id, true);

        bean.setRepairState(repairState);
    }

    /**
     * Change the 'repairTask' attribute of this service message
     * @param id
     * @param repairTask
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setRepairTask(long id, RepairTask repairTask) {
        final ServiceMessage bean = findById(id, true);

        bean.setRepairTask(repairTask);
    }

    /**
     * Remove the persistent material object from the corresponding list of this service message
     * @param id
     * @param material
     */
    @Generated
    public void removeMaterialFromFailureMaterials(long id, Material material) {
        final ServiceMessage bean = findById(id, true);

        for (final Material item : bean.getFailureMaterials())
            if (material.getId() == item.getId()) {
                bean.getFailureMaterials().remove(item);
                return;
            }
    }

    /**
     * Add the persistent material object to the corresponding list of this service message
     * @param id
     * @param material
     * @throws DuplicateCollectionEntryException if the caller tries to add an element to the collection twice
     */
    @Generated
    public void addMaterialToFailureMaterials(long id, Material material) {
        final ServiceMessage bean = findById(id, true);

        // Prevent duplicate entries
        for (final Material item : bean.getFailureMaterials())
            if (material.getId() == item.getId())
                throw new DuplicateCollectionEntryException("Entry already exists in this collection!");

        bean.getFailureMaterials().add(material);
    }

}