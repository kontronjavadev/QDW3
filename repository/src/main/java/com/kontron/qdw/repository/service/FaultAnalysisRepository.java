package com.kontron.qdw.repository.service;

import net.sourceforge.jbizmo.commons.random.*;
import com.kontron.qdw.domain.service.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import jakarta.ejb.*;
import jakarta.validation.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Stateless
public class FaultAnalysisRepository extends AbstractRepository<FaultAnalysis, String> {
    @Generated
    private static final String PARAM_CODE = "code";

    /**
     * Find a persistent fault analysis by using the primary key of the provided object
     * @param faultAnalysis
     * @return the fault analysis or null if the object could not be found
     */
    @Generated
    public FaultAnalysis findById(FaultAnalysis faultAnalysis) {
        return findById(faultAnalysis.getCode());
    }

    /**
     * Create a deep copy of the given fault analysis
     * @param sourceObject
     * @param targetObject
     * @param loggedOnUserId
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     * @return the new fault analysis
     */
    @Generated
    public FaultAnalysis copy(FaultAnalysis sourceObject, FaultAnalysis targetObject, long loggedOnUserId) {
        if (targetObject == null) {
            targetObject = new FaultAnalysis();
        }

        targetObject.setCode(RandomStringGenerator.generateRandomString(50));
        targetObject.setShortText(sourceObject.getShortText());
        targetObject.setComment(sourceObject.getComment());
        targetObject.setMappedTo(sourceObject.getMappedTo());

        targetObject = persist(targetObject, false, false);

        return targetObject;
    }

    /**
     * Get the fault analysis of this fault analysis
     * @param code
     * @return the fault analysis of this fault analysis, or null if it could not be found
     */
    @Generated
    public FaultAnalysis getMappedTo(String code) {
        final TypedQuery<FaultAnalysis> query = em.createNamedQuery(FaultAnalysis.NQ_GET_MAPPEDTO, FaultAnalysis.class);
        query.setParameter(PARAM_CODE, code);

        return query.getResultList().stream().findFirst().orElse(null);
    }

    /**
     * Change the 'mappedTo' attribute of this fault analysis
     * @param code
     * @param mappedTo
     * @throws ConstraintViolationException if the validation of the persistent attributes has failed
     */
    @Generated
    public void setMappedTo(String code, FaultAnalysis mappedTo) {
        final FaultAnalysis bean = findById(code, true);

        bean.setMappedTo(mappedTo);
    }

}