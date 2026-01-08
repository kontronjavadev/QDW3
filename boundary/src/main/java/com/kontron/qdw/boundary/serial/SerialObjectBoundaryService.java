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

}