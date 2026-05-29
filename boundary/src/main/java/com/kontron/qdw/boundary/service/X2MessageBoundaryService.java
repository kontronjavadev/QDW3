package com.kontron.qdw.boundary.service;

import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.repository.service.*;
import com.kontron.qdw.domain.service.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.dto.service.*;

@Stateless
public class X2MessageBoundaryService {
    @Generated
    private final X2MessageRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public X2MessageBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public X2MessageBoundaryService(X2MessageRepository repository) {
        this.repository = repository;
    }

    /**
     * Delete existing X2 message
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteX2Message(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected X2 message
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final X2Message sourceObject = repository.findById(sourceObjectId);
        final X2Message targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}