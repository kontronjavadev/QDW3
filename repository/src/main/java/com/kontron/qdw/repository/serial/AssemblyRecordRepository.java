package com.kontron.qdw.repository.serial;

import com.kontron.qdw.domain.serial.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class AssemblyRecordRepository extends AbstractRepository<AssemblyRecord, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent assembly record by using the primary key of the provided object
     * @param assemblyRecord
     * @return the assembly record or null if the object could not be found
     */
    @Generated
    public AssemblyRecord findById(AssemblyRecord assemblyRecord) {
        return findById(assemblyRecord.getId());
    }

    /**
     * Create a deep copy of the given assembly record
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new assembly record
     */
    @Generated
    public AssemblyRecord copy(AssemblyRecord sourceObject, AssemblyRecord targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new AssemblyRecord();
            targetObject.setParentSerialObject(sourceObject.getParentSerialObject());
        }

        targetObject.setAssemblyDate(sourceObject.getAssemblyDate());
        targetObject.setProductionOrderNumber(sourceObject.getProductionOrderNumber());
        targetObject.setMaterialRevision(sourceObject.getMaterialRevision());
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
     * Get the material revision of this assembly record
     * @param id
     * @return the material revision of this assembly record, or null if it could not be found
     */
    @Generated
    public MaterialRevision getMaterialRevision(long id) {
        final TypedQuery<MaterialRevision> query = em.createNamedQuery(AssemblyRecord.NQ_GET_MATERIALREVISION, MaterialRevision.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the serial object of this assembly record
     * @param id
     * @return the serial object of this assembly record, or null if it could not be found
     */
    @Generated
    public SerialObject getParentSerialObject(long id) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(AssemblyRecord.NQ_GET_PARENTSERIALOBJECT, SerialObject.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get the serial object of this assembly record
     * @param id
     * @return the serial object of this assembly record, or null if it could not be found
     */
    @Generated
    public SerialObject getSerialObject(long id) {
        final TypedQuery<SerialObject> query = em.createNamedQuery(AssemblyRecord.NQ_GET_SERIALOBJECT, SerialObject.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'materialRevision' attribute of this assembly record
     * @param id
     * @param materialRevision
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterialRevision(long id, MaterialRevision materialRevision) {
        final AssemblyRecord bean = findById(id, true);

        bean.setMaterialRevision(materialRevision);
    }

    /**
     * Change the 'parentSerialObject' attribute of this assembly record
     * @param id
     * @param parentSerialObject
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setParentSerialObject(long id, SerialObject parentSerialObject) {
        final AssemblyRecord bean = findById(id, true);

        bean.setParentSerialObject(parentSerialObject);
    }

    /**
     * Change the 'serialObject' attribute of this assembly record
     * @param id
     * @param serialObject
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSerialObject(long id, SerialObject serialObject) {
        final AssemblyRecord bean = findById(id, true);

        bean.setSerialObject(serialObject);
    }

}