package com.kontron.qdw.boundary.base;

import com.kontron.qdw.boundary.util.MailServiceFacade;
import com.kontron.qdw.repository.base.*;
import com.kontron.qdw.boundary.util.Constants;
import net.sourceforge.jbizmo.commons.search.exception.*;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.domain.base.*;
import com.kontron.util.cipher.PasswordGenerator;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import java.security.NoSuchAlgorithmException;
import net.sourceforge.jbizmo.commons.crypto.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

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
     * @param accountOrEmail
     * @return the user or null if it could not be found
     */
    @Customized
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UserSearchDTO findByAccountOrEmail(String accountOrEmail) {
        // Find persistent object
        final User user;
        if (accountOrEmail.contains("@")) {
            user = repository.findActiveByEmail(accountOrEmail);
        }
        else {
            user = repository.findByName(accountOrEmail);            
        }

        if (user == null) {
            return null;
        }

        final var dto = new UserSearchDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setActive(user.isActive());
        dto.setId(user.getId());
        dto.setCreationDate(user.getCreationDate());
        dto.setLastUpdate(user.getLastUpdate());

        return dto;
    }

    /**
     * Create new user
     * @param object
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted user object
     */
    @Customized
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UserCreateDTO createUser(UserCreateDTO object) {
        // Create new object to be persisted
        var newUser = new User();
        newUser.setName(object.getName() != null ? object.getName().trim() : null);
        newUser.setEmail(object.getEmail() != null ? object.getEmail().trim() : null);
        newUser.setActive(object.isActive());
        newUser.setRoles(new ArrayList<>());

        for (final RoleListDTO a : object.getRoles()) {
            final Role b = repository.getReference(Role.class, a.getId());
            newUser.getRoles().add(b);
        }


        String pw = PasswordGenerator.createPassword();
        try {
            newUser.setPassword(HashGenerator.encryptSHA256(pw));
        }
        catch (final NoSuchAlgorithmException e) {
            throw new IllegalStateException(e);
        }

        newUser = repository.persist(newUser, true, true, true);

        object.setId(newUser.getId());
        object.setVersion(newUser.getVersion());
        object.setCreationDate(newUser.getCreationDate());

        MailServiceFacade.sendMail(newUser.getEmail(), String.format("%s: %s", Constants.APP_ENV, "your new account"),
                String.format("Your got an account for %s.\n"
                        + "name: %s\n"
                        + "password: %s\n"
                        + "Please notice that passwords are case sensitiv.\n"
                        + "Change password after first login (buttons at top bar).",
                        Constants.APP_ENV, newUser.getName(), pw));
        return object;
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

    /**
     * Search for user objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of user objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<UserSearchDTO> searchAllUsers(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(UserSearchDTO.SELECT_NAME);
        selectTokens.add(UserSearchDTO.SELECT_EMAIL);
        selectTokens.add(UserSearchDTO.SELECT_ACTIVE);
        selectTokens.add(UserSearchDTO.SELECT_ID);
        selectTokens.add(UserSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(UserSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from User a");

        return repository.search(searchObj, UserSearchDTO.class, selectTokens);
    }

    /**
     * Count user objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllUsers(SearchDTO searchObj) {
        searchObj.setFromClause("from User a");

        return repository.count(searchObj);
    }

    /**
     * Delete existing user
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteUser(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected user
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final User sourceObject = repository.findById(sourceObjectId);
        final User targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

    /**
     * Update existing user object
     * @param object the user to update
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateUser(UserUpdateDTO object) {
        // Find and attach object
        User user = repository.findById(object.getId(), true);

        user.setName(object.getName() != null ? object.getName().trim() : null);
        user.setEmail(object.getEmail() != null ? object.getEmail().trim() : null);
        user.setActive(object.isActive());
        user.setVersion(object.getVersion());
        user.setRoles(new ArrayList<>());

        for (final RoleListDTO a : object.getRoles()) {
            final Role b = repository.getReference(Role.class, a.getId());
            user.getRoles().add(b);
        }


        repository.merge(user, true, false);
    }

    /**
     * Find user by its ID
     * @param id
     * @return the user object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public UserUpdateDTO getUserForUpdate(long id) {
        // Find persistent object
        final User user = repository.findById(id, true);

        final var dto = new UserUpdateDTO();
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        dto.setActive(user.isActive());
        dto.setId(user.getId());
        dto.setVersion(user.getVersion());
        dto.setCreationDate(user.getCreationDate());
        dto.setLastUpdate(user.getLastUpdate());

        dto.setRoles(new ArrayList<>());

        for (final Role listElement : user.getRoles()) {
            final var listDTO = new RoleListDTO();
            listDTO.setId(listElement.getId());
            listDTO.setName(listElement.getName());

            dto.getRoles().add(listDTO);
        }


        return dto;
    }

}