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
public class AggregatedArrivalBoundaryService {
    @Generated
    private final AggregatedArrivalRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public AggregatedArrivalBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public AggregatedArrivalBoundaryService(AggregatedArrivalRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for aggregated arrival objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of aggregated arrival objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<AggregatedArrivalSearchDTO> searchAllAggregatedArrivals(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_ARRIVALS);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_ID);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_YEAR);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MONTH);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_PLANTCODE);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MOVEMENTTYPECODE);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_SUPPLIERNAME);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_SUPPLIERCODE);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_COUNTRYCODE);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_COUNTRYNAME);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MATREVMATERIALID);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MATREVMATMATERIALNUMBER);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MATREVMATSAPNUMBER);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MATREVMATTYPECODE);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MATREVMATCLASSCODE);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MATREVMATSHORTTEXT);
        selectTokens.add(AggregatedArrivalSearchDTO.SELECT_MATREVREVISIONNUMBER);

        searchObj.setFromClause(
                "from AggregatedArrival a join a.materialRevision b join a.plant c join a.movementType d join a.supplier e join e.country f join b.material g join g.ownerLocation j join g.materialClass k join g.materialType l");

        return repository.search(searchObj, AggregatedArrivalSearchDTO.class, selectTokens);
    }

    /**
     * Count aggregated arrival objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllAggregatedArrivals(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from AggregatedArrival a join a.materialRevision b join a.plant c join a.movementType d join a.supplier e join e.country f join b.material g join g.ownerLocation j join g.materialClass k join g.materialType l");

        return repository.count(searchObj);
    }

    /**
     * Delete existing aggregated arrival
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteAggregatedArrival(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected aggregated arrival
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final AggregatedArrival sourceObject = repository.findById(sourceObjectId);
        final AggregatedArrival targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}