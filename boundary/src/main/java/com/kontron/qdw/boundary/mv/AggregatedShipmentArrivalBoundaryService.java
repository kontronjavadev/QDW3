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
public class AggregatedShipmentArrivalBoundaryService {
    @Generated
    private final AggregatedShipmentArrivalRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public AggregatedShipmentArrivalBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public AggregatedShipmentArrivalBoundaryService(AggregatedShipmentArrivalRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for aggregated shipment arrival objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of aggregated shipment arrival objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<AggregatedShipmentArrivalSearchDTO> searchAllAggregatedShipmentArrivals(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_SHIPMENTS);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_ID);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_YEAR);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MONTH);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_PLANTCODE);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_CUSTOMERNAME);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_CUSTOMERCODE);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_SHIPMENTMOVEMENTTYPECODE);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_CUSTCOUNTRYCODE);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_CUSTCOUNTRYNAME);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_SUPPLIERCODE);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_SUPPLIERNAME);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_ARRIVALMOVEMENTTYPECODE);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MATREVMATERIALID);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MATREVMATMATERIALNUMBER);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MATREVMATSAPNUMBER);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MATREVMATMATERIALHIERARCHY);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MATREVMATSHORTTEXT);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MATREVMATMATERIALTYPECODE);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE);
        selectTokens.add(AggregatedShipmentArrivalSearchDTO.SELECT_MATERIALREVISIONREVISIONNUMBER);

        searchObj.setFromClause(
                "from AggregatedShipmentArrival a join a.materialRevision b join a.plant c join a.customer d join a.shipmentMovementType e left join a.arrivalMovementType f left join a.supplier g join d.country h join b.material k join k.ownerLocation n join k.materialClass o join k.materialType p");

        return repository.search(searchObj, AggregatedShipmentArrivalSearchDTO.class, selectTokens);
    }

    /**
     * Count aggregated shipment arrival objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllAggregatedShipmentArrivals(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from AggregatedShipmentArrival a join a.materialRevision b join a.plant c join a.customer d join a.shipmentMovementType e left join a.arrivalMovementType f left join a.supplier g join d.country h join b.material k join k.ownerLocation n join k.materialClass o join k.materialType p");

        return repository.count(searchObj);
    }

    /**
     * Delete existing aggregated shipment arrival
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteAggregatedShipmentArrival(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected aggregated shipment arrival
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final AggregatedShipmentArrival sourceObject = repository.findById(sourceObjectId);
        final AggregatedShipmentArrival targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}