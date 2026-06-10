package com.kontron.qdw.boundary.serial;

import com.kontron.qdw.domain.serial.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.dto.serial.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import com.kontron.qdw.repository.serial.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class AssemblyRecordBoundaryService {
    @Generated
    private final AssemblyRecordRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public AssemblyRecordBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public AssemblyRecordBoundaryService(AssemblyRecordRepository repository) {
        this.repository = repository;
    }

    /**
     * Delete existing assembly record
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteAssemblyRecord(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected assembly record
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final AssemblyRecord sourceObject = repository.findById(sourceObjectId);
        final AssemblyRecord targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}