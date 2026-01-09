package com.kontron.qdw.boundary.base;

import jakarta.validation.ConstraintViolationException;

import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;

import java.util.*;
import com.kontron.qdw.repository.base.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import com.kontron.qdw.dto.base.*;

import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Stateless
public class CustomerBoundaryService {
    @Generated
    private final CustomerRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public CustomerBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public CustomerBoundaryService(CustomerRepository repository) {
        this.repository = repository;
    }

    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<CustomerListDTO> findCustomer(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(CustomerListDTO.SELECT_CODE);
        selectTokens.add(CustomerListDTO.SELECT_NAME);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from Customer a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(CustomerListDTO.SELECT_NAME, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, CustomerListDTO.class, selectTokens);
    }

    /**
     * Create new customer
     * @param object
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted customer object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public CustomerDTO createCustomer(CustomerDTO object) {
        // Create new object to be persisted
        var newCustomer = new Customer();
        newCustomer.setName(object.getName() != null ? object.getName().trim() : null);
        newCustomer.setStreet(object.getStreet() != null ? object.getStreet().trim() : null);
        newCustomer.setZipCode(object.getZipCode() != null ? object.getZipCode().trim() : null);
        newCustomer.setCity(object.getCity() != null ? object.getCity().trim() : null);
        newCustomer.setInternal(object.isInternal());
        newCustomer.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newCustomer.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newCustomer.setComment(object.getComment() != null ? object.getComment().trim() : null);
        newCustomer.setCountry(repository.getReference(Country.class, object.getCountry().getCode()));

        if (object.getVerticalSector() == null || object.getVerticalSector().getCode().isEmpty())
            newCustomer.setVerticalSector(null);
        else
            newCustomer.setVerticalSector(repository.getReference(VerticalSector.class, object.getVerticalSector().getCode()));


        newCustomer = repository.persist(newCustomer, true, true, true);

        object.setCode(newCustomer.getCode());
        object.setCreationDate(newCustomer.getCreationDate());
        object.setVersion(newCustomer.getVersion());

        return object;
    }

    /**
     * Update existing customer object
     * @param object the customer to update
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateCustomer(CustomerUpdateDTO object) {
        // Find and attach object
        Customer customer = repository.findById(object.getCode(), true);

        customer.setName(object.getName() != null ? object.getName().trim() : null);
        customer.setStreet(object.getStreet() != null ? object.getStreet().trim() : null);
        customer.setZipCode(object.getZipCode() != null ? object.getZipCode().trim() : null);
        customer.setCity(object.getCity() != null ? object.getCity().trim() : null);
        customer.setInternal(object.isInternal());
        customer.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        customer.setComment(object.getComment() != null ? object.getComment().trim() : null);
        customer.setVersion(object.getVersion());
        customer.setCountry(repository.getReference(Country.class, object.getCountry().getCode()));

        if (object.getVerticalSector() == null || object.getVerticalSector().getCode().isEmpty())
            customer.setVerticalSector(null);
        else
            customer.setVerticalSector(repository.getReference(VerticalSector.class, object.getVerticalSector().getCode()));


        repository.merge(customer, true, false);
    }

    /**
     * Find customer by its ID
     * @param code
     * @return the customer object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public CustomerUpdateDTO getCustomerForUpdate(String code) {
        // Find persistent object
        final Customer customer = repository.findById(code, true);

        final var dto = new CustomerUpdateDTO();
        dto.setName(customer.getName());
        dto.setStreet(customer.getStreet());
        dto.setZipCode(customer.getZipCode());
        dto.setCity(customer.getCity());
        dto.setInternal(customer.isInternal());
        dto.setCode(customer.getCode());
        dto.setShortText(customer.getShortText());
        dto.setComment(customer.getComment());
        dto.setCreationDate(customer.getCreationDate());
        dto.setLastUpdate(customer.getLastUpdate());
        dto.setVersion(customer.getVersion());
        dto.setCountry(new CountryListDTO());
        dto.getCountry().setCode(customer.getCountry().getCode());
        dto.getCountry().setName(customer.getCountry().getName());

        if (customer.getVerticalSector() != null) {
            dto.setVerticalSector(new VerticalSectorListDTO());
            dto.getVerticalSector().setCode(customer.getVerticalSector().getCode());
        }


        return dto;
    }

    /**
     * Search for customer objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of customer objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<CustomerSearchDTO> searchAllCustomers(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(CustomerSearchDTO.SELECT_NAME);
        selectTokens.add(CustomerSearchDTO.SELECT_STREET);
        selectTokens.add(CustomerSearchDTO.SELECT_ZIPCODE);
        selectTokens.add(CustomerSearchDTO.SELECT_CITY);
        selectTokens.add(CustomerSearchDTO.SELECT_INTERNAL);
        selectTokens.add(CustomerSearchDTO.SELECT_CODE);
        selectTokens.add(CustomerSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(CustomerSearchDTO.SELECT_COMMENT);
        selectTokens.add(CustomerSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(CustomerSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(CustomerSearchDTO.SELECT_COUNTRYNAME);
        selectTokens.add(CustomerSearchDTO.SELECT_COUNTRYCODE);

        searchObj.setFromClause("from Customer a join a.country b");

        return repository.search(searchObj, CustomerSearchDTO.class, selectTokens);
    }

    /**
     * Count customer objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllCustomers(SearchDTO searchObj) {
        searchObj.setFromClause("from Customer a join a.country b");

        return repository.count(searchObj);
    }

    /**
     * Delete existing customer
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteCustomer(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected customer
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
        final Customer sourceObject = repository.findById(sourceObjectId);
        final Customer targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}