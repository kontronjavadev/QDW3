package com.kontron.qdw.boundary.serial;

import com.kontron.qdw.domain.serial.*;
import java.util.*;
import com.kontron.qdw.dto.serial.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import com.kontron.qdw.repository.serial.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
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

}