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
public class AggregatedShipmentBoundaryService {
    @Generated
    private final AggregatedShipmentRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public AggregatedShipmentBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public AggregatedShipmentBoundaryService(AggregatedShipmentRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for aggregated shipment objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of aggregated shipment objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<AggregatedShipmentSearchDTO> searchAllAggregatedShipments(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_SHIPMENTS);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_ID);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_YEAR);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MONTH);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_PLANTCODE);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_CUSTOMERNAME);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_CUSTOMERCODE);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MOVEMENTTYPECODE);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_CUSTCOUNTRYCODE);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_CUSTCOUNTRYNAME);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MATREVMATERIALID);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MATREVMATLMATERIALNUMBER);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MATREVMATSAPNUMBER);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MATREVMATSHORTTEXT);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MATREVMATMATERIALTYPECODE);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MATREVMATMATERIALHIERARCHY);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE);
        selectTokens.add(AggregatedShipmentSearchDTO.SELECT_MATREVREVISIONNUMBER);

        searchObj.setFromClause(
                "from AggregatedShipment a join a.materialRevision b join a.plant c join a.customer d join a.movementType e join d.country f join b.material h join h.ownerLocation k join h.materialClass l join h.materialType m");

        return repository.search(searchObj, AggregatedShipmentSearchDTO.class, selectTokens);
    }

    /**
     * Count aggregated shipment objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllAggregatedShipments(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from AggregatedShipment a join a.materialRevision b join a.plant c join a.customer d join a.movementType e join d.country f join b.material h join h.ownerLocation k join h.materialClass l join h.materialType m");

        return repository.count(searchObj);
    }

    /**
     * Delete existing aggregated shipment
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteAggregatedShipment(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected aggregated shipment
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final AggregatedShipment sourceObject = repository.findById(sourceObjectId);
        final AggregatedShipment targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}