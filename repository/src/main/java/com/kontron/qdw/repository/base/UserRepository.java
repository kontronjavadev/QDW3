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
public class UserRepository extends AbstractRepository<User, Long> {
    @Generated
    private static final String PARAM_NAME = "name";
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent user by using the primary key of the provided object
     * @param user
     * @return the user or null if the object could not be found
     */
    @Generated
    public User findById(User user) {
        return findById(user.getId());
    }

    /**
     * Merge the user object
     * @param user
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged user object
     */
    @Generated
    public User merge(User user, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByIdAndName(user.getId(), user.getName()))
            throw new UniqueConstraintViolationException("User with name '" + user.getName() + "' already exists!");

        return merge(user, performFlush);
    }

    /**
     * Persist the user object
     * @param user
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted user object
     */
    @Generated
    public User persist(User user, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByName(user.getName()))
            throw new UniqueConstraintViolationException("User with name '" + user.getName() + "' already exists!");

        return persist(user, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given user
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new user
     */
    @Generated
    public User copy(User sourceObject, User targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new User();
        }

        targetObject.setName("N/A");
        targetObject.setPassword(sourceObject.getPassword());
        targetObject.setEmail(sourceObject.getEmail());
        targetObject.setActive(sourceObject.isActive());

        targetObject = persist(targetObject, true, false, false);

        for (final Role role : sourceObject.getRoles())
            targetObject.getRoles().add(role);

        if (flushAndRefresh) {
            // Call the flush() method in order to force the database insert immediately!
            em.flush();

            // Get a fully attached version of the entity
            em.refresh(targetObject);
        }

        return targetObject;
    }

    /**
     * Check if the given user already exists
     * @param name
     * @return true if the user already exists
     */
    @Generated
    public boolean existsByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(User.NQ_UK_EXISTS_BY_NAME, Long.class);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given user already exists
     * @param id
     * @param name
     * @return true if the user already exists
     */
    @Generated
    public boolean existsByIdAndName(long id, String name) {
        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(User.NQ_UK_EXISTS_BY_NAME_AND_ID, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_ID, id);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for user objects by using the provided parameters
     * @param name
     * @return a list that contains all user objects that match the provided filter criteria
     */
    @Generated
    public List<User> searchByName(String name) {
        final TypedQuery<User> query = em.createNamedQuery(User.NQ_UK_SEARCH_BY_NAME, User.class);
        query.setParameter(PARAM_NAME, name);

        return query.getResultList();
    }

    /**
     * Find a persistent user object by using the provided parameters
     * @param name
     * @return the user object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public User findByName(String name) {
        final TypedQuery<User> query = em.createNamedQuery(User.NQ_UK_FIND_BY_NAME, User.class);
        query.setParameter(PARAM_NAME, name);

        final List<User> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

    /**
     * Get all roles of this user
     * @param id
     * @return a list of roles of this user
     */
    @Generated
    public List<Role> getRoles(long id) {
        final TypedQuery<Role> query = em.createNamedQuery(User.NQ_GET_ROLES, Role.class);
        query.setParameter(PARAM_ID, id);

        return query.getResultList();
    }

    /**
     * Remove the persistent role object from the corresponding list of this user
     * @param id
     * @param role
     */
    @Generated
    public void removeRoleFromRoles(long id, Role role) {
        final User bean = findById(id, true);

        for (final Role item : bean.getRoles())
            if (role.getId() == item.getId()) {
                bean.getRoles().remove(item);
                return;
            }
    }

    /**
     * Add the persistent role object to the corresponding list of this user
     * @param id
     * @param role
     * @throws DuplicateCollectionEntryException if the caller tries to add an element to the collection twice
     */
    @Generated
    public void addRoleToRoles(long id, Role role) {
        final User bean = findById(id, true);

        // Prevent duplicate entries
        for (final Role item : bean.getRoles())
            if (role.getId() == item.getId())
                throw new DuplicateCollectionEntryException("Entry already exists in this collection!");

        bean.getRoles().add(role);
    }

}