package com.kontron.qdw.boundary.base;

import jakarta.validation.ConstraintViolationException;
import java.util.*;
import com.kontron.qdw.repository.base.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import com.kontron.qdw.dto.base.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class NotificationBoundaryService {
    @Generated
    private final NotificationRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public NotificationBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public NotificationBoundaryService(NotificationRepository repository) {
        this.repository = repository;
    }

    /**
     * Update existing notification object
     * @param object the notification to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateNotification(NotificationUpdateDTO object) {
        // Find and attach object
        Notification notification = repository.findById(object.getId(), true);

        notification.setHeader(object.getHeader() != null ? object.getHeader().trim() : null);
        notification.setNotification(object.getNotification() != null ? object.getNotification().trim() : null);
        notification.setNotificationStart(object.getNotificationStart());
        notification.setNotificationEnd(object.getNotificationEnd());
        notification.setVersion(object.getVersion());
        notification.setInitiator(repository.getReference(User.class, object.getInitiator().getId()));

        repository.merge(notification, false);
    }

    /**
     * Find notification by its ID
     * @param id
     * @return the notification object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public NotificationUpdateDTO getNotificationForUpdate(long id) {
        // Find persistent object
        final Notification notification = repository.findById(id, true);

        final var dto = new NotificationUpdateDTO();
        dto.setHeader(notification.getHeader());
        dto.setNotification(notification.getNotification());
        dto.setNotificationStart(notification.getNotificationStart());
        dto.setNotificationEnd(notification.getNotificationEnd());
        dto.setId(notification.getId());
        dto.setVersion(notification.getVersion());
        dto.setCreationDate(notification.getCreationDate());
        dto.setLastUpdate(notification.getLastUpdate());
        dto.setInitiator(new UserListDTO());
        dto.getInitiator().setId(notification.getInitiator().getId());
        dto.getInitiator().setName(notification.getInitiator().getName());

        return dto;
    }

    /**
     * Create new notification
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted notification object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public NotificationCreateDTO createNotification(NotificationCreateDTO object) {
        // Create new object to be persisted
        var newNotification = new Notification();
        newNotification.setHeader(object.getHeader() != null ? object.getHeader().trim() : null);
        newNotification.setNotification(object.getNotification() != null ? object.getNotification().trim() : null);
        newNotification.setNotificationStart(object.getNotificationStart());
        newNotification.setNotificationEnd(object.getNotificationEnd());
        newNotification.setInitiator(repository.getReference(User.class, object.getInitiator().getId()));

        newNotification = repository.persist(newNotification, true, true);

        object.setId(newNotification.getId());
        object.setVersion(newNotification.getVersion());
        object.setCreationDate(newNotification.getCreationDate());

        return object;
    }

    /**
     * Search for notification objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of notification objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<NotificationSearchDTO> searchAllNotifications(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(NotificationSearchDTO.SELECT_HEADER);
        selectTokens.add(NotificationSearchDTO.SELECT_NOTIFICATION);
        selectTokens.add(NotificationSearchDTO.SELECT_NOTIFICATIONSTART);
        selectTokens.add(NotificationSearchDTO.SELECT_NOTIFICATIONEND);
        selectTokens.add(NotificationSearchDTO.SELECT_ID);
        selectTokens.add(NotificationSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(NotificationSearchDTO.SELECT_INITIATORNAME);
        selectTokens.add(NotificationSearchDTO.SELECT_INITIATORID);

        searchObj.setFromClause("from Notification a join a.initiator b");

        return repository.search(searchObj, NotificationSearchDTO.class, selectTokens);
    }

    /**
     * Count notification objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllNotifications(SearchDTO searchObj) {
        searchObj.setFromClause("from Notification a join a.initiator b");

        return repository.count(searchObj);
    }

    /**
     * Delete existing notification
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteNotification(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected notification
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final Notification sourceObject = repository.findById(sourceObjectId);
        final Notification targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}