package com.kontron.qdw.boundary.base;

import com.kontron.qdw.repository.base.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.domain.base.*;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import jakarta.ejb.*;
import jakarta.inject.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class RoleBoundaryService {
    @Generated
    private final RoleRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public RoleBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public RoleBoundaryService(RoleRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for role objects
     * @param filter
     * @return a list of role objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RoleListDTO> findRoles(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RoleListDTO.SELECT_ID);
        selectTokens.add(RoleListDTO.SELECT_NAME);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from Role a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(RoleListDTO.SELECT_NAME, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, RoleListDTO.class, selectTokens);
    }

    /**
     * Find role by its ID
     * @param id
     * @return the role object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RoleListDTO findListRole(long id) {
        // Find persistent object
        final Role role = repository.findById(id, true);

        final var dto = new RoleListDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());

        return dto;
    }

    /**
     * Search for role objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of role objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RoleSearchDTO> searchAllRoles(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RoleSearchDTO.SELECT_NAME);
        selectTokens.add(RoleSearchDTO.SELECT_ID);

        searchObj.setFromClause("from Role a");

        return repository.search(searchObj, RoleSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing role
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteRole(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected role
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
        final Role sourceObject = repository.findById(sourceObjectId);
        final Role targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

    /**
     * Create new role
     * @param object
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted role object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RoleCreateDTO createRole(RoleCreateDTO object) {
        // Create new object to be persisted
        var newRole = new Role();
        newRole.setName(object.getName() != null ? object.getName().trim() : null);

        newRole = repository.persist(newRole, true, true, true);

        object.setId(newRole.getId());
        object.setVersion(newRole.getVersion());
        object.setCreationDate(newRole.getCreationDate());

        return object;
    }

    /**
     * Find role by its ID
     * @param id
     * @return the role object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RoleDTO findRoleById(long id) {
        // Find persistent object
        final Role role = repository.findById(id, true);

        final var dto = new RoleDTO();
        dto.setId(role.getId());
        dto.setName(role.getName());

        return dto;
    }

}