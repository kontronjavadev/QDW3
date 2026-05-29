package com.kontron.qdw.repository.service;

import com.kontron.qdw.domain.serial.*;
import com.kontron.qdw.domain.service.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class X2MessageRepository extends AbstractRepository<X2Message, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent X2 message by using the primary key of the provided object
     * @param x2Message
     * @return the X2 message or null if the object could not be found
     */
    @Generated
    public X2Message findById(X2Message x2Message) {
        return findById(x2Message.getId());
    }

    /**
     * Create a deep copy of the given X2 message
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new X2 message
     */
    @Generated
    public X2Message copy(X2Message sourceObject, X2Message targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new X2Message();
            targetObject.setServiceMessage(sourceObject.getServiceMessage());
        }

        targetObject.setAnalysisText(sourceObject.getAnalysisText());
        targetObject.setCauseText(sourceObject.getCauseText());
        targetObject.setCustomerReport(sourceObject.getCustomerReport());
        targetObject.setDefectComponent(sourceObject.getDefectComponent());
        targetObject.setDesignator(sourceObject.getDesignator());
        targetObject.setWorkCenter(sourceObject.getWorkCenter());
        targetObject.setDefectMaterial(sourceObject.getDefectMaterial());
        targetObject.setFaultAnalysis(sourceObject.getFaultAnalysis());
        targetObject.setMaterialRevision(sourceObject.getMaterialRevision());
        targetObject.setRepairErrorCode(sourceObject.getRepairErrorCode());
        targetObject.setRepairState(sourceObject.getRepairState());
        targetObject.setRepairTask(sourceObject.getRepairTask());
        targetObject.setSerialObject(sourceObject.getSerialObject());

        targetObject = persist(targetObject, false, false);

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Get the material of this X2 message
     * @param id
     * @return the material of this X2 message, or null if it could not be found
     */
    @Generated
    public Material getDefectMaterial(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(X2Message.NQ_GET_DEFECTMATERIAL, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the fault analysis of this X2 message
     * @param id
     * @return the fault analysis of this X2 message, or null if it could not be found
     */
    @Generated
    public FaultAnalysis getFaultAnalysis(long id) {
        final TypedQuery<FaultAnalysis> query = em.createNamedQuery(X2Message.NQ_GET_FAULTANALYSIS, FaultAnalysis.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the material revision of this X2 message
     * @param id
     * @return the material revision of this X2 message, or null if it could not be found
     */
    @Generated
    public MaterialRevision getMaterialRevision(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(X2Message.NQ_GET_MATERIALREVISION, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the repair error code of this X2 message
     * @param id
     * @return the repair error code of this X2 message, or null if it could not be found
     */
    @Generated
    public RepairErrorCode getRepairErrorCode(long id) {
        final TypedQuery<RepairErrorCode> query = em.createNamedQuery(X2Message.NQ_GET_REPAIRERRORCODE, RepairErrorCode.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the repair state of this X2 message
     * @param id
     * @return the repair state of this X2 message, or null if it could not be found
     */
    @Generated
    public RepairState getRepairState(long id) {
        final TypedQuery<RepairState> query = em.createNamedQuery(X2Message.NQ_GET_REPAIRSTATE, RepairState.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the repair task of this X2 message
     * @param id
     * @return the repair task of this X2 message, or null if it could not be found
     */
    @Generated
    public RepairTask getRepairTask(long id) {
        final TypedQuery<RepairTask> query = em.createNamedQuery(X2Message.NQ_GET_REPAIRTASK, RepairTask.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the serial object of this X2 message
     * @param id
     * @return the serial object of this X2 message, or null if it could not be found
     */
    @Generated
    public SerialObject getSerialObject(long id) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(X2Message.NQ_GET_SERIALOBJECT, SerialObject.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the service message of this X2 message
     * @param id
     * @return the service message of this X2 message, or null if it could not be found
     */
    @Generated
    public ServiceMessage getServiceMessage(long id) {
        final TypedQuery<ServiceMessage> query = em.createNamedQuery(X2Message.NQ_GET_SERVICEMESSAGE, ServiceMessage.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'defectMaterial' attribute of this X2 message
     * @param id
     * @param defectMaterial
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setDefectMaterial(long id, Material defectMaterial) {
        final X2Message bean = findById(id, true);

        bean.setDefectMaterial(defectMaterial);
    }

    /**
     * Change the 'faultAnalysis' attribute of this X2 message
     * @param id
     * @param faultAnalysis
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setFaultAnalysis(long id, FaultAnalysis faultAnalysis) {
        final X2Message bean = findById(id, true);

        bean.setFaultAnalysis(faultAnalysis);
    }

    /**
     * Change the 'materialRevision' attribute of this X2 message
     * @param id
     * @param materialRevision
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialRevision(long id, MaterialRevision materialRevision) {
        final X2Message bean = findById(id, true);

        bean.setMaterialRevision(materialRevision);
    }

    /**
     * Change the 'repairErrorCode' attribute of this X2 message
     * @param id
     * @param repairErrorCode
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setRepairErrorCode(long id, RepairErrorCode repairErrorCode) {
        final X2Message bean = findById(id, true);

        bean.setRepairErrorCode(repairErrorCode);
    }

    /**
     * Change the 'repairState' attribute of this X2 message
     * @param id
     * @param repairState
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setRepairState(long id, RepairState repairState) {
        final X2Message bean = findById(id, true);

        bean.setRepairState(repairState);
    }

    /**
     * Change the 'repairTask' attribute of this X2 message
     * @param id
     * @param repairTask
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setRepairTask(long id, RepairTask repairTask) {
        final X2Message bean = findById(id, true);

        bean.setRepairTask(repairTask);
    }

    /**
     * Change the 'serialObject' attribute of this X2 message
     * @param id
     * @param serialObject
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSerialObject(long id, SerialObject serialObject) {
        final X2Message bean = findById(id, true);

        bean.setSerialObject(serialObject);
    }

    /**
     * Change the 'serviceMessage' attribute of this X2 message
     * @param id
     * @param serviceMessage
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setServiceMessage(long id, ServiceMessage serviceMessage) {
        final X2Message bean = findById(id, true);

        bean.setServiceMessage(serviceMessage);
    }

}