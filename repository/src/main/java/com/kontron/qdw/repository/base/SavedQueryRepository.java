package com.kontron.qdw.repository.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class SavedQueryRepository extends AbstractRepository<SavedQuery, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent saved query by using the primary key of the provided object
     * @param savedQuery
     * @return the saved query or null if the object could not be found
     */
    @Generated
    public SavedQuery findById(SavedQuery savedQuery) {
        return findById(savedQuery.getId());
    }

    /**
     * Create a deep copy of the given saved query
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new saved query
     */
    @Generated
    public SavedQuery copy(SavedQuery sourceObject, SavedQuery targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new SavedQuery();
        }

        targetObject.setTitle(sourceObject.getTitle());
        targetObject.setViewName(sourceObject.getViewName());
        targetObject.setDataObj(sourceObject.getDataObj());
        targetObject.setUser(getReference(User.class, loggedOnUserId));

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
     * Get the user of this saved query
     * @param id
     * @return the user of this saved query, or null if it could not be found
     */
    @Generated
    public User getUser(long id) {
        final TypedQuery<User> query = em.createNamedQuery(SavedQuery.NQ_GET_USER, User.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'user' attribute of this saved query
     * @param id
     * @param user
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setUser(long id, User user) {
        final SavedQuery bean = findById(id, true);

        bean.setUser(user);
    }

}