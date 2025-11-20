package com.kontron.qdw.repository.base;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class UserPropertyRepository extends AbstractRepository<UserProperty, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent user property by using the primary key of the provided object
     * @param userProperty
     * @return the user property or null if the object could not be found
     */
    @Generated
    public UserProperty findById(UserProperty userProperty) {
        return findById(userProperty.getId());
    }

    /**
     * Create a deep copy of the given user property
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new user property
     */
    @Generated
    public UserProperty copy(UserProperty sourceObject, UserProperty targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new UserProperty();
        }

        targetObject.setPropKey(sourceObject.getPropKey());
        targetObject.setViewName(sourceObject.getViewName());
        targetObject.setValue(sourceObject.getValue());
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
     * Get the user of this user property
     * @param id
     * @return the user of this user property, or null if it could not be found
     */
    @Generated
    public User getUser(long id) {
        final TypedQuery<User> query = em.createNamedQuery(UserProperty.NQ_GET_USER, User.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'user' attribute of this user property
     * @param id
     * @param user
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setUser(long id, User user) {
        final UserProperty bean = findById(id, true);

        bean.setUser(user);
    }

}