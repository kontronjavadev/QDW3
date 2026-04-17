package com.kontron.qdw.boundary.service;

import java.util.*;
import com.kontron.qdw.repository.service.*;
import com.kontron.qdw.domain.service.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.dto.service.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class RepairStateBoundaryService {
    @Generated
    private final RepairStateRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public RepairStateBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public RepairStateBoundaryService(RepairStateRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for repair state objects
     * @param filter
     * @return a list of repair state objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairStateListDTO> findRepairStates(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairStateListDTO.SELECT_CODE);
        selectTokens.add(RepairStateListDTO.SELECT_NAME);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from RepairState a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(RepairStateListDTO.SELECT_NAME, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, RepairStateListDTO.class, selectTokens);
    }

    /**
     * Find repair state by its ID
     * @param id
     * @return the repair state object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairStateListDTO findListRepairState(String id) {
        // Find persistent object
        final RepairState repairState = repository.findById(id, true);

        final var dto = new RepairStateListDTO();
        dto.setCode(repairState.getCode());
        dto.setName(repairState.getName());

        return dto;
    }

}