package com.kontron.qdw.boundary.base;

import java.util.*;
import java.security.NoSuchAlgorithmException;
import net.sourceforge.jbizmo.commons.crypto.*;
import com.kontron.qdw.repository.base.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import com.kontron.qdw.dto.base.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class UserBoundaryService {
    @Generated
    private final UserRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public UserBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public UserBoundaryService(UserRepository repository) {
        this.repository = repository;
    }

    /**
     * Perform user log-on
     * @param userName
     * @param password
     * @return a data transfer object containing security information
     * @throws SecurityException if login has failed!
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ApplicationLogOnDTO logOn(String userName, String password) {
        final User user = repository.findByName(userName);

        if (user == null)
            throw new SecurityException("User account does not exist!");

        if (!user.isActive())
            throw new SecurityException("Account is locked!");

        final var dto = new ApplicationLogOnDTO();


        dto.setRoles(new ArrayList<>());

        for (final Role listElement : user.getRoles()) {
            final var listDTO = new RoleListDTO();
            listDTO.setId(listElement.getId());
            listDTO.setName(listElement.getName());

            dto.getRoles().add(listDTO);
        }

        dto.setName(user.getName());
        dto.setPassword(user.getPassword());
        dto.setEmail(user.getEmail());
        dto.setActive(user.isActive());
        dto.setId(user.getId());

        return dto;
    }

    /**
     * Change password of given user account
     * @param id
     * @param oldPassword
     * @param newPassword
     * @param confirmedPassword
     * @throws IllegalArgumentException if input is invalid
     * @throws IllegalStateException if the password encryption algorithm does not exist
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void changePassword(long id, String oldPassword, String newPassword, String confirmedPassword) {
        if (!newPassword.equals(confirmedPassword))
            throw new IllegalArgumentException("New password and confirmed password are not equal!");

        // Find persistent object
        final User user = repository.findById(id, true);

        try {
            if (!user.getPassword().equals(HashGenerator.encryptSHA256(oldPassword)))
                throw new IllegalArgumentException("Password is incorrect!");

            user.setPassword(HashGenerator.encryptSHA256(newPassword));
        }
        catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }
    }

}