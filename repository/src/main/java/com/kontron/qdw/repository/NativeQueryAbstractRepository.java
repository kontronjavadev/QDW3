package com.kontron.qdw.repository;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import net.sourceforge.jbizmo.commons.jpa.util.JPAQueryStatementGenerator;
import net.sourceforge.jbizmo.commons.search.dto.SearchDTO;
import net.sourceforge.jbizmo.commons.search.exception.GeneralSearchException;

@Stateless
public class NativeQueryAbstractRepository {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    private @PersistenceContext EntityManager em;

    /*
     * Benutzung:
        searchObj.setFromClause("from qdw.arrival_tab a "
                + "join qdw.material_revision_tab b on a.material_revision = b.id "
                + "join qdw.serial_object_tab e on a.serial_object = e.id "
                + "join qdw.supplier_tab f on a.supplier = f.code "
                + "join qdw.material_tab l on b.material = l.id ");
    
        und entsprechend die Felder im DTO:
        SELECT_ARRIVALDATE = "a.arrival_date";
     */

    public <X> List<X> search(SearchDTO searchObj, Class<X> type, List<String> selectTokens) {
        if (selectTokens != null) {
            String selectedFields = selectTokens.stream()
                    .collect(Collectors.joining(","));
            searchObj.setFromClause("select " + selectedFields + " " + searchObj.getFromClause().trim());
        }

        final String statement = JPAQueryStatementGenerator.createStatement(searchObj);

        try {
            final Map<String, Object> addParams = JPAQueryStatementGenerator.createParameters(searchObj);

            return search(statement, searchObj.getMaxResult(), searchObj.getStartIndex(), addParams, type);
        }
        catch (final Exception e) {
            logger.error("Error while performing query with statement '{}'!", statement, e);

            throw new GeneralSearchException(e, true);
        }
    }

    @SuppressWarnings("unchecked")
    private <X> List<X> search(String statement, int maxResults, int startIndex, Map<String, Object> addParams, Class<X> type) {
        final Query query = em.createNativeQuery(statement, type);
        query.setMaxResults(maxResults);
        query.setFirstResult(startIndex);

        if (addParams != null) {
            addParams.forEach(query::setParameter);
        }

        return query.getResultList();
    }

    public long count(SearchDTO searchObj) {
        String statement = JPAQueryStatementGenerator.createCountStatement(searchObj);
        statement = statement.replace(JPAQueryStatementGenerator.COUNT, "count(*) ");
        final var errorMsg = "Error while parsing additional query parameters!";
        Map<String, Object> addParams = null;

        try {
            addParams = JPAQueryStatementGenerator.createParameters(searchObj);
        }
        catch (final Exception e) {
            logger.error(errorMsg, e);
        }

        if (addParams == null) {
            throw new IllegalStateException(errorMsg);
        }

        final Query query = em.createNativeQuery(statement, Long.class);

        addParams.forEach(query::setParameter);

        return (Long) query.getSingleResult();
    }

}
