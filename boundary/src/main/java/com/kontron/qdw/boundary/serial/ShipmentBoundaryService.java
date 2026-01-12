package com.kontron.qdw.boundary.serial;

import com.kontron.qdw.domain.serial.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import com.kontron.qdw.dto.base.*;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.dto.material.*;
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

    /**
     * Find shipment by its ID
     * @param id
     * @return the shipment object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ShipmentDTO findShipmentById(long id) {
        // Find persistent object
        final Shipment shipment = repository.findById(id, true);

        final var dto = new ShipmentDTO();
        dto.setOrderNumber(shipment.getOrderNumber());
        dto.setShipmentDate(shipment.getShipmentDate());
        dto.setId(shipment.getId());
        dto.setVersion(shipment.getVersion());
        dto.setCreationDate(shipment.getCreationDate());
        dto.setLastUpdate(shipment.getLastUpdate());
        dto.setCustomer(new CustomerListDTO());
        dto.getCustomer().setCode(shipment.getCustomer().getCode());
        dto.getCustomer().setName(shipment.getCustomer().getName());
        dto.setMaterialRevision(new MaterialRevisionListDTO());
        dto.getMaterialRevision().setId(shipment.getMaterialRevision().getId());
        dto.getMaterialRevision().setRevisionNumber(shipment.getMaterialRevision().getRevisionNumber());
        dto.setMovementType(new MovementTypeListDTO());
        dto.getMovementType().setCode(shipment.getMovementType().getCode());
        dto.setPlant(new PlantListDTO());
        dto.getPlant().setCode(shipment.getPlant().getCode());
        dto.setSerialObject(new SerialObjectListDTO());
        dto.getSerialObject().setId(shipment.getSerialObject().getId());
        dto.getSerialObject().setSerialNumber(shipment.getSerialObject().getSerialNumber());
        dto.setSerialObjectSerialNumber(shipment.getSerialObject().getSerialNumber());
        dto.setMaterialRevisionMaterial(new MaterialListDTO());
        dto.getMaterialRevisionMaterial().setId(shipment.getMaterialRevision().getMaterial().getId());
        dto.getMaterialRevisionMaterial().setMaterialNumber(shipment.getMaterialRevision().getMaterial().getMaterialNumber());
        dto.setMaterialShortText(shipment.getMaterialRevision().getMaterial().getShortText());
        dto.setMaterialRevisionMaterialType(new MaterialTypeListDTO());
        dto.getMaterialRevisionMaterialType().setCode(shipment.getMaterialRevision().getMaterial().getMaterialType().getCode());
        dto.setMaterialRevisionMaterialClass(new MaterialClassListDTO());
        dto.getMaterialRevisionMaterialClass().setCode(shipment.getMaterialRevision().getMaterial().getMaterialClass().getCode());

        return dto;
    }

}