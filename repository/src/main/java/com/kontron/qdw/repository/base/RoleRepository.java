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
public class RoleRepository extends AbstractRepository<Role, Long> {
    @Generated
    private static final String PARAM_NAME = "name";
    @Generated
    private static final String PARAM_ID = "id";

    /**
     * Find a persistent role by using the primary key of the provided object
     * @param role
     * @return the role or null if the object could not be found
     */
    @Generated
    public Role findById(Role role) {
        return findById(role.getId());
    }

    /**
     * Merge the role object
     * @param role
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the merged role object
     */
    @Generated
    public Role merge(Role role, boolean performChecks, boolean performFlush) {
        // Perform unique key checks
        if (performChecks && existsByIdAndName(role.getId(), role.getName()))
            throw new UniqueConstraintViolationException("Role with name '" + role.getName() + "' already exists!");

        return merge(role, performFlush);
    }

    /**
     * Persist the role object
     * @param role
     * @param performChecks flag that controls if internal data integrity checks should be performed
     * @param performFlush flag that controls if the database synchronization should be performed immediately
     * @param performRefresh flag that controls if a refresh operation should be performed after persist
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the persisted role object
     */
    @Generated
    public Role persist(Role role, boolean performChecks, boolean performFlush, boolean performRefresh) {
        // Perform unique key checks
        if (performChecks && existsByName(role.getName()))
            throw new UniqueConstraintViolationException("Role with name '" + role.getName() + "' already exists!");

        return persist(role, performFlush, performRefresh);
    }

    /**
     * Create a deep copy of the given role
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new role
     */
    @Generated
    public Role copy(Role sourceObject, Role targetObject, long loggedOnUserId) {
        boolean flushAndRefresh = false;

        if (targetObject == null) {
            flushAndRefresh = true;

            targetObject = new Role();
        }

        targetObject.setName("N/A");

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
     * Check if the given role already exists
     * @param name
     * @return true if the role already exists
     */
    @Generated
    public boolean existsByName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Role.NQ_UK_EXISTS_BY_NAME, Long.class);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Check if the given role already exists
     * @param id
     * @param name
     * @return true if the role already exists
     */
    @Generated
    public boolean existsByIdAndName(long id, String name) {
        if (name == null)
            throw new IllegalArgumentException("Parameter \"name\" must not be null!");

        final TypedQuery<Long> query = em.createNamedQuery(Role.NQ_UK_EXISTS_BY_NAME_AND_ID, Long.class);
        query.setFlushMode(FlushModeType.COMMIT);
        query.setParameter(PARAM_ID, id);
        query.setParameter(PARAM_NAME, name);

        return query.getSingleResult() != 0;
    }

    /**
     * Search for role objects by using the provided parameters
     * @param name
     * @return a list that contains all role objects that match the provided filter criteria
     */
    @Generated
    public List<Role> searchByName(String name) {
        final TypedQuery<Role> query = em.createNamedQuery(Role.NQ_UK_SEARCH_BY_NAME, Role.class);
        query.setParameter(PARAM_NAME, name);

        return query.getResultList();
    }

    /**
     * Find a persistent role object by using the provided parameters
     * @param name
     * @return the role object or null if it could not be found
     * @throws IllegalStateException if the query returned more than one object
     */
    @Generated
    public Role findByName(String name) {
        final TypedQuery<Role> query = em.createNamedQuery(Role.NQ_UK_FIND_BY_NAME, Role.class);
        query.setParameter(PARAM_NAME, name);

        final List<Role> resultList = query.getResultList();

        if (resultList.size() <= 1)
            return resultList.stream().findFirst().orElse(null);

        throw new IllegalStateException("Non unique result!");
    }

}