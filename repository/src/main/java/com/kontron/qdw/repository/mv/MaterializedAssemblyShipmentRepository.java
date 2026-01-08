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
public class MaterializedAssemblyShipmentRepository extends AbstractRepository<MaterializedAssemblyShipment, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent materialized assembly shipment by using the primary key of the provided object
     * @param materializedAssemblyShipment
     * @return the materialized assembly shipment or null if the object could not be found
     */
    @Generated
    public MaterializedAssemblyShipment findById(MaterializedAssemblyShipment materializedAssemblyShipment) {
        return findById(materializedAssemblyShipment.getId());
    }

    /**
     * Create a deep copy of the given materialized assembly shipment
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new materialized assembly shipment
     */
    @Generated
    public MaterializedAssemblyShipment copy(MaterializedAssemblyShipment sourceObject, MaterializedAssemblyShipment targetObject,
            long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new MaterializedAssemblyShipment();
        }

        targetObject.setArrivalDate(sourceObject.getArrivalDate());
        targetObject.setArrivalId(sourceObject.getArrivalId());
        targetObject.setArrivalMovementType(sourceObject.getArrivalMovementType());
        targetObject.setCountryCode(sourceObject.getCountryCode());
        targetObject.setCountryName(sourceObject.getCountryName());
        targetObject.setCustomerCode(sourceObject.getCustomerCode());
        targetObject.setCustomerName(sourceObject.getCustomerName());
        targetObject.setCustomerOrderNumber(sourceObject.getCustomerOrderNumber());
        targetObject.setOwnerLocation(sourceObject.getOwnerLocation());
        targetObject.setPurchaseOrderNumber(sourceObject.getPurchaseOrderNumber());
        targetObject.setShipmentDate(sourceObject.getShipmentDate());
        targetObject.setShipmentId(sourceObject.getShipmentId());
        targetObject.setShipmentMovementType(sourceObject.getShipmentMovementType());
        targetObject.setSupplierCode(sourceObject.getSupplierCode());
        targetObject.setSupplierName(sourceObject.getSupplierName());
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
        targetObject.setMaterial(sourceObject.getMaterial());
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
     * Get the material of this materialized assembly shipment
     * @param id
     * @return the material of this materialized assembly shipment, or null if it could not be found
     */
    @Generated
    public Material getMaterial(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(MaterializedAssemblyShipment.NQ_GET_MATERIAL, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the serial object of this materialized assembly shipment
     * @param id
     * @return the serial object of this materialized assembly shipment, or null if it could not be found
     */
    @Generated
    public SerialObject getSerialObject(long id) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(MaterializedAssemblyShipment.NQ_GET_SERIALOBJECT, SerialObject.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'material' attribute of this materialized assembly shipment
     * @param id
     * @param material
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterial(long id, Material material) {
        final MaterializedAssemblyShipment bean = findById(id, true);

        bean.setMaterial(material);
    }

    /**
     * Change the 'serialObject' attribute of this materialized assembly shipment
     * @param id
     * @param serialObject
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSerialObject(long id, SerialObject serialObject) {
        final MaterializedAssemblyShipment bean = findById(id, true);

        bean.setSerialObject(serialObject);
    }

}