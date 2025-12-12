package com.kontron.qdw.repository.base;

import java.util.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class NotificationRepository extends AbstractRepository<Notification, Long> {
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent notification by using the primary key of the provided object
     * @param notification
     * @return the notification or null if the object could not be found
     */
    @Generated
    public Notification findById(Notification notification) {
        return findById(notification.getId());
    }

    /**
     * Create a deep copy of the given notification
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new notification
     */
    @Customized
    public Notification copy(Notification sourceObject, Notification targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new Notification();
        }

        targetObject.setHeader(sourceObject.getHeader());
        targetObject.setNotification(sourceObject.getNotification());
        targetObject.setNotificationStart(sourceObject.getNotificationStart());
        targetObject.setNotificationEnd(sourceObject.getNotificationEnd());
        targetObject.setInitiator(getReference(User.class, loggedOnUserId));

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
     * Get the user of this notification
     * @param id
     * @return the user of this notification, or null if it could not be found
     */
    @Generated
    public User getInitiator(long id) {
        final TypedQuery<User> query = em.createNamedQuery(Notification.NQ_GET_INITIATOR, User.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Get all users of this notification
     * @param id
     * @return a list of users of this notification
     */
    @Generated
    public List<User> getGelesen(long id) {
        final TypedQuery<User> query = em.createNamedQuery(Notification.NQ_GET_GELESEN, User.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Change the 'initiator' attribute of this notification
     * @param id
     * @param initiator
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setInitiator(long id, User initiator) {
        final Notification bean = findById(id, true);

        bean.setInitiator(initiator);
    }

    /**
     * Remove the persistent user object from the corresponding list of this notification
     * @param id
     * @param user
     */
    @Generated
    public void removeUserFromGelesen(long id, User user) {
        final Notification bean = findById(id, true);

        for (final User item : bean.getGelesen())
            if (user.getId() == item.getId()) {
                bean.getGelesen().remove(item);
                return;
            }
    }

    /**
     * Add the persistent user object to the corresponding list of this notification
     * @param id
     * @param user
     * @throws DuplicateCollectionEntryException if the caller tries to add an element to the collection twice
     */
    @Generated
    public void addUserToGelesen(long id, User user) {
        final Notification bean = findById(id, true);

        // Prevent duplicate entries
        for (final User item : bean.getGelesen())
            if (user.getId() == item.getId())
                throw new DuplicateCollectionEntryException("Entry already exists in this collection!");

        bean.getGelesen().add(user);
    }

}