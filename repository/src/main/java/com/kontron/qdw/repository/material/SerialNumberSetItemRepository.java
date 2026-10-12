package com.kontron.qdw.repository.material;

import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class SerialNumberSetItemRepository extends AbstractRepository<SerialNumberSetItem, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent serial number set item by using the primary key of the provided object
     * @param serialNumberSetItem
     * @return the serial number set item or null if the object could not be found
     */
    @Generated
    public SerialNumberSetItem findById(SerialNumberSetItem serialNumberSetItem) {
        return findById(serialNumberSetItem.getId());
    }

    /**
     * Create a deep copy of the given serial number set item
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new serial number set item
     */
    @Generated
    public SerialNumberSetItem copy(SerialNumberSetItem sourceObject, SerialNumberSetItem targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new SerialNumberSetItem();
            targetObject.setSerialNumberSet(sourceObject.getSerialNumberSet());
        }

        targetObject.setSerialNumber(sourceObject.getSerialNumber());

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
     * Get the serial number set of this serial number set item
     * @param id
     * @return the serial number set of this serial number set item, or null if it could not be found
     */
    @Generated
    public SerialNumberSet getSerialNumberSet(long id) {
        final TypedQuery<SerialNumberSet> query = em.createNamedQuery(SerialNumberSetItem.NQ_GET_SERIALNUMBERSET, SerialNumberSet.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'serialNumberSet' attribute of this serial number set item
     * @param id
     * @param serialNumberSet
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setSerialNumberSet(long id, SerialNumberSet serialNumberSet) {
        final SerialNumberSetItem bean = findById(id, true);

        bean.setSerialNumberSet(serialNumberSet);
    }

}