package com.kontron.qdw.boundary.material;

import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.DEFAULT_LIST_SIZE;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import java.util.*;
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

}