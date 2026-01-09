package com.kontron.qdw.boundary.serial;

import com.kontron.qdw.domain.serial.*;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.dto.serial.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import com.kontron.qdw.repository.serial.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class ShipmentBoundaryService {
    @Generated
    private final ShipmentRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public ShipmentBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public ShipmentBoundaryService(ShipmentRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for shipment objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of shipment objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ShipmentSearchDTO> searchAllShipments(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(ShipmentSearchDTO.SELECT_ORDERNUMBER);
        selectTokens.add(ShipmentSearchDTO.SELECT_SHIPMENTDATE);
        selectTokens.add(ShipmentSearchDTO.SELECT_ID);
        selectTokens.add(ShipmentSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(ShipmentSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(ShipmentSearchDTO.SELECT_CUSTOMERNAME);
        selectTokens.add(ShipmentSearchDTO.SELECT_CUSTOMERCODE);
        selectTokens.add(ShipmentSearchDTO.SELECT_MOVEMENTTYPECODE);
        selectTokens.add(ShipmentSearchDTO.SELECT_PLANTCODE);
        selectTokens.add(ShipmentSearchDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(ShipmentSearchDTO.SELECT_SERIALOBJECTID);
        selectTokens.add(ShipmentSearchDTO.SELECT_SERIALOBJECTSERIALNUMBER);
        selectTokens.add(ShipmentSearchDTO.SELECT_MATREVMATERIALID);
        selectTokens.add(ShipmentSearchDTO.SELECT_MATREVMATERIALMATERIALNUMBER);
        selectTokens.add(ShipmentSearchDTO.SELECT_MATREVMATERIALSAPNUMBER);
        selectTokens.add(ShipmentSearchDTO.SELECT_MATREVMATERIALSHORTTEXT);
        selectTokens.add(ShipmentSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE);
        selectTokens.add(ShipmentSearchDTO.SELECT_MATREVMATMATERIALTYPECODE);
        selectTokens.add(ShipmentSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE);
        selectTokens.add(ShipmentSearchDTO.SELECT_MATREVMATMATERIALHIERARCHY);
        selectTokens.add(ShipmentSearchDTO.SELECT_MATREVREVISIONNUMBER);

        searchObj.setFromClause(
                "from Shipment a join a.customer b join a.materialRevision c join a.movementType d join a.plant e join a.serialObject f join c.material l join l.ownerLocation o join l.materialClass p join l.materialType q");

        return repository.search(searchObj, ShipmentSearchDTO.class, selectTokens);
    }

    /**
     * Count shipment objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllShipments(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from Shipment a join a.customer b join a.materialRevision c join a.movementType d join a.plant e join a.serialObject f join c.material l join l.ownerLocation o join l.materialClass p join l.materialType q");

        return repository.count(searchObj);
    }

    /**
     * Delete existing shipment
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteShipment(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected shipment
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final Shipment sourceObject = repository.findById(sourceObjectId);
        final Shipment targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}