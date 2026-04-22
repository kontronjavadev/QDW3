package com.kontron.qdw.boundary.service;

import com.kontron.qdw.domain.service.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import com.kontron.qdw.dto.service.*;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.repository.service.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
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

    /**
     * Find fault analysis by its ID
     * @param code
     * @return the fault analysis object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public FaultAnalysisDTO findFaultAnalysisById(String code) {
        // Find persistent object
        final FaultAnalysis faultAnalysis = repository.findById(code, true);

        final var dto = new FaultAnalysisDTO();
        dto.setCode(faultAnalysis.getCode());
        dto.setShortText(faultAnalysis.getShortText());
        dto.setComment(faultAnalysis.getComment());
        dto.setCreationDate(faultAnalysis.getCreationDate());
        dto.setLastUpdate(faultAnalysis.getLastUpdate());
        dto.setVersion(faultAnalysis.getVersion());

        if (faultAnalysis.getMappedTo() != null) {
            dto.setMappedTo(new FaultAnalysisListDTO());
            dto.getMappedTo().setCode(faultAnalysis.getMappedTo().getCode());
        }


        return dto;
    }

    /**
     * Update existing fault analysis object
     * @param object the fault analysis to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateFaultAnalysis(FaultAnalysisUpdateDTO object) {
        // Find and attach object
        FaultAnalysis faultAnalysis = repository.findById(object.getCode(), true);

        faultAnalysis.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        faultAnalysis.setComment(object.getComment() != null ? object.getComment().trim() : null);
        faultAnalysis.setVersion(object.getVersion());

        if (object.getMappedTo() == null || object.getMappedTo().getCode().isEmpty())
            faultAnalysis.setMappedTo(null);
        else
            faultAnalysis.setMappedTo(repository.getReference(FaultAnalysis.class, object.getMappedTo().getCode()));


        repository.merge(faultAnalysis, false);
    }

    /**
     * Find fault analysis by its ID
     * @param code
     * @return the fault analysis object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public FaultAnalysisUpdateDTO getFaultAnalysisForUpdate(String code) {
        // Find persistent object
        final FaultAnalysis faultAnalysis = repository.findById(code, true);

        final var dto = new FaultAnalysisUpdateDTO();
        dto.setCode(faultAnalysis.getCode());
        dto.setShortText(faultAnalysis.getShortText());
        dto.setComment(faultAnalysis.getComment());
        dto.setCreationDate(faultAnalysis.getCreationDate());
        dto.setLastUpdate(faultAnalysis.getLastUpdate());
        dto.setVersion(faultAnalysis.getVersion());

        if (faultAnalysis.getMappedTo() != null) {
            dto.setMappedTo(new FaultAnalysisListDTO());
            dto.getMappedTo().setCode(faultAnalysis.getMappedTo().getCode());
        }


        return dto;
    }

    /**
     * Create new fault analysis
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted fault analysis object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public FaultAnalysisCreateDTO createFaultAnalysis(FaultAnalysisCreateDTO object) {
        // Create new object to be persisted
        var newFaultAnalysis = new FaultAnalysis();
        newFaultAnalysis.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newFaultAnalysis.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newFaultAnalysis.setComment(object.getComment() != null ? object.getComment().trim() : null);

        if (object.getMappedTo() == null || object.getMappedTo().getCode().isEmpty())
            newFaultAnalysis.setMappedTo(null);
        else
            newFaultAnalysis.setMappedTo(repository.getReference(FaultAnalysis.class, object.getMappedTo().getCode()));


        newFaultAnalysis = repository.persist(newFaultAnalysis, true, true);

        object.setCreationDate(newFaultAnalysis.getCreationDate());
        object.setVersion(newFaultAnalysis.getVersion());

        return object;
    }

    /**
     * Search for fault analysis objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of fault analysis objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<FaultAnalysisSearchDTO> searchAllFaultAnalyses(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(FaultAnalysisSearchDTO.SELECT_CODE);
        selectTokens.add(FaultAnalysisSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(FaultAnalysisSearchDTO.SELECT_COMMENT);
        selectTokens.add(FaultAnalysisSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(FaultAnalysisSearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from FaultAnalysis a");

        return repository.search(searchObj, FaultAnalysisSearchDTO.class, selectTokens);
    }

    /**
     * Delete existing fault analysis
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteFaultAnalysis(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected fault analysis
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final FaultAnalysis sourceObject = repository.findById(sourceObjectId);
        final FaultAnalysis targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}