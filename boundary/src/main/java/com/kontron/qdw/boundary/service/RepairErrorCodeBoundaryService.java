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
public class RepairErrorCodeBoundaryService {
    @Generated
    private final RepairErrorCodeRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public RepairErrorCodeBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public RepairErrorCodeBoundaryService(RepairErrorCodeRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for repair error code objects
     * @param filter
     * @return a list of repair error code objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<RepairErrorCodeListDTO> findRepairErrorCodes(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(RepairErrorCodeListDTO.SELECT_CODE);
        selectTokens.add(RepairErrorCodeListDTO.SELECT_NAME);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from RepairErrorCode a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(RepairErrorCodeListDTO.SELECT_NAME, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, RepairErrorCodeListDTO.class, selectTokens);
    }

    /**
     * Find repair error code by its ID
     * @param id
     * @return the repair error code object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public RepairErrorCodeListDTO findListRepairErrorCode(String id) {
        // Find persistent object
        final RepairErrorCode repairErrorCode = repository.findById(id, true);

        final var dto = new RepairErrorCodeListDTO();
        dto.setCode(repairErrorCode.getCode());
        dto.setName(repairErrorCode.getName());

        return dto;
    }

}