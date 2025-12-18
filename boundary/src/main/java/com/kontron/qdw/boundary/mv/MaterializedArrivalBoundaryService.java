package com.kontron.qdw.boundary.mv;

import java.util.*;
import jakarta.validation.ConstraintViolationException;
import net.sourceforge.jbizmo.commons.search.exception.GeneralSearchException;
import com.kontron.qdw.domain.mv.*;
import com.kontron.qdw.dto.mv.*;
import com.kontron.qdw.repository.mv.*;
import net.sourceforge.jbizmo.commons.search.dto.SearchDTO;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class MaterializedArrivalBoundaryService {
    @Generated
    private final MaterializedArrivalRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterializedArrivalBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterializedArrivalBoundaryService(MaterializedArrivalRepository repository) {
        this.repository = repository;
    }

    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterializedArrivalSearchDTO> searchAllArrivals(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_ID);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PLANT);

        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_SERIALOBJECTID);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_SERIALNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTSERIALOBJECTID);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTSERIALNUMBER);

        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_ORDERNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_ARRIVALDATE);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_ASSEMBLYDATE);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_ASSEMBLYPO);

        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_SUPPLIERCODE);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_SUPPLIERNAME);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_COUNTRYCODE);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_COUNTRYNAME);

        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_REVISIONID);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_REVISIONNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTREVISIONID);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTREVISIONNUMBER);

        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MATERIALNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_SAPNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MATERIALSHORTTEXT);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MATERIALHIERARCHY);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MATERIALTYPE);

        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTSAPNUMBER);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALSHORTTEXT);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALHIERARCHY);
        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_PARENTMATERIALTYPE);

        selectTokens.add(MaterializedArrivalSearchDTO.SELECT_MOVEMENTTYPE);

        searchObj.setFromClause("from MaterializedArrival a ");

        return repository.search(searchObj, MaterializedArrivalSearchDTO.class, selectTokens);
    }

    /**
     * Count arrival objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllArrivals(SearchDTO searchObj) {
        searchObj.setFromClause("from MaterializedArrival a ");
        return repository.count(searchObj);
    }

    /**
     * @return a list of materialized arrivals objects
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterializedArrivalSearchDTO> findAll() {
        final var resultList = new ArrayList<MaterializedArrivalSearchDTO>();

        for (final MaterializedArrival materializedArrival : repository.findAll()) {
            final var dto = new MaterializedArrivalSearchDTO();
            dto.setId(materializedArrival.getId());
            dto.setPlant(materializedArrival.getPlant());
            dto.setSerialObjectId(materializedArrival.getSerialObjectId());
            dto.setSerialNumber(materializedArrival.getSerialNumber());
            dto.setParentSerialObjectId(materializedArrival.getParentSerialObjectId());
            dto.setParentSerialNumber(materializedArrival.getParentSerialNumber());
            dto.setOrderNumber(materializedArrival.getOrderNumber());
            dto.setArrivalDate(materializedArrival.getArrivalDate());
            dto.setAssemblyDate(materializedArrival.getAssemblyDate());
            dto.setAssemblyPO(materializedArrival.getAssemblyPO());
            dto.setSupplierCode(materializedArrival.getSupplierCode());
            dto.setSupplierName(materializedArrival.getSupplierName());
            dto.setCountryCode(materializedArrival.getCountryCode());
            dto.setCountryName(materializedArrival.getCountryName());
            dto.setRevisionId(materializedArrival.getRevisionId());
            dto.setRevisionNumber(materializedArrival.getRevisionNumber());
            dto.setParentRevisionId(materializedArrival.getParentRevisionId());
            dto.setParentRevisionNumber(materializedArrival.getParentRevisionNumber());
            dto.setMaterialNumber(materializedArrival.getMaterialNumber());
            dto.setSapNumber(materializedArrival.getSapNumber());
            dto.setMaterialShortText(materializedArrival.getMaterialShortText());
            dto.setMaterialHierarchy(materializedArrival.getMaterialHierarchy());
            dto.setMaterialType(materializedArrival.getMaterialType());
            dto.setParentMaterialNumber(materializedArrival.getParentMaterialNumber());
            dto.setParentSapNumber(materializedArrival.getParentSapNumber());
            dto.setParentMaterialShortText(materializedArrival.getParentMaterialShortText());
            dto.setParentMaterialHierarchy(materializedArrival.getParentMaterialHierarchy());
            dto.setParentMaterialType(materializedArrival.getParentMaterialType());
            dto.setMovementType(materializedArrival.getMovementType());

            resultList.add(dto);
        }

        return resultList;
    }

    /**
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void delete(long id) {
        repository.delete(id);
    }

    /**
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final MaterializedArrival sourceObject = repository.findById(sourceObjectId);
        final MaterializedArrival targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}
