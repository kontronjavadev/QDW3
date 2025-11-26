package com.kontron.qdw.boundary.material;

import java.util.*;
import com.kontron.qdw.repository.material.*;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.dto.material.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class MaterialBoundaryService {
    @Generated
    private final MaterialRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterialBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterialBoundaryService(MaterialRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for material objects
     * @param filter
     * @return a list of material objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterialListDTO> findMaterials(String filter) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterialListDTO.SELECT_ID);
        selectTokens.add(MaterialListDTO.SELECT_MATERIALNUMBER);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from Material a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(MaterialListDTO.SELECT_MATERIALNUMBER, SearchFieldDataTypeEnum.STRING);
            filterField.setFilterCriteria(filter + WILDCARD);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, MaterialListDTO.class, selectTokens);
    }

    /**
     * Find material by its ID
     * @param id
     * @return the material object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public MaterialListDTO findListMaterial(long id) {
        // Find persistent object
        final Material material = repository.findById(id, true);

        final var dto = new MaterialListDTO();
        dto.setId(material.getId());
        dto.setMaterialNumber(material.getMaterialNumber());

        return dto;
    }

}