package com.kontron.qdw.boundary.serial;

import com.kontron.qdw.domain.serial.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.DEFAULT_LIST_SIZE;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import com.kontron.qdw.repository.serial.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.repository.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class SerialObjectBoundaryService {
    @Generated
    private final SerialObjectRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public SerialObjectBoundaryService(SerialObjectRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for serial object objects
     * @param filter
     * @return a list of serial object objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<SerialObjectListDTO> findSerialObjects(String filter) {
        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD))
            try {
                Long.parseLong(filter);
            }
            catch (NumberFormatException e) {
                return Collections.emptyList();
            }

        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(SerialObjectListDTO.SELECT_ID);
        selectTokens.add(SerialObjectListDTO.SELECT_SERIALNUMBER);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from SerialObject a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(SerialObjectListDTO.SELECT_ID, SearchFieldDataTypeEnum.LONG);
            filterField.setFilterCriteria(filter);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, SerialObjectListDTO.class, selectTokens);
    }

    /**
     * Get all serial objects of a given serial object
     * @param id
     * @return a list of serial object objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<SerialObjectSerialObjectsDTO> getSerialObjectsOfSerialObject(long id) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(SerialObjectSerialObjectsDTO.SELECT_SERIALNUMBER);
        selectTokens.add(SerialObjectSerialObjectsDTO.SELECT_ASSEMBLYDATE);
        selectTokens.add(SerialObjectSerialObjectsDTO.SELECT_CUSTOMERSERIALNUMBER);
        selectTokens.add(SerialObjectSerialObjectsDTO.SELECT_PRODUCTIONORDERNUMBER);
        selectTokens.add(SerialObjectSerialObjectsDTO.SELECT_ID);
        selectTokens.add(SerialObjectSerialObjectsDTO.SELECT_CREATIONDATE);
        selectTokens.add(SerialObjectSerialObjectsDTO.SELECT_LASTUPDATE);
        selectTokens.add(SerialObjectSerialObjectsDTO.SELECT_MATERIALMATERIALNUMBER);
        selectTokens.add(SerialObjectSerialObjectsDTO.SELECT_MATERIALID);
        selectTokens.add(SerialObjectSerialObjectsDTO.SELECT_MATERIALSAPNUMBER);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(DEFAULT_LIST_SIZE);
        searchObj.setFromClause("from SerialObject x join x.serialObjects a join a.material b");

        final var parentFilterField = searchObj.addSearchField("x.id", SearchFieldDataTypeEnum.LONG);
        parentFilterField.setFilterCriteria(Long.toString(id));

        return repository.search(searchObj, SerialObjectSerialObjectsDTO.class, selectTokens);
    }

    /**
     * Delete existing serial object
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteSerialObject(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected serial object
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws UniqueConstraintViolationException if a unique constraint check has failed
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final SerialObject sourceObject = repository.findById(sourceObjectId);
        final SerialObject targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

    /**
     * Find serial object by its ID
     * @param id
     * @return the serial object object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SerialObjectDTO findSerialObjectById(long id) {
        // Find persistent object
        final SerialObject serialObject = repository.findById(id, true);

        final var dto = new SerialObjectDTO();
        dto.setSerialNumber(serialObject.getSerialNumber());
        dto.setAssemblyDate(serialObject.getAssemblyDate());
        dto.setCustomerSerialNumber(serialObject.getCustomerSerialNumber());
        dto.setProductionOrderNumber(serialObject.getProductionOrderNumber());
        dto.setId(serialObject.getId());
        dto.setVersion(serialObject.getVersion());
        dto.setCreationDate(serialObject.getCreationDate());
        dto.setLastUpdate(serialObject.getLastUpdate());
        dto.setMaterial(new MaterialListDTO());
        dto.getMaterial().setId(serialObject.getMaterial().getId());
        dto.getMaterial().setMaterialNumber(serialObject.getMaterial().getMaterialNumber());

        if (serialObject.getParentObject() != null) {
            dto.setParentObject(new SerialObjectListDTO());
            dto.getParentObject().setId(serialObject.getParentObject().getId());
            dto.getParentObject().setSerialNumber(serialObject.getParentObject().getSerialNumber());
        }


        if (serialObject.getTraceBom() != null) {
            dto.setTraceBom(new TraceBoMListDTO());
            dto.getTraceBom().setId(serialObject.getTraceBom().getId());
        }


        if (serialObject.getParentObject() != null)
            dto.setParentObjectSerialNumber(serialObject.getParentObject().getSerialNumber());

        dto.setMaterialMaterialNumber(serialObject.getMaterial().getMaterialNumber());
        dto.setMaterialSapNumber(serialObject.getMaterial().getSapNumber());

        return dto;
    }

    /**
     * Find serial object by its ID
     * @param id
     * @return the serial object object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public SerialObjectListDTO findListSerialObject(long id) {
        // Find persistent object
        final SerialObject serialObject = repository.findById(id, true);

        final var dto = new SerialObjectListDTO();
        dto.setId(serialObject.getId());
        dto.setSerialNumber(serialObject.getSerialNumber());

        return dto;
    }

    /**
     * Search for serial object objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of serial object objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<SerialObjectTraceBoMSearchDTO> searchAllSerialObjectTraceBoMs(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_SERIALNUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_ASSEMBLYDATE);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_CUSTOMERSERIALNUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_PRODUCTIONORDERNUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_ID);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMSUPPLIERCODE);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMSUPPLIERNAME);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMDELIVERYNOTENUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMMATREVID);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMMATREVMATID);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMMATREVMATMATERIALNUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMMATREVMATSAPNUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMMATREVMATMATERIALHIERARCHY);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMMATREVMATSHORTTEXT);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMID);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMMATID);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMMATMATERIALNUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMMATSAPNUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMMATMATERIALHIERARCHY);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMMATSHORTTEXT);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMMATREVREVISIONNUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMPRODUCTIONDATE);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMORDERNUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMLOTNUMBER);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMMANUFACTURERNAME);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMORDERCODE);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMDATECODE);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMQUANTITY);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMINFOFIELD1);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMTBOMITEMINFOFIELD2);
        selectTokens.add(SerialObjectTraceBoMSearchDTO.SELECT_TBOMID);

        searchObj.setFromClause(
                "from SerialObject a left join a.traceBom f join f.materialRevision h join f.supplier i join f.traceBoMItems j join h.material m join j.material u");

        return repository.search(searchObj, SerialObjectTraceBoMSearchDTO.class, selectTokens);
    }

    /**
     * Count serial object objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllSerialObjectTraceBoMs(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from SerialObject a left join a.traceBom f join f.materialRevision h join f.supplier i join f.traceBoMItems j join h.material m join j.material u");

        return repository.count(searchObj);
    }

    /**
     * Search for serial object objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of serial object objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<SerialObjectSearchDTO> searchAllSerialObjects(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(SerialObjectSearchDTO.SELECT_SERIALNUMBER);
        selectTokens.add(SerialObjectSearchDTO.SELECT_ASSEMBLYDATE);
        selectTokens.add(SerialObjectSearchDTO.SELECT_CUSTOMERSERIALNUMBER);
        selectTokens.add(SerialObjectSearchDTO.SELECT_PRODUCTIONORDERNUMBER);
        selectTokens.add(SerialObjectSearchDTO.SELECT_ID);
        selectTokens.add(SerialObjectSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(SerialObjectSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(SerialObjectSearchDTO.SELECT_MATMATERIALNUMBER);
        selectTokens.add(SerialObjectSearchDTO.SELECT_MATID);
        selectTokens.add(SerialObjectSearchDTO.SELECT_MATSAPNUMBER);
        selectTokens.add(SerialObjectSearchDTO.SELECT_MATMATERIALHIERARCHY);
        selectTokens.add(SerialObjectSearchDTO.SELECT_MATSHORTTEXT);
        selectTokens.add(SerialObjectSearchDTO.SELECT_MATOWNERLOCATIONCODE);
        selectTokens.add(SerialObjectSearchDTO.SELECT_MATMATERIALTYPECODE);
        selectTokens.add(SerialObjectSearchDTO.SELECT_MATMATERIALCLASSCODE);

        searchObj.setFromClause("from SerialObject a join a.material b join b.ownerLocation h join b.materialClass i join b.materialType j");

        return repository.search(searchObj, SerialObjectSearchDTO.class, selectTokens);
    }

    /**
     * Count serial object objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllSerialObjects(SearchDTO searchObj) {
        searchObj.setFromClause("from SerialObject a join a.material b join b.ownerLocation h join b.materialClass i join b.materialType j");

        return repository.count(searchObj);
    }

}