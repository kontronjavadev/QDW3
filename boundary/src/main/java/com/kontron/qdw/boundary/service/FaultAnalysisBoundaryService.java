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
public class FaultAnalysisBoundaryService {
    @Generated
    private final FaultAnalysisRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public FaultAnalysisBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public FaultAnalysisBoundaryService(FaultAnalysisRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for fault analysis objects
     * @param filter
     * @return a list of fault analysis objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<FaultAnalysisListDTO> findFaultAnalyses(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(FaultAnalysisListDTO.SELECT_CODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from FaultAnalysis a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(FaultAnalysisListDTO.SELECT_CODE, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, FaultAnalysisListDTO.class, selectTokens);
    }

}