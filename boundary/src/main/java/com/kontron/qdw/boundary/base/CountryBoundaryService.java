package com.kontron.qdw.boundary.base;

import com.kontron.qdw.repository.base.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.domain.base.*;
import jakarta.validation.ConstraintViolationException;
import java.util.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class CountryBoundaryService {
    @Generated
    private final CountryRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public CountryBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public CountryBoundaryService(CountryRepository repository) {
        this.repository = repository;
    }

    /**
     * Create new country
     * @param object
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted country object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public CountryCreateDTO createCountry(CountryCreateDTO object) {
        // Create new object to be persisted
        var newCountry = new Country();
        newCountry.setName(object.getName() != null ? object.getName().trim() : null);
        newCountry.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newCountry.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newCountry.setComment(object.getComment() != null ? object.getComment().trim() : null);
        newCountry.setActive(object.isActive());

        newCountry = repository.persist(newCountry, true, true, true);

        object.setCode(newCountry.getCode());
        object.setCreationDate(newCountry.getCreationDate());
        object.setVersion(newCountry.getVersion());

        return object;
    }

    /**
     * Update existing country object
     * @param object the country to update
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateCountry(CountryUpdateDTO object) {
        // Find and attach object
        Country country = repository.findById(object.getCode(), true);

        country.setName(object.getName() != null ? object.getName().trim() : null);
        country.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        country.setComment(object.getComment() != null ? object.getComment().trim() : null);
        country.setActive(object.isActive());
        country.setVersion(object.getVersion());

        repository.merge(country, true, false);
    }

    /**
     * Find country by its ID
     * @param code
     * @return the country object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public CountryUpdateDTO getCountryForUpdate(String code) {
        // Find persistent object
        final Country country = repository.findById(code, true);

        final var dto = new CountryUpdateDTO();
        dto.setName(country.getName());
        dto.setCode(country.getCode());
        dto.setShortText(country.getShortText());
        dto.setComment(country.getComment());
        dto.setActive(country.isActive());
        dto.setCreationDate(country.getCreationDate());
        dto.setLastUpdate(country.getLastUpdate());
        dto.setVersion(country.getVersion());

        return dto;
    }

    /**
     * Search for country objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of country objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<CountrySearchDTO> searchAllCountries(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(CountrySearchDTO.SELECT_NAME);
        selectTokens.add(CountrySearchDTO.SELECT_CODE);
        selectTokens.add(CountrySearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(CountrySearchDTO.SELECT_COMMENT);
        selectTokens.add(CountrySearchDTO.SELECT_ACTIVE);
        selectTokens.add(CountrySearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(CountrySearchDTO.SELECT_LASTUPDATE);

        searchObj.setFromClause("from Country a");

        return repository.search(searchObj, CountrySearchDTO.class, selectTokens);
    }

    /**
     * Delete existing country
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteCountry(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected country
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public String copy(String sourceObjectId, long loggedOnUserId) {
        final Country sourceObject = repository.findById(sourceObjectId);
        final Country targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

    /**
     * Search for country objects
     * @param filter
     * @return a list of country objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<CountryListDTO> findCountries(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(CountryListDTO.SELECT_CODE);
        selectTokens.add(CountryListDTO.SELECT_NAME);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from Country a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(CountryListDTO.SELECT_NAME, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, CountryListDTO.class, selectTokens);
    }

    /**
     * Find country by its ID
     * @param id
     * @return the country object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public CountryListDTO findListCountry(String id) {
        // Find persistent object
        final Country country = repository.findById(id, true);

        final var dto = new CountryListDTO();
        dto.setCode(country.getCode());
        dto.setName(country.getName());

        return dto;
    }

}