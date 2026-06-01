package com.kontron.qdw.repository.material;

import java.util.*;
import com.kontron.qdw.domain.material.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class EWSEntryRepository extends AbstractRepository<EWSEntry, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent EWS entry by using the primary key of the provided object
     * @param eWSEntry
     * @return the EWS entry or null if the object could not be found
     */
    @Generated
    public EWSEntry findById(EWSEntry eWSEntry) {
        return findById(eWSEntry.getId());
    }

    /**
     * Create a deep copy of the given EWS entry
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new EWS entry
     */
    @Generated
    public EWSEntry copy(EWSEntry sourceObject, EWSEntry targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new EWSEntry();
        }

        targetObject.setThreshold(sourceObject.getThreshold());
        targetObject.setBoardOrSystem(sourceObject.isBoardOrSystem());
        targetObject.setFilterCriterion(sourceObject.getFilterCriterion());
        targetObject.setType(sourceObject.getType());
        targetObject.setMaterial(sourceObject.getMaterial());

        targetObject = persist(targetObject, false, false);

        for (final User user : sourceObject.getReceivers())
            targetObject.getReceivers().add(user);

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Get the material of this EWS entry
     * @param id
     * @return the material of this EWS entry, or null if it could not be found
     */
    @Generated
    public Material getMaterial(long id) {
        final TypedQuery<Material> query = em.createNamedQuery(EWSEntry.NQ_GET_MATERIAL, Material.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all users of this EWS entry
     * @param id
     * @return a list of users of this EWS entry
     */
    @Generated
    public List<User> getReceivers(long id) {
        final TypedQuery<User> query = em.createNamedQuery(EWSEntry.NQ_GET_RECEIVERS, User.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Change the 'material' attribute of this EWS entry
     * @param id
     * @param material
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMaterial(long id, Material material) {
        final EWSEntry bean = findById(id, true);

        bean.setMaterial(material);
    }

    /**
     * Remove the persistent user object from the corresponding list of this EWS entry
     * @param id
     * @param user
     */
    @Generated
    public void removeUserFromReceivers(long id, User user) {
        final EWSEntry bean = findById(id, true);

        for (final User item : bean.getReceivers())
            if (user.getId() == item.getId()) {
                bean.getReceivers().remove(item);
                return;
            }
    }

    /**
     * Add the persistent user object to the corresponding list of this EWS entry
     * @param id
     * @param user
     * @throws DuplicateCollectionEntryException if the caller tries to add an element to the collection twice
     */
    @Generated
    public void addUserToReceivers(long id, User user) {
        final EWSEntry bean = findById(id, true);

        // Prevent duplicate entries
        for (final User item : bean.getReceivers())
            if (user.getId() == item.getId())
                throw new DuplicateCollectionEntryException("Entry already exists in this collection!");

        bean.getReceivers().add(user);
    }

}