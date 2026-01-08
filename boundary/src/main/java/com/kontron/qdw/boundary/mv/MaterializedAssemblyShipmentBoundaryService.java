package com.kontron.qdw.boundary.mv;

import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.domain.mv.*;
import com.kontron.qdw.dto.mv.*;
import com.kontron.qdw.repository.mv.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class MaterializedAssemblyShipmentBoundaryService {
    @Generated
    private final MaterializedAssemblyShipmentRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterializedAssemblyShipmentBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterializedAssemblyShipmentBoundaryService(MaterializedAssemblyShipmentRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for materialized assembly shipment objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of materialized assembly shipment objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterializedAssemblyShipmentSearchDTO> searchAllMaterializedAssemblyShipments(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_ARRIVALDATE);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_ARRIVALID);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_ARRIVALMOVEMENTTYPE);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_COUNTRYCODE);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_COUNTRYNAME);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_CUSTOMERCODE);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_CUSTOMERNAME);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_CUSTOMERORDERNUMBER);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PURCHASEORDERNUMBER);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_SHIPMENTDATE);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_SHIPMENTID);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_SHIPMENTMOVEMENTTYPE);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_SUPPLIERCODE);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_SUPPLIERNAME);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_ID);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_MESERIALOBJECTID);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTSERIALOBJECTID);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_SERIALNUMBER);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTSERIALNUMBER);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_MATERIALNUMBER);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTMATERIALNUMBER);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_MATERIALTYPE);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTMATERIALTYPE);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_MATERIALSHORTTEXT);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTMATERIALSHORTTEXT);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_SAPNUMBER);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTSAPNUMBER);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_MATERIALHIERARCHY);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTMATERIALHIERARCHY);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_REVISIONID);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTREVISIONID);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_REVISIONNUMBER);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PARENTREVISIONNUMBER);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_ASSEMBLYDATE);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_ASSEMBLYPO);
        selectTokens.add(MaterializedAssemblyShipmentSearchDTO.SELECT_PLANT);

        searchObj.setFromClause("from MaterializedAssemblyShipment a");

        return repository.search(searchObj, MaterializedAssemblyShipmentSearchDTO.class, selectTokens);
    }

    /**
     * Count materialized assembly shipment objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllMaterializedAssemblyShipments(SearchDTO searchObj) {
        searchObj.setFromClause("from MaterializedAssemblyShipment a");

        return repository.count(searchObj);
    }

    /**
     * Delete existing materialized assembly shipment
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteMaterializedAssemblyShipment(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected materialized assembly shipment
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final MaterializedAssemblyShipment sourceObject = repository.findById(sourceObjectId);
        final MaterializedAssemblyShipment targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}