package com.kontron.qdw.boundary.base;

import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.repository.base.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.repository.*;
import com.kontron.qdw.dto.base.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class PasswordResetBoundaryService {
    @Generated
    private final PasswordResetRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public PasswordResetBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public PasswordResetBoundaryService(PasswordResetRepository repository) {
        this.repository = repository;
    }

    /**
     * @param uuid
     * @return the password reset or null if it could not be found
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PasswordResetDTO findByUuid(UUID uuid) {
        // Find persistent object
        final PasswordReset passwordReset = repository.findByUuid(uuid);

        if (passwordReset == null)
            return null;

        final var dto = new PasswordResetDTO();
        dto.setUuid(passwordReset.getUuid());
        dto.setId(passwordReset.getId());
        dto.setVersion(passwordReset.getVersion());
        dto.setCreationDate(passwordReset.getCreationDate());
        dto.setLastUpdate(passwordReset.getLastUpdate());
        dto.setUserId(passwordReset.getUser().getId());
        dto.setUserName(passwordReset.getUser().getName());

        return dto;
    }

    /**
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void delete(long id) {
        repository.delete(id);
    }

    /**
     * Save the given password reset
     * @param passwordReset the password reset to be saved
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the saved password reset object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PasswordResetDTO savePasswordReset(PasswordResetDTO passwordReset) {
        boolean createNew = true;
        PasswordReset passwordResetToSave = null;

        if (passwordReset.getId() != 0) {
            // Find and attach the persistent object
            passwordResetToSave = repository.findById(passwordReset.getId());
            createNew = passwordResetToSave == null;
        }

        if (createNew)
            passwordResetToSave = new PasswordReset();

        passwordResetToSave.setUuid(passwordReset.getUuid());
        passwordResetToSave.setVersion(passwordReset.getVersion());
        passwordResetToSave.getUser().setId(passwordReset.getUserId());
        passwordResetToSave.getUser().setName(passwordReset.getUserName() != null ? passwordReset.getUserName().trim() : null);

        if (createNew) {
            // Persist a new object
            passwordResetToSave = repository.persist(passwordResetToSave, true, true, true);

            passwordReset.setVersion(passwordResetToSave.getVersion());
            passwordReset.setCreationDate(passwordResetToSave.getCreationDate());
            passwordReset.setId(passwordResetToSave.getId());
        }
        else {
            // Merge the existing object
            passwordResetToSave = repository.merge(passwordResetToSave, true, true);

            passwordReset.setVersion(passwordResetToSave.getVersion());
            passwordReset.setLastUpdate(passwordResetToSave.getLastUpdate());
        }

        return passwordReset;
    }

}