package com.kontron.qdw.boundary.service;

import java.util.*;
import com.kontron.qdw.repository.service.*;
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
public class RepairTaskBoundaryService {
    @Generated
    private final RepairTaskRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public RepairTaskBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public RepairTaskBoundaryService(RepairTaskRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for repair task objects
     * @param filter
     * @return a list of repair task objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairTaskListDTO> findRepairTasks(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairTaskListDTO.SELECT_CODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from RepairTask a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(RepairTaskListDTO.SELECT_CODE, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, RepairTaskListDTO.class, selectTokens);
    }

}