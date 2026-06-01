package com.kontron.qdw.boundary.material;

import com.kontron.qdw.domain.material.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.DEFAULT_LIST_SIZE;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.domain.base.*;
import jakarta.validation.ConstraintViolationException;
import java.util.*;
import com.kontron.qdw.repository.material.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class EWSEntryBoundaryService {
    @Generated
    private final EWSEntryRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public EWSEntryBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public EWSEntryBoundaryService(EWSEntryRepository repository) {
        this.repository = repository;
    }

    /**
     * Create new EWS entry
     * @param object
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted EWS entry object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public EWSEntryCreateDTO createEWSEntry(EWSEntryCreateDTO object) {
        // Create new object to be persisted
        var newEWSEntry = new EWSEntry();
        newEWSEntry.setThreshold(object.getThreshold());
        newEWSEntry.setBoardOrSystem(object.isBoardOrSystem());
        newEWSEntry.setFilterCriterion(object.getFilterCriterion() != null ? object.getFilterCriterion().trim() : null);
        newEWSEntry.setType(object.getType());
        newEWSEntry.setMaterial(repository.getReference(Material.class, object.getMaterial().getId()));
        newEWSEntry.setReceivers(new ArrayList<>());

        for (final UserListDTO a : object.getReceivers()) {
            final User b = repository.getReference(User.class, a.getId());
            newEWSEntry.getReceivers().add(b);
        }


        newEWSEntry = repository.persist(newEWSEntry, true, true);

        object.setId(newEWSEntry.getId());
        object.setVersion(newEWSEntry.getVersion());
        object.setCreationDate(newEWSEntry.getCreationDate());

        return object;
    }

    /**
     * Find EWS entry by its ID
     * @param id
     * @return the EWS entry object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public EWSEntryDTO findEWSEntryById(long id) {
        // Find persistent object
        final EWSEntry eWSEntry = repository.findById(id, true);

        final var dto = new EWSEntryDTO();
        dto.setThreshold(eWSEntry.getThreshold());
        dto.setBoardOrSystem(eWSEntry.isBoardOrSystem());
        dto.setFilterCriterion(eWSEntry.getFilterCriterion());
        dto.setType(eWSEntry.getType());
        dto.setId(eWSEntry.getId());
        dto.setVersion(eWSEntry.getVersion());
        dto.setCreationDate(eWSEntry.getCreationDate());
        dto.setLastUpdate(eWSEntry.getLastUpdate());
        dto.setMaterial(new MaterialListDTO());
        dto.getMaterial().setId(eWSEntry.getMaterial().getId());
        dto.getMaterial().setMaterialNumber(eWSEntry.getMaterial().getMaterialNumber());

        return dto;
    }

    /**
     * Get all users of a given EWS entry
     * @param id
     * @return a list of user objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<EWSEntryReceiversDTO> getReceiversOfEWSEntry(long id) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(EWSEntryReceiversDTO.SELECT_NAME);
        selectTokens.add(EWSEntryReceiversDTO.SELECT_EMAIL);
        selectTokens.add(EWSEntryReceiversDTO.SELECT_ACTIVE);
        selectTokens.add(EWSEntryReceiversDTO.SELECT_ID);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(DEFAULT_LIST_SIZE);
        searchObj.setFromClause("from EWSEntry x join x.receivers a");

        final var parentFilterField = searchObj.addSearchField("x.id", SearchFieldDataTypeEnum.LONG);
        parentFilterField.setFilterCriteria(Long.toString(id));

        return repository.search(searchObj, EWSEntryReceiversDTO.class, selectTokens);
    }

    /**
     * Update existing EWS entry object
     * @param object the EWS entry to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateEWSEntry(EWSEntryUpdateDTO object) {
        // Find and attach object
        EWSEntry eWSEntry = repository.findById(object.getId(), true);

        eWSEntry.setThreshold(object.getThreshold());
        eWSEntry.setBoardOrSystem(object.isBoardOrSystem());
        eWSEntry.setFilterCriterion(object.getFilterCriterion() != null ? object.getFilterCriterion().trim() : null);
        eWSEntry.setType(object.getType());
        eWSEntry.setVersion(object.getVersion());
        eWSEntry.setMaterial(repository.getReference(Material.class, object.getMaterial().getId()));
        eWSEntry.setReceivers(new ArrayList<>());

        for (final UserListDTO a : object.getReceivers()) {
            final User b = repository.getReference(User.class, a.getId());
            eWSEntry.getReceivers().add(b);
        }


        repository.merge(eWSEntry, false);
    }

    /**
     * Find EWS entry by its ID
     * @param id
     * @return the EWS entry object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public EWSEntryUpdateDTO getEWSEntryForUpdate(long id) {
        // Find persistent object
        final EWSEntry eWSEntry = repository.findById(id, true);

        final var dto = new EWSEntryUpdateDTO();
        dto.setThreshold(eWSEntry.getThreshold());
        dto.setBoardOrSystem(eWSEntry.isBoardOrSystem());
        dto.setFilterCriterion(eWSEntry.getFilterCriterion());
        dto.setType(eWSEntry.getType());
        dto.setId(eWSEntry.getId());
        dto.setVersion(eWSEntry.getVersion());
        dto.setCreationDate(eWSEntry.getCreationDate());
        dto.setLastUpdate(eWSEntry.getLastUpdate());
        dto.setMaterial(new MaterialListDTO());
        dto.getMaterial().setId(eWSEntry.getMaterial().getId());
        dto.getMaterial().setMaterialNumber(eWSEntry.getMaterial().getMaterialNumber());

        dto.setReceivers(new ArrayList<>());

        for (final User listElement : eWSEntry.getReceivers()) {
            final var listDTO = new UserListDTO();
            listDTO.setId(listElement.getId());
            listDTO.setName(listElement.getName());

            dto.getReceivers().add(listDTO);
        }


        return dto;
    }

    /**
     * Search for EWS entry objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of EWS entry objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<EWSEntrySearchDTO> searchAllEWSEntries(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(EWSEntrySearchDTO.SELECT_THRESHOLD);
        selectTokens.add(EWSEntrySearchDTO.SELECT_BOARDORSYSTEM);
        selectTokens.add(EWSEntrySearchDTO.SELECT_FILTERCRITERION);
        selectTokens.add(EWSEntrySearchDTO.SELECT_TYPE);
        selectTokens.add(EWSEntrySearchDTO.SELECT_ID);
        selectTokens.add(EWSEntrySearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(EWSEntrySearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(EWSEntrySearchDTO.SELECT_MATERIALMATERIALNUMBER);
        selectTokens.add(EWSEntrySearchDTO.SELECT_MATERIALID);

        searchObj.setFromClause("from EWSEntry a join a.material b");

        return repository.search(searchObj, EWSEntrySearchDTO.class, selectTokens);
    }

    /**
     * Count EWS entry objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllEWSEntries(SearchDTO searchObj) {
        searchObj.setFromClause("from EWSEntry a join a.material b");

        return repository.count(searchObj);
    }

    /**
     * Delete existing EWS entry
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteEWSEntry(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected EWS entry
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final EWSEntry sourceObject = repository.findById(sourceObjectId);
        final EWSEntry targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}