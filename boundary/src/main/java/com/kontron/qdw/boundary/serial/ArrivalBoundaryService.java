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
public class ArrivalBoundaryService {
    @Generated
    private final ArrivalRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public ArrivalBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public ArrivalBoundaryService(ArrivalRepository repository) {
        this.repository = repository;
    }

    /**
     * Find arrival by its ID
     * @param id
     * @return the arrival object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public ArrivalDTO findArrivalById(long id) {
        // Find persistent object
        final Arrival arrival = repository.findById(id, true);

        final var dto = new ArrivalDTO();
        dto.setArrivalDate(arrival.getArrivalDate());
        dto.setOrderNumber(arrival.getOrderNumber());
        dto.setId(arrival.getId());
        dto.setVersion(arrival.getVersion());
        dto.setCreationDate(arrival.getCreationDate());
        dto.setLastUpdate(arrival.getLastUpdate());
        dto.setMaterialRevision(new MaterialRevisionListDTO());
        dto.getMaterialRevision().setId(arrival.getMaterialRevision().getId());
        dto.getMaterialRevision().setRevisionNumber(arrival.getMaterialRevision().getRevisionNumber());
        dto.setMovementType(new MovementTypeListDTO());
        dto.getMovementType().setCode(arrival.getMovementType().getCode());
        dto.setPlant(new PlantListDTO());
        dto.getPlant().setCode(arrival.getPlant().getCode());
        dto.setSerialObject(new SerialObjectListDTO());
        dto.getSerialObject().setId(arrival.getSerialObject().getId());
        dto.setSupplier(new SupplierListDTO());
        dto.getSupplier().setCode(arrival.getSupplier().getCode());
        dto.getSupplier().setName(arrival.getSupplier().getName());
        dto.setSerialObjectSerialNumber(arrival.getSerialObject().getSerialNumber());
        dto.setMaterialRevisionMaterial(new MaterialListDTO());
        dto.getMaterialRevisionMaterial().setId(arrival.getMaterialRevision().getMaterial().getId());
        dto.getMaterialRevisionMaterial().setMaterialNumber(arrival.getMaterialRevision().getMaterial().getMaterialNumber());
        dto.setMaterialSapNumber(arrival.getMaterialRevision().getMaterial().getSapNumber());
        dto.setMaterialRevisionMaterialType(new MaterialTypeListDTO());
        dto.getMaterialRevisionMaterialType().setCode(arrival.getMaterialRevision().getMaterial().getMaterialType().getCode());
        dto.setMaterialRevisionMaterialClass(new MaterialClassListDTO());
        dto.getMaterialRevisionMaterialClass().setCode(arrival.getMaterialRevision().getMaterial().getMaterialClass().getCode());
        dto.setMaterialShortText(arrival.getMaterialRevision().getMaterial().getShortText());

        return dto;
    }

    /**
     * Delete existing arrival
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteArrival(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected arrival
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final Arrival sourceObject = repository.findById(sourceObjectId);
        final Arrival targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

    /**
     * Search for arrival objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of arrival objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ArrivalSearchDTO> searchAllArrivals(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(ArrivalSearchDTO.SELECT_ARRIVALDATE);
        selectTokens.add(ArrivalSearchDTO.SELECT_ORDERNUMBER);
        selectTokens.add(ArrivalSearchDTO.SELECT_ID);
        selectTokens.add(ArrivalSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(ArrivalSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(ArrivalSearchDTO.SELECT_MOVEMENTTYPECODE);
        selectTokens.add(ArrivalSearchDTO.SELECT_PLANTCODE);
        selectTokens.add(ArrivalSearchDTO.SELECT_SUPPLIERNAME);
        selectTokens.add(ArrivalSearchDTO.SELECT_SUPPLIERCODE);
        selectTokens.add(ArrivalSearchDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(ArrivalSearchDTO.SELECT_SERIALOBJECTID);
        selectTokens.add(ArrivalSearchDTO.SELECT_SERIALOBJECTSERIALNUMBER);
        selectTokens.add(ArrivalSearchDTO.SELECT_MATREVMATID);
        selectTokens.add(ArrivalSearchDTO.SELECT_MATREVMATMATERIALNUMBER);
        selectTokens.add(ArrivalSearchDTO.SELECT_MATREVMATSAPNUMBER);
        selectTokens.add(ArrivalSearchDTO.SELECT_MATREVMATSHORTTEXT);
        selectTokens.add(ArrivalSearchDTO.SELECT_MATREVMATMATERIALTYPECODE);
        selectTokens.add(ArrivalSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE);
        selectTokens.add(ArrivalSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE);
        selectTokens.add(ArrivalSearchDTO.SELECT_MATREVMATMATERIALHIERARCHY);
        selectTokens.add(ArrivalSearchDTO.SELECT_MATREVREVISIONNUMBER);

        searchObj.setFromClause("from Arrival a "
                + "join a.materialRevision b "
                + "join a.movementType c "
                + "join a.plant d "
                + "join a.serialObject e "
                + "join a.supplier f "
                + "join b.material l "
                + "join l.ownerLocation o "
                + "join l.materialClass p "
                + "join l.materialType q");

        return repository.search(searchObj, ArrivalSearchDTO.class, selectTokens);
    }

    /**
     * Count arrival objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllArrivals(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from Arrival a join a.materialRevision b join a.movementType c join a.plant d join a.serialObject e join a.supplier f join b.material l join l.ownerLocation o join l.materialClass p join l.materialType q");

        return repository.count(searchObj);
    }

    /**
     * Search for arrival objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of arrival objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<ArrivalLastSearchDTO> searchAllArrivalsLast(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(ArrivalLastSearchDTO.SELECT_ARRIVALDATE);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_ORDERNUMBER);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_ID);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MOVEMENTTYPECODE);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_PLANTCODE);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_SUPPLIERNAME);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_SUPPLIERCODE);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_SERIALOBJECTID);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_SERIALOBJECTSERIALNUMBER);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MATREVMATID);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MATREVMATMATERIALNUMBER);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MATREVMATSAPNUMBER);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MATREVMATSHORTTEXT);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MATREVMATOWNERLOCATIONCODE);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MATREVMATMATERIALTYPECODE);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MATREVMATMATERIALCLASSCODE);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MATREVMATMATERIALHIERARCHY);
        selectTokens.add(ArrivalLastSearchDTO.SELECT_MATREVREVISIONNUMBER);

        searchObj.setFromClause("from Arrival a "
                + "join a.materialRevision b "
                + "join a.movementType c "
                + "join a.plant d "
                + "join a.serialObject e "
                + "join a.supplier f "
                + "join b.material l "
                + "join l.ownerLocation o "
                + "join l.materialClass p "
                + "join l.materialType q "
                + "where a.arrivalDate = ("
                + "  select max(la.arrivalDate) "
                + "  from Arrival la "
                + "  join la.serialObject lc "
                + "  where lc.id = b.id"
                + ")");

        return repository.search(searchObj, ArrivalLastSearchDTO.class, selectTokens);
    }

    /**
     * Count arrival objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllArrivalsLast(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from Arrival a join a.materialRevision b join a.movementType c join a.plant d join a.serialObject e join a.supplier f join b.material l join l.ownerLocation o join l.materialClass p join l.materialType q where a.arrivalDate = (select max(la.arrivalDate) from Arrival la join la.serialObject lc where lc.id = b.id)");

        return repository.count(searchObj);
    }

}