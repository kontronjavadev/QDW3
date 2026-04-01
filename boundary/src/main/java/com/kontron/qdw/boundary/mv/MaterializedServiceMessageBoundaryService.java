package com.kontron.qdw.boundary.mv;

import java.util.*;
import jakarta.validation.ConstraintViolationException;
import com.kontron.qdw.domain.mv.*;
import com.kontron.qdw.dto.mv.*;
import com.kontron.qdw.repository.mv.*;
import net.sourceforge.jbizmo.commons.search.exception.*;
import jakarta.inject.*;
import jakarta.ejb.*;
import jakarta.annotation.security.*;
import net.sourceforge.jbizmo.commons.search.dto.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class MaterializedServiceMessageBoundaryService {
    @Generated
    private final MaterializedServiceMessageRepository repository;

    /**
     * Default constructor
     */
    @Generated
    public MaterializedServiceMessageBoundaryService() {
        this.repository = null;
    }

    /**
     * Constructor for injecting all required beans
     * @param repository
     */
    @Inject
    @Generated
    public MaterializedServiceMessageBoundaryService(MaterializedServiceMessageRepository repository) {
        this.repository = repository;
    }

    /**
     * Search for materialized service message objects
     * @param searchObj a generic container that holds filter criteria
     * @return a list of materialized service message objects
     * @throws GeneralSearchException if the search operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public List<MaterializedServiceMessageSearchDTO> searchAllMaterializedServiceMessages(SearchDTO searchObj) {
        // Collect the select tokens of all fields that should be fetched
        final var selectTokens = new ArrayList<String>();
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_SERVICEORDER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_SERVICEORDERTYPE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_RMATYPE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_SERVICENAME);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_TASKNAME);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_STATENAME);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_INTERNALARRIVALDATE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_INTERNALSHIPMENTDATE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_BASICSTARTDATE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_BASICENDDATE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_DESIGNATOR);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_DEFECTCOMPONENT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_ANALYSISTEXT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_INTERNALREPORT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_EXTERNALREPORT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERREPORT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_EPIDEMICFAILURE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_ERRORID);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_ORIGIN);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERFAILURE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERCODE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERNAME);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERGROUP);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_COUNTRYCODE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_COUNTRYNAME);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_SUPPLIERCODE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_SUPPLIERNAME);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_SUPPLIERARRIVALDATE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_CUSTOMERSHIPMENTDATE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_FAULTANALYSISCODE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_ERRORCODENAME);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_EXTERNALSUPPLIERCODE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_EXTERNALSUPPLIERNAME);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_DELIVERYNOTENUMBER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_ERRORCODEGROUP);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_SYMPTOMSHORTTEXT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_REPAIRTASKSHORTTEXT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_REPAIRDESCRIPTION);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_OWNERLOCATION);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_ERRORSHORTTEXT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_ID);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_MESERIALOBJECTID);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_PARENTSERIALOBJECTID);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_SERIALNUMBER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_PARENTSERIALNUMBER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_MATERIALNUMBER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALNUMBER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_MATERIALTYPE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALTYPE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_MATERIALSHORTTEXT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALSHORTTEXT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_SAPNUMBER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_PARENTSAPNUMBER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_MATERIALHIERARCHY);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_PARENTMATERIALHIERARCHY);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_REVISIONID);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_PARENTREVISIONID);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_REVISIONNUMBER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_PARENTREVISIONNUMBER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_ASSEMBLYDATE);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_ASSEMBLYPO);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_PLANT);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_MATERIALMATERIALNUMBER);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_SERIALOBJECTID);
        selectTokens.add(MaterializedServiceMessageSearchDTO.SELECT_MATERIALID);

        searchObj.setFromClause("from MaterializedServiceMessage a join a.serialObject b join a.material c");

        return repository.search(searchObj, MaterializedServiceMessageSearchDTO.class, selectTokens);
    }

    /**
     * Count materialized service message objects
     * @param searchObj the query criteria
     * @return the number of objects a query would return
     * @throws GeneralSearchException if the count operation has failed
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long countAllMaterializedServiceMessages(SearchDTO searchObj) {
        searchObj.setFromClause("from MaterializedServiceMessage a join a.serialObject b join a.material c");

        return repository.count(searchObj);
    }

    /**
     * Delete existing materialized service message
     * @param id the ID of the object to be deleted
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void deleteMaterializedServiceMessage(long id) {
        repository.delete(id);
    }

    /**
     * Create copy of selected materialized service message
     * @param sourceObjectId
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of one or more persistent attribute values has failed
     * @return the id of the new object
     */
    @Generated
    @PermitAll
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public long copy(long sourceObjectId, long loggedOnUserId) {
        final MaterializedServiceMessage sourceObject = repository.findById(sourceObjectId);
        final MaterializedServiceMessage targetObject = repository.copy(sourceObject, null, loggedOnUserId);

        return targetObject.getId();
    }

}