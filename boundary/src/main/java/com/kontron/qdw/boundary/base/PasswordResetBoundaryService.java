package com.kontron.qdw.boundary.base;

import java.security.NoSuchAlgorithmException;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.repository.base.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.repository.*;
import com.kontron.qdw.dto.base.*;

import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import net.sourceforge.jbizmo.commons.crypto.HashGenerator;

import com.kontron.qdw.boundary.util.Constants;
import com.kontron.qdw.boundary.util.MailServiceFacade;
import com.kontron.qdw.domain.base.*;

@Stateless
public class PasswordResetBoundaryService {
    @Generated
    private final PasswordResetRepository repository;
    private final UserRepository userRepository;

    /**
     * Default constructor
     */
    @Generated
    public PasswordResetBoundaryService() {
        repository = null;
        userRepository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public PasswordResetBoundaryService(PasswordResetRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    /**
     * Change password of given user account
     * @throws IllegalStateException if the password encryption algorithm does not exist
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void changePassword(PasswordResetDTO passwordReset, String newPassword) {
        // Find persistent object
        final User user = userRepository.findById(passwordReset.getUserId(), true);

        try {
            user.setPassword(HashGenerator.encryptSHA256(newPassword));
            delete(passwordReset.getId());
        }
        catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Speichert das PasswordReset-Objekt mit dem Benutzer und einer uuid und schickt dem Anwender eine Benachrichtigung.
     * @param passwordReset the password reset to be saved
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the saved password reset object
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PasswordResetDTO savePasswordResetAndMail(PasswordResetDTO passwordReset) {
        passwordReset = savePasswordReset(passwordReset);

        User user = userRepository.findById(passwordReset.getUserId(), true);

        MailServiceFacade.sendMail(user.getEmail(), String.format("%s: %s", Constants.APP_ENV, "Password reset information"),
                String.format("Hello %s,\n"
                        + "\n"
                        + "Use this link to reset your password:\n"
                        + "%s\n",
                        user.getName(),
                        Constants.APP_SERVER + "PasswordReset.jsf?uuid=" + passwordReset.getUuid().toString()));

        return passwordReset;
    }

    /**
     * Save the given password reset
     * @param passwordReset the password reset to be saved
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the saved password reset object
     */
    @Customized
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public PasswordResetDTO savePasswordReset(PasswordResetDTO passwordReset) {
        boolean createNew = true;
        PasswordReset passwordResetToSave = null;
        User user = userRepository.findById(passwordReset.getUserId(), true);

        if (passwordReset.getId() != 0) {
            // Find and attach the persistent object
            passwordResetToSave = repository.findById(passwordReset.getId());
            createNew = passwordResetToSave == null;
        }
        else {
            // Find and attach the persistent object
            passwordResetToSave = repository.findByUserId(user);
            createNew = passwordResetToSave == null;
        }

        if (createNew) {
            passwordResetToSave = new PasswordReset();
        }


        passwordResetToSave.setUuid(passwordReset.getUuid());
        passwordResetToSave.setUser(user);

        if (createNew) {
            // Persist a new object
            passwordResetToSave = repository.persist(passwordResetToSave, true, true, true);
        }
        else {
            // Merge the existing object
            passwordResetToSave = repository.merge(passwordResetToSave, true, true);
            passwordReset.setVersion(passwordResetToSave.getVersion());
            passwordReset.setLastUpdate(passwordResetToSave.getLastUpdate());
        }

        return passwordReset;
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

        if (passwordReset == null) {
            return null;
        }

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

}
