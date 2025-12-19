package com.kontron.qdw.boundary.mv;

import com.kontron.qdw.domain.mv.*;
import com.kontron.qdw.dto.mv.*;
import net.sourceforge.jbizmo.commons.search.dto.SearchDTO;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import net.sourceforge.jbizmo.commons.search.exception.GeneralSearchException;
import com.kontron.qdw.repository.mv.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class MaterializedArrivalBoundaryService {
    @Generated
    private final MaterializedArrivalRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterializedArrivalBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterializedArrivalBoundaryService(MaterializedArrivalRepository repository) {
        this.repository = repository;
    }

    /**
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final MaterializedArrival sourceObject = repository.findById(sourceObjectId);
        final MaterializedArrival targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

    /**
     * Search for materialized arrival objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of materialized arrival objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterializedArrivalSearchDTO> searchAllMaterializedArrivals(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_ARRIVALDATE);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_SUPPLIERNAME);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MOVEMENTTYPE);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_ORDERNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_COUNTRYCODE);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_COUNTRYNAME);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_ID);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MESERIALOBJECTID);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTSERIALOBJECTID);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_SERIALNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTSERIALNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MATERIALNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MATERIALTYPE);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALTYPE);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MATERIALSHORTTEXT);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALSHORTTEXT);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_SAPNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTSAPNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MATERIALHIERARCHY);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALHIERARCHY);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_REVISIONID);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTREVISIONID);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_REVISIONNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTREVISIONNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_ASSEMBLYDATE);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_ASSEMBLYPO);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PLANT);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_SUPPLIERCODE);

        searchObj.setFromClause("from MaterializedArrival a");

        return repository.search(searchObj, MaterializedArrivalSearchDTO.class, selectTokens);
    }

    /**
     * Count materialized arrival objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllMaterializedArrivals(SearchDTO searchObj) {
        searchObj.setFromClause("from MaterializedArrival a");

        return repository.count(searchObj);
    }

    /**
     * Delete existing materialized arrival
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteMaterializedArrival(long id) {
        repository.delete(id);
    }

}