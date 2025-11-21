package com.kontron.qdw.repository.base;

import java.util.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class PasswordResetRepository extends AbstractRepository<PasswordReset, Long> {
    @Generated
    private static final String PARAM_USER = "user";
    @Generated
    private static final String PARAM_ID = "id";
    @Generated
    private static final String PARAM_UUID = "uuid";

    /**
     * Find a persistent password reset by using the primary key of the provided object
     * @param passwordReset
     * @return the password reset or null if the object could not be found
     */
    @Generated
    public PasswordReset findById(PasswordReset passwordReset) {
        return findById(passwordReset.getId());
    }

    /**
     * Merge the password reset object
     * @param passwordReset
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged password reset object
     */
    @Generated
    public PasswordReset merge(PasswordReset passwordReset, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByIdAndUserId(passwordReset.getId(), passwordReset.getUser()))
            throw new UniqueConstraintViolationException("Password reset with user '" + passwordReset.getUser().getId() + "' already exists!");

        if (performChecks && existsByIdAndUuid(passwordReset.getId(), passwordReset.getUuid()))
            throw new UniqueConstraintViolationException("Password reset with uuid '" + passwordReset.getUuid() + "' already exists!");

        return merge(passwordReset, performFlush);
    }

    /**
     * Persist the password reset object
     * @param passwordReset
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted password reset object
     */
    @Generated
    public PasswordReset persist(PasswordReset passwordReset, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByUuid(passwordReset.getUuid()))
            throw new UniqueConstraintViolationException("Password reset with uuid '" + passwordReset.getUuid() + "' already exists!");

        return persist(passwordReset, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given password reset
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new password reset
     */
    @Generated
    public PasswordReset copy(PasswordReset sourceObject, PasswordReset targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new PasswordReset();
        }

        targetObject.setUuid(sourceObject.getUuid());
        targetObject.setUser(sourceObject.getUser());

        targetObject = persist(targetObject, true, false, false);

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Check if the given password reset already exists
     * @param user
     * @return true if the password reset already exists
     */
    @Generated
    public boolean existsByUserId(User user) {
        if (user == null)
            throw new IllegalArgumentException("Parameter \"user\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(PasswordReset.NQ_UK_EXISTS_BY_USER, Long.class);
        query.setParameter(PARAM_USER, user.getId());

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given password reset already exists
     * @param id
     * @param user
     * @return true if the password reset already exists
     */
    @Generated
    public boolean existsByIdAndUserId(long id, User user) {
        if (user == null)
            throw new IllegalArgumentException("Parameter \"user\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(PasswordReset.NQ_UK_EXISTS_BY_USER_AND_ID, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_ID, id);
        query.setParameter(PARAM_USER, user.getId());

        return query.getSingleResult() != 0;
    }

    /**
     * Find a persistent password reset object by using the provided parameters
     * @param user
     * @return the password reset object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public PasswordReset findByUserId(User user) {
        final TypedQuery<PasswordReset> query = em.createNamedQuery(PasswordReset.NQ_UK_FIND_BY_USER, PasswordReset.class);
        query.setParameter(PARAM_USER, user.getId());

        final List<PasswordReset> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Check if the given password reset already exists
     * @param uuid
     * @return true if the password reset already exists
     */
    @Generated
    public boolean existsByUuid(UUID uuid) {
        if (uuid == null)
            throw new IllegalArgumentException("Parameter \"uuid\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(PasswordReset.NQ_UK_EXISTS_BY_UUID, Long.class);
        query.setParameter(PARAM_UUID, uuid);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given password reset already exists
     * @param id
     * @param uuid
     * @return true if the password reset already exists
     */
    @Generated
    public boolean existsByIdAndUuid(long id, UUID uuid) {
        if (uuid == null)
            throw new IllegalArgumentException("Parameter \"uuid\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(PasswordReset.NQ_UK_EXISTS_BY_UUID_AND_ID, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_ID, id);
        query.setParameter(PARAM_UUID, uuid);

        return query.getSingleResult() != 0;
    }

    /**
     * Find a persistent password reset object by using the provided parameters
     * @param uuid
     * @return the password reset object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public PasswordReset findByUuid(UUID uuid) {
        final TypedQuery<PasswordReset> query = em.createNamedQuery(PasswordReset.NQ_UK_FIND_BY_UUID, PasswordReset.class);
        query.setParameter(PARAM_UUID, uuid);

        final List<PasswordReset> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Get the user of this password reset
     * @param id
     * @return the user of this password reset, or null if it could not be found
     */
    @Generated
    public User getUser(long id) {
        final TypedQuery<User> query = em.createNamedQuery(PasswordReset.NQ_GET_USER, User.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList().stream().findFirst().orElse(null);
    }

}