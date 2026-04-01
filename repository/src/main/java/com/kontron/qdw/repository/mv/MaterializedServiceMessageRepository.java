package com.kontron.qdw.repository.mv;

import com.kontron.qdw.domain.serial.*;
import com.kontron.qdw.domain.mv.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class MaterializedServiceMessageRepository extends AbstractRepository<MaterializedServiceMessage, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent materialized service message by using the primary key of the provided object
     * @param materializedServiceMessage
     * @return the materialized service message or null if the object could not be found
     */
    @Generated
    public MaterializedServiceMessage findById(MaterializedServiceMessage materializedServiceMessage) {
        return findById(materializedServiceMessage.getId());
    }

    /**
     * Create a deep copy of the given materialized service message
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new materialized service message
     */
    @Generated
    public MaterializedServiceMessage copy(MaterializedServiceMessage sourceObject, MaterializedServiceMessage targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new MaterializedServiceMessage();
        }

        targetObject.setServiceOrder(sourceObject.getServiceOrder());
        targetObject.setServiceOrderType(sourceObject.getServiceOrderType());
        targetObject.setRmaType(sourceObject.getRmaType());
        targetObject.setLocation(sourceObject.getLocation());
        targetObject.setServiceName(sourceObject.getServiceName());
        targetObject.setTaskName(sourceObject.getTaskName());
        targetObject.setStateName(sourceObject.getStateName());
        targetObject.setInternalArrivalDate(sourceObject.getInternalArrivalDate());
        targetObject.setInternalShipmentDate(sourceObject.getInternalShipmentDate());
        targetObject.setBasicStartDate(sourceObject.getBasicStartDate());
        targetObject.setBasicEndDate(sourceObject.getBasicEndDate());
        targetObject.setDesignator(sourceObject.getDesignator());
        targetObject.setDefectComponent(sourceObject.getDefectComponent());
        targetObject.setAnalysisText(sourceObject.getAnalysisText());
        targetObject.setInternalReport(sourceObject.getInternalReport());
        targetObject.setExternalReport(sourceObject.getExternalReport());
        targetObject.setCustomerReport(sourceObject.getCustomerReport());
        targetObject.setEpidemicFailure(sourceObject.isEpidemicFailure());
        targetObject.setErrorId(sourceObject.getErrorId());
        targetObject.setOrigin(sourceObject.getOrigin());
        targetObject.setCustomerFailure(sourceObject.isCustomerFailure());
        targetObject.setCustomerCode(sourceObject.getCustomerCode());
        targetObject.setCustomerName(sourceObject.getCustomerName());
        targetObject.setCustomerGroup(sourceObject.getCustomerGroup());
        targetObject.setCountryCode(sourceObject.getCountryCode());
        targetObject.setCountryName(sourceObject.getCountryName());
        targetObject.setSupplierCode(sourceObject.getSupplierCode());
        targetObject.setSupplierName(sourceObject.getSupplierName());
        targetObject.setSupplierArrivalDate(sourceObject.getSupplierArrivalDate());
        targetObject.setCustomerShipmentDate(sourceObject.getCustomerShipmentDate());
        targetObject.setFaultAnalysisCode(sourceObject.getFaultAnalysisCode());
        targetObject.setErrorCodeName(sourceObject.getErrorCodeName());
        targetObject.setExternalSupplierCode(sourceObject.getExternalSupplierCode());
        targetObject.setExternalSupplierName(sourceObject.getExternalSupplierName());
        targetObject.setDeliveryNoteNumber(sourceObject.getDeliveryNoteNumber());
        targetObject.setErrorCodeGroup(sourceObject.getErrorCodeGroup());
        targetObject.setSymptomShortText(sourceObject.getSymptomShortText());
        targetObject.setRepairTaskShortText(sourceObject.getRepairTaskShortText());
        targetObject.setRepairDescription(sourceObject.getRepairDescription());
        targetObject.setOwnerLocation(sourceObject.getOwnerLocation());
        targetObject.setErrorShortText(sourceObject.getErrorShortText());
        targetObject.setMeSerialObjectId(sourceObject.getMeSerialObjectId());
        targetObject.setParentSerialObjectId(sourceObject.getParentSerialObjectId());
        targetObject.setSerialNumber(sourceObject.getSerialNumber());
        targetObject.setParentSerialNumber(sourceObject.getParentSerialNumber());
        targetObject.setMaterialNumber(sourceObject.getMaterialNumber());
        targetObject.setParentMaterialNumber(sourceObject.getParentMaterialNumber());
        targetObject.setMaterialType(sourceObject.getMaterialType());
        targetObject.setParentMaterialType(sourceObject.getParentMaterialType());
        targetObject.setMaterialShortText(sourceObject.getMaterialShortText());
        targetObject.setParentMaterialShortText(sourceObject.getParentMaterialShortText());
        targetObject.setSapNumber(sourceObject.getSapNumber());
        targetObject.setParentSapNumber(sourceObject.getParentSapNumber());
        targetObject.setMaterialHierarchy(sourceObject.getMaterialHierarchy());
        targetObject.setParentMaterialHierarchy(sourceObject.getParentMaterialHierarchy());
        targetObject.setRevisionId(sourceObject.getRevisionId());
        targetObject.setParentRevisionId(sourceObject.getParentRevisionId());
        targetObject.setRevisionNumber(sourceObject.getRevisionNumber());
        targetObject.setParentRevisionNumber(sourceObject.getParentRevisionNumber());
        targetObject.setAssemblyDate(sourceObject.getAssemblyDate());
        targetObject.setAssemblyPO(sourceObject.getAssemblyPO());
        targetObject.setPlant(sourceObject.getPlant());
        targetObject.setSerialObject(sourceObject.getSerialObject());
        targetObject.setMaterial(sourceObject.getMaterial());

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
     * Get the serial object of this materialized service message
     * @param id
     * @return the serial object of this materialized service message, or null if it could not be found
     */
    @Generated
    public SerialObject getSerialObject(long id) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(MaterializedServiceMessage.NQ_GET_SERIALOBJECT, SerialObject.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the material of this materialized service message
     * @param id
     * @return the material of this materialized service message, or null if it could not be found
     */
    @Generated
    public Material getMaterial(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(MaterializedServiceMessage.NQ_GET_MATERIAL, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'serialObject' attribute of this materialized service message
     * @param id
     * @param serialObject
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSerialObject(long id, SerialObject serialObject) {
        final MaterializedServiceMessage bean = findById(id, true);

        bean.setSerialObject(serialObject);
    }

    /**
     * Change the 'material' attribute of this materialized service message
     * @param id
     * @param material
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterial(long id, Material material) {
        final MaterializedServiceMessage bean = findById(id, true);

        bean.setMaterial(material);
    }

}