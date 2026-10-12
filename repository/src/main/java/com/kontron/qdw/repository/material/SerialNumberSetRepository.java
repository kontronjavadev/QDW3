package com.kontron.qdw.repository.material;

import net.sourceforge.jbizmo.commons.random.*;
import java.util.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class SerialNumberSetRepository extends AbstractRepository<SerialNumberSet, String> {
    @Generated
    private static final String PARAM_CODE = "code";
    @Generated
    private final SerialNumberSetItemRepository serialNumberSetItemManager;

    /**
     * Default constructor
     */
    @Generated
    public SerialNumberSetRepository() {
        this.serialNumberSetItemManager = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param serialNumberSetItemManager
     */
    @Inject
    @Generated
    public SerialNumberSetRepository(SerialNumberSetItemRepository serialNumberSetItemManager) {
        this.serialNumberSetItemManager = serialNumberSetItemManager;
    }

    /**
     * Find a persistent serial number set by using the primary key of the provided object
     * @param serialNumberSet
     * @return the serial number set or null if the object could not be found
     */
    @Generated
    public SerialNumberSet findById(SerialNumberSet serialNumberSet) {
        return findById(serialNumberSet.getCode());
    }

    /**
     * Create a deep copy of the given serial number set
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new serial number set
     */
    @Generated
    public SerialNumberSet copy(SerialNumberSet sourceObject, SerialNumberSet targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new SerialNumberSet();
        }

        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());

        targetObject = persist(targetObject, false, false);

        for (final SerialNumberSetItem serialNumberSetItem : sourceObject.getSerialNumbers()) {
            var newSerialNumberSetItem = new SerialNumberSetItem();
            newSerialNumberSetItem.setSerialNumberSet(targetObject);

            newSerialNumberSetItem = serialNumberSetItemManager.copy(serialNumberSetItem, newSerialNumberSetItem, loggedOnUserId);
            targetObject.getSerialNumbers().add(newSerialNumberSetItem);
        }

        return targetObject;
    }

    /**
     * Get all serial number set items of this serial number set
     * @param code
     * @return a list of serial number set items of this serial number set
     */
    @Generated
    public List<SerialNumberSetItem> getSerialNumbers(String code) {
        final TypedQuery<SerialNumberSetItem> query = em.createNamedQuery(SerialNumberSet.NQ_GET_SERIALNUMBERS, SerialNumberSetItem.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList();
    }

}