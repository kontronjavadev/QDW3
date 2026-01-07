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
public class MaterializedArrivalShipmentBoundaryService {
    @Generated
    private final MaterializedArrivalShipmentRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterializedArrivalShipmentBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterializedArrivalShipmentBoundaryService(MaterializedArrivalShipmentRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for materialized arrival shipment objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of materialized arrival shipment objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterializedArrivalShipmentSearchDTO> searchAllMaterializedArrivalShipments(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_ARRIVALDATE);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_ARRIVALID);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_ARRIVALMOVEMENTTYPE);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_COUNTRYCODE);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_COUNTRYNAME);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_CUSTOMERCODE);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_CUSTOMERNAME);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_CUSTOMERORDERNUMBER);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_OWNERLOCATION);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PURCHASEORDERNUMBER);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_SHIPMENTDATE);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_SHIPMENTID);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_SHIPMENTMOVEMENTTYPE);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_SUPPLIERCODE);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_SUPPLIERNAME);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_ID);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_MESERIALOBJECTID);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PARENTSERIALOBJECTID);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_SERIALNUMBER);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PARENTSERIALNUMBER);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_MATERIALNUMBER);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PARENTMATERIALNUMBER);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_MATERIALTYPE);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PARENTMATERIALTYPE);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_MATERIALSHORTTEXT);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PARENTMATERIALSHORTTEXT);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_SAPNUMBER);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PARENTSAPNUMBER);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_MATERIALHIERARCHY);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PARENTMATERIALHIERARCHY);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_REVISIONID);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PARENTREVISIONID);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_REVISIONNUMBER);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PARENTREVISIONNUMBER);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_ASSEMBLYDATE);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_ASSEMBLYPO);
        selectTokens.add(MaterializedArrivalShipmentSearchDTO.SELECT_PLANT);

        searchObj.setFromClause("from MaterializedArrivalShipment a");

        return repository.search(searchObj, MaterializedArrivalShipmentSearchDTO.class, selectTokens);
    }

    /**
     * Count materialized arrival shipment objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllMaterializedArrivalShipments(SearchDTO searchObj) {
        searchObj.setFromClause("from MaterializedArrivalShipment a");

        return repository.count(searchObj);
    }

    /**
     * Delete existing materialized arrival shipment
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteMaterializedArrivalShipment(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected materialized arrival shipment
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final MaterializedArrivalShipment sourceObject = repository.findById(sourceObjectId);
        final MaterializedArrivalShipment targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}