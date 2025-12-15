package com.kontron.qdw.boundary.material;

import com.kontron.qdw.domain.material.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.DEFAULT_LIST_SIZE;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import com.kontron.qdw.dto.base.*;
import com.kontron.qdw.domain.base.*;
import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.repository.material.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class MaterialRevisionBoundaryService {
    @Generated
    private final MaterialRevisionRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterialRevisionBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterialRevisionBoundaryService(MaterialRevisionRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for material revision objects
     * @param filter
     * @return a list of material revision objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialRevisionListDTO> findMaterialRevisions(String filter) {
        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD))
            try {
                Long.parseLong(filter);
            }
            catch (NumberFormatException e) {
                return Collections.emptyList();
            }

        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialRevisionListDTO.SELECT_ID);
        selectTokens.add(MaterialRevisionListDTO.SELECT_REVISIONNUMBER);
        selectTokens.add(MaterialRevisionListDTO.SELECT_PLANTCODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from MaterialRevision a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(MaterialRevisionListDTO.SELECT_ID, SearchFieldDataTypeEnum.LONG);
            filterField.setFilterCriteria(filter);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, MaterialRevisionListDTO.class, selectTokens);
    }

    /**
     * Get all bom items of a given material revision
     * @param id
     * @return a list of bom item objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialRevisionBoMItemsDTO> getBoMItemsOfMaterialRevision(long id) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_QUANTITY);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_LABEL);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_POSITION);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_ID);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_CREATIONDATE);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_LASTUPDATE);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_MATERIALREVISIONID);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_MATERIALID);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_MATERIALMATERIALNUMBER);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_MATERIALSAPNUMBER);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_MATERIALSHORTTEXT);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_MATERIALTYPECODE);
        selectTokens.add(MaterialRevisionBoMItemsDTO.SELECT_MATERIALCLASSCODE);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(DEFAULT_LIST_SIZE);
        searchObj.setFromClause(
                "from MaterialRevision x join x.boMItems a left join a.material b left join a.materialRevision c left join b.materialClass e left join b.materialType f");

        final var parentFilterField = searchObj.addSearchField("x.id", SearchFieldDataTypeEnum.LONG);
        parentFilterField.setFilterCriteria(Long.toString(id));

        return repository.search(searchObj, MaterialRevisionBoMItemsDTO.class, selectTokens);
    }

    /**
     * Update existing material revision object
     * @param object the material revision to update
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void updateMaterialRevision(MaterialRevisionUpdateDTO object) {
        // Find and attach object
        MaterialRevision materialRevision = repository.findById(object.getId(), true);

        materialRevision.setRevisionNumber(object.getRevisionNumber() != null ? object.getRevisionNumber().trim() : null);
        materialRevision.setRev2(object.getRev2() != null ? object.getRev2().trim() : null);
        materialRevision.setRev6(object.getRev6() != null ? object.getRev6().trim() : null);
        materialRevision.setAlternativeNumber(object.getAlternativeNumber() != null ? object.getAlternativeNumber().trim() : null);
        materialRevision.setComment(object.getComment() != null ? object.getComment().trim() : null);
        materialRevision.setVersion(object.getVersion());
        materialRevision.setMaterial(repository.getReference(Material.class, object.getMaterial().getId()));
        materialRevision.setPlant(repository.getReference(Plant.class, object.getPlant().getCode()));

        repository.merge(materialRevision, false);
    }

    /**
     * Find material revision by its ID
     * @param id
     * @return the material revision object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialRevisionUpdateDTO getMaterialRevisionForUpdate(long id) {
        // Find persistent object
        final MaterialRevision materialRevision = repository.findById(id, true);

        final var dto = new MaterialRevisionUpdateDTO();
        dto.setRevisionNumber(materialRevision.getRevisionNumber());
        dto.setRev2(materialRevision.getRev2());
        dto.setRev6(materialRevision.getRev6());
        dto.setAlternativeNumber(materialRevision.getAlternativeNumber());
        dto.setComment(materialRevision.getComment());
        dto.setId(materialRevision.getId());
        dto.setVersion(materialRevision.getVersion());
        dto.setCreationDate(materialRevision.getCreationDate());
        dto.setLastUpdate(materialRevision.getLastUpdate());
        dto.setMaterial(new MaterialListDTO());
        dto.getMaterial().setId(materialRevision.getMaterial().getId());
        dto.getMaterial().setMaterialNumber(materialRevision.getMaterial().getMaterialNumber());
        dto.setPlant(new PlantListDTO());
        dto.getPlant().setCode(materialRevision.getPlant().getCode());

        return dto;
    }

    /**
     * Find material revision by its ID
     * @param id
     * @return the material revision object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialRevisionDTO findMaterialRevisionById(long id) {
        // Find persistent object
        final MaterialRevision materialRevision = repository.findById(id, true);

        final var dto = new MaterialRevisionDTO();
        dto.setRevisionNumber(materialRevision.getRevisionNumber());
        dto.setRev2(materialRevision.getRev2());
        dto.setRev6(materialRevision.getRev6());
        dto.setAlternativeNumber(materialRevision.getAlternativeNumber());
        dto.setComment(materialRevision.getComment());
        dto.setId(materialRevision.getId());
        dto.setVersion(materialRevision.getVersion());
        dto.setCreationDate(materialRevision.getCreationDate());
        dto.setLastUpdate(materialRevision.getLastUpdate());
        dto.setMaterial(new MaterialListDTO());
        dto.getMaterial().setId(materialRevision.getMaterial().getId());
        dto.getMaterial().setMaterialNumber(materialRevision.getMaterial().getMaterialNumber());
        dto.setPlant(new PlantListDTO());
        dto.getPlant().setCode(materialRevision.getPlant().getCode());

        return dto;
    }

    /**
     * Search for material revision objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of material revision objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialRevisionSearchDTO> searchAllMaterialRevisions(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_REVISIONNUMBER);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_REV2);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_REV6);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_ALTERNATIVENUMBER);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_COMMENT);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_ID);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_CREATIONDATE);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_LASTUPDATE);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_MATERIALMATERIALNUMBER);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_PLANTCODE);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_MATERIALID);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_MATERIALSAPNUMBER);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_MATERIALSHORTTEXT);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_OWNERLOCATIONCODE);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_MATERIALTYPECODE);
        selectTokens.add(MaterialRevisionSearchDTO.SELECT_MATERIALCLASSCODE);

        searchObj.setFromClause(
                "from MaterialRevision a join a.material b join a.plant c join b.ownerLocation e join b.materialClass f join b.materialType g");

        return repository.search(searchObj, MaterialRevisionSearchDTO.class, selectTokens);
    }

    /**
     * Count material revision objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllMaterialRevisions(SearchDTO searchObj) {
        searchObj.setFromClause(
                "from MaterialRevision a join a.material b join a.plant c join b.ownerLocation e join b.materialClass f join b.materialType g");

        return repository.count(searchObj);
    }

    /**
     * Delete existing material revision
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteMaterialRevision(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected material revision
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final MaterialRevision sourceObject = repository.findById(sourceObjectId);
        final MaterialRevision targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

    /**
     * Find material revision by its ID
     * @param id
     * @return the material revision object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialRevisionListDTO findListMaterialRevision(long id) {
        // Find persistent object
        final MaterialRevision materialRevision = repository.findById(id, true);

        final var dto = new MaterialRevisionListDTO();
        dto.setId(materialRevision.getId());
        dto.setRevisionNumber(materialRevision.getRevisionNumber());
        dto.setPlantCode(materialRevision.getPlant().getCode());

        return dto;
    }

}