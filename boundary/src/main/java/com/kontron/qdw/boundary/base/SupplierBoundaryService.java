package com.kontron.qdw.boundary.base;

import jakarta.validation.ConstraintViolationException;
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
public class SupplierBoundaryService {
    @Generated
    private final SupplierRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public SupplierBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public SupplierBoundaryService(SupplierRepository repository) {
        this.repository = repository;
    }

    /**
     * Create new supplier
     * @param object
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the persisted supplier object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SupplierCreateDTO createSupplier(SupplierCreateDTO object) {
        // Create new object to be persisted
        var newSupplier = new Supplier();
        newSupplier.setName(object.getName() != null ? object.getName().trim() : null);
        newSupplier.setStreet(object.getStreet() != null ? object.getStreet().trim() : null);
        newSupplier.setZipCode(object.getZipCode() != null ? object.getZipCode().trim() : null);
        newSupplier.setCity(object.getCity() != null ? object.getCity().trim() : null);
        newSupplier.setCode(object.getCode() != null ? object.getCode().trim() : null);
        newSupplier.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        newSupplier.setComment(object.getComment() != null ? object.getComment().trim() : null);
        newSupplier.setCountry(repository.getReference(Country.class, object.getCountry().getCode()));

        newSupplier = repository.persist(newSupplier, true, true, true);

        object.setCreationDate(newSupplier.getCreationDate());
        object.setVersion(newSupplier.getVersion());

        return object;
    }

    /**
     * Update existing supplier object
     * @param object the supplier to update
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateSupplier(SupplierUpdateDTO object) {
        // Find and attach object
        Supplier supplier = repository.findById(object.getCode(), true);

        supplier.setName(object.getName() != null ? object.getName().trim() : null);
        supplier.setStreet(object.getStreet() != null ? object.getStreet().trim() : null);
        supplier.setZipCode(object.getZipCode() != null ? object.getZipCode().trim() : null);
        supplier.setCity(object.getCity() != null ? object.getCity().trim() : null);
        supplier.setShortText(object.getShortText() != null ? object.getShortText().trim() : null);
        supplier.setComment(object.getComment() != null ? object.getComment().trim() : null);
        supplier.setVersion(object.getVersion());
        supplier.setCountry(repository.getReference(Country.class, object.getCountry().getCode()));

        repository.merge(supplier, true, false);
    }

    /**
     * Find supplier by its ID
     * @param code
     * @return the supplier object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SupplierUpdateDTO getSupplierForUpdate(String code) {
        // Find persistent object
        final Supplier supplier = repository.findById(code, true);

        final var dto = new SupplierUpdateDTO();
        dto.setName(supplier.getName());
        dto.setStreet(supplier.getStreet());
        dto.setZipCode(supplier.getZipCode());
        dto.setCity(supplier.getCity());
        dto.setCode(supplier.getCode());
        dto.setShortText(supplier.getShortText());
        dto.setComment(supplier.getComment());
        dto.setCreationDate(supplier.getCreationDate());
        dto.setLastUpdate(supplier.getLastUpdate());
        dto.setVersion(supplier.getVersion());
        dto.setCountry(new CountryListDTO());
        dto.getCountry().setCode(supplier.getCountry().getCode());
        dto.getCountry().setName(supplier.getCountry().getName());

        return dto;
    }

    /**
     * Search for supplier objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of supplier objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<SupplierSearchDTO> searchAllSuppliers(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(SupplierSearchDTO.SELECT_NAME);
        selectTokens.add(SupplierSearchDTO.SELECT_STREET);
        selectTokens.add(SupplierSearchDTO.SELECT_ZIPCODE);
        selectTokens.add(SupplierSearchDTO.SELECT_CITY);
        selectTokens.add(SupplierSearchDTO.SELECT_CODE);
        selectTokens.add(SupplierSearchDTO.SELECT_SHORTTEXT);
        selectTokens.add(SupplierSearchDTO.SELECT_COMMENT);
        selectTokens.add(SupplierSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(SupplierSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(SupplierSearchDTO.SELECT_COUNTRYNAME);
        selectTokens.add(SupplierSearchDTO.SELECT_COUNTRYCODE);

        searchObj.setFromClause("from Supplier a join a.country b");

        return repository.search(searchObj, SupplierSearchDTO.class, selectTokens);
    }

    /**
     * Count supplier objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllSuppliers(SearchDTO searchObj) {
        searchObj.setFromClause("from Supplier a join a.country b");

        return repository.count(searchObj);
    }

    /**
     * Delete existing supplier
     * @param code the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteSupplier(String code) {
        repository.delete(code);
    }

    /**
     * Create copy of selected supplier
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
        final Supplier sourceObject = repository.findById(sourceObjectId);
        final Supplier targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getCode();
    }

}