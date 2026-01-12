package com.kontron.qdw.boundary.serial;

import com.kontron.qdw.domain.serial.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.DEFAULT_LIST_SIZE;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.WILDCARD;
import com.kontron.qdw.dto.base.*;
import java.util.*;
import com.kontron.qdw.dto.serial.*;
import com.kontron.qdw.dto.material.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import com.kontron.qdw.repository.serial.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import static net.sourceforge.jbizmo.commons.jpa.AbstractRepository.SMALL_LIST_SIZE;

@Stateless
public class TraceBoMBoundaryService {
    @Generated
    private final TraceBoMRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public TraceBoMBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public TraceBoMBoundaryService(TraceBoMRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for trace BoM objects
     * @param filter
     * @return a list of trace BoM objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<TraceBoMListDTO> findTraceBoMs(String filter) {
        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD))
            try {
                Long.parseLong(filter);
            }
            catch (NumberFormatException e) {
                return Collections.emptyList();
            }

        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(TraceBoMListDTO.SELECT_ID);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(SMALL_LIST_SIZE);
        searchObj.setFromClause("from TraceBoM a");

        if (filter != null && !filter.isEmpty() && !filter.equals(WILDCARD)) {
            final var filterField = searchObj.addSearchField(TraceBoMListDTO.SELECT_ID, SearchFieldDataTypeEnum.LONG);
            filterField.setFilterCriteria(filter);
            filterField.setSortIndex(1);
            filterField.setSortOrder(SortDirectionEnum.ASC);
        }

        return repository.search(searchObj, TraceBoMListDTO.class, selectTokens);
    }

    /**
     * Get all trace BoM items of a given trace BoM
     * @param id
     * @return a list of trace BoM item objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<TraceBoMTraceBoMItemsDTO> getTraceBoMItemsOfTraceBoM(long id) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_DATECODE);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_INFOFIELD1);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_INFOFIELD2);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_INFOFIELD3);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_INFOFIELD4);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_MANUFACTURERNAME);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_MANUFACTURERREVISION);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_ORDERCODE);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_QUANTITY);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_ID);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_CREATIONDATE);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_LASTUPDATE);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_MATERIALMATERIALNUMBER);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_MATERIALID);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_TRACEBOMID);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_MATERIALSAPNUMBER);
        selectTokens.add(TraceBoMTraceBoMItemsDTO.SELECT_MATERIALSHORTTEXT);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(DEFAULT_LIST_SIZE);
        searchObj.setFromClause("from TraceBoM x join x.traceBoMItems a join a.material b join a.traceBom c");

        final var parentFilterField = searchObj.addSearchField("x.id", SearchFieldDataTypeEnum.LONG);
        parentFilterField.setFilterCriteria(Long.toString(id));

        return repository.search(searchObj, TraceBoMTraceBoMItemsDTO.class, selectTokens);
    }

    /**
     * Get all illegal trace BoM items of a given trace BoM
     * @param id
     * @return a list of illegal trace BoM item objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<TraceBoMIllegalTraceBoMItemsDTO> getIllegalTraceBoMItemsOfTraceBoM(long id) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(TraceBoMIllegalTraceBoMItemsDTO.SELECT_DATECODE);
        selectTokens.add(TraceBoMIllegalTraceBoMItemsDTO.SELECT_MANUFACTURER);
        selectTokens.add(TraceBoMIllegalTraceBoMItemsDTO.SELECT_MANUFACTURERREVISION);
        selectTokens.add(TraceBoMIllegalTraceBoMItemsDTO.SELECT_MATERIALNUMBER);
        selectTokens.add(TraceBoMIllegalTraceBoMItemsDTO.SELECT_ORDERCODE);
        selectTokens.add(TraceBoMIllegalTraceBoMItemsDTO.SELECT_ID);
        selectTokens.add(TraceBoMIllegalTraceBoMItemsDTO.SELECT_CREATIONDATE);
        selectTokens.add(TraceBoMIllegalTraceBoMItemsDTO.SELECT_LASTUPDATE);
        selectTokens.add(TraceBoMIllegalTraceBoMItemsDTO.SELECT_TRACEBOMID);

        // Initialize the search object
        final var searchObj = new SearchDTO();
        searchObj.setExactFilterMatch(true);
        searchObj.setCaseSensitive(true);
        searchObj.setMaxResult(DEFAULT_LIST_SIZE);
        searchObj.setFromClause("from TraceBoM x join x.illegalTraceBoMItems a join a.traceBom b");

        final var parentFilterField = searchObj.addSearchField("x.id", SearchFieldDataTypeEnum.LONG);
        parentFilterField.setFilterCriteria(Long.toString(id));

        return repository.search(searchObj, TraceBoMIllegalTraceBoMItemsDTO.class, selectTokens);
    }

    /**
     * Find trace BoM by its ID
     * @param id
     * @return the trace BoM object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public TraceBoMDTO findTraceBoMById(long id) {
        // Find persistent object
        final TraceBoM traceBoM = repository.findById(id, true);

        final var dto = new TraceBoMDTO();
        dto.setDeliveryNoteNumber(traceBoM.getDeliveryNoteNumber());
        dto.setLotNumber(traceBoM.getLotNumber());
        dto.setOrderNumber(traceBoM.getOrderNumber());
        dto.setProductionDate(traceBoM.getProductionDate());
        dto.setId(traceBoM.getId());
        dto.setVersion(traceBoM.getVersion());
        dto.setCreationDate(traceBoM.getCreationDate());
        dto.setLastUpdate(traceBoM.getLastUpdate());
        dto.setMaterialRevision(new MaterialRevisionListDTO());
        dto.getMaterialRevision().setId(traceBoM.getMaterialRevision().getId());
        dto.getMaterialRevision().setRevisionNumber(traceBoM.getMaterialRevision().getRevisionNumber());
        dto.setSupplier(new SupplierListDTO());
        dto.getSupplier().setCode(traceBoM.getSupplier().getCode());
        dto.getSupplier().setName(traceBoM.getSupplier().getName());
        dto.setMaterialRevisionMaterial(new MaterialListDTO());
        dto.getMaterialRevisionMaterial().setId(traceBoM.getMaterialRevision().getMaterial().getId());
        dto.getMaterialRevisionMaterial().setMaterialNumber(traceBoM.getMaterialRevision().getMaterial().getMaterialNumber());
        dto.setMaterialSapNumber(traceBoM.getMaterialRevision().getMaterial().getSapNumber());
        dto.setMaterialShortText(traceBoM.getMaterialRevision().getMaterial().getShortText());

        return dto;
    }

}