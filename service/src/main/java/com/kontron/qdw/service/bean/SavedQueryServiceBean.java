package com.kontron.qdw.service.bean;

import org.slf4j.*;
import java.util.*;
import java.lang.invoke.*;
import net.sourceforge.jbizmo.commons.jpa.*;
import net.sourceforge.jbizmo.commons.search.dto.SearchDTO;
import com.kontron.qdw.service.*;
import jakarta.ejb.*;
import jakarta.validation.constraints.NotEmpty;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import java.io.*;
import com.kontron.qdw.domain.base.*;

@Stateless
@Local(SavedQueryService.class)
public class SavedQueryServiceBean extends AbstractRepository<SavedQuery, Long> implements SavedQueryService {
    @Generated
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    /**
     * Build query statement
     * @param ownerId
     * @param viewName
     * @param title
     * @param allQueries
     * @return the query statement
     */
    @Generated
    private String buildQueryStatement(long ownerId, String viewName, String title, boolean allQueries) {
        if (title == null || title.isEmpty())
            title = LAST_QUERY_TITLE;

        String statement = "select a from SavedQuery a where a.user.id = " + ownerId;
        statement += " and a.viewName = '" + viewName + "' and a.title ";

        if (allQueries)
            statement += "=";
        else
            statement += "!=";

        return statement += "'" + title + "'";
    }

    /* (non-Javadoc)
     * @see com.kontron.qdw.service.SavedQueryService#getLastQuery(long, java.lang.String)
     */
    @Override
    @Generated
    public SearchDTO getLastQuery(long ownerId, String viewName) {
        return getSavedQuery(ownerId, viewName, null);
    }

    /* (non-Javadoc)
     * @see com.kontron.qdw.service.SavedQueryService#saveQuery(long, java.lang.String, java.lang.String, net.sourceforge.jbizmo.commons.search.dto.SearchDTO)
     */
    @Override
    @Generated
    public void saveQuery(long ownerId, String viewName, String title, SearchDTO query) {
        var ba = new ByteArrayOutputStream();

        try {
            new ObjectOutputStream(ba).writeObject(query);
        }
        catch (final IOException e) {
            throw new IllegalStateException(e);
        }

        final Collection<SavedQuery> savedQueries = search(buildQueryStatement(ownerId, viewName, title, true));

        if (!savedQueries.isEmpty()) {
            if (title == null || title.isEmpty())
                logger.debug("Overwrite last query of view '{}' (owner id: '{}')", viewName, ownerId);
            else
                logger.debug("Overwrite saved query '{}' of view '{}' (owner id: '{}')", title, viewName, ownerId);

            // Overwrite existing query
            savedQueries.forEach(q -> q.setDataObj(ba.toByteArray()));

            return;
        }

        if (title == null || title.isEmpty()) {
            logger.debug("Save last query of view '{}' (owner id: '{}')", viewName, ownerId);

            title = LAST_QUERY_TITLE;
        }
        else
            logger.debug("Save query '{}' of view '{}' (owner id: '{}')", title, viewName, ownerId);

        // Create new entry
        final var savedQuery = new SavedQuery();
        savedQuery.setUser(findById(User.class, ownerId));
        savedQuery.setDataObj(ba.toByteArray());
        savedQuery.setTitle(title);
        savedQuery.setViewName(viewName);

        persist(savedQuery);
    }

    /* (non-Javadoc)
     * @see com.kontron.qdw.service.SavedQueryService#getSavedQuery(long, java.lang.String, java.lang.String)
     */
    @Override
    @Generated
    public SearchDTO getSavedQuery(long ownerId, String viewName, String title) {
        if (title == null || title.isEmpty())
            logger.debug("Get last query of view '{}' (owner id: '{}')", viewName, ownerId);
        else
            logger.debug("Get query '{}' of view '{}' (owner id: '{}')", title, viewName, ownerId);

        final Collection<SavedQuery> savedQueries = search(buildQueryStatement(ownerId, viewName, title, true));

        for (final SavedQuery query : savedQueries)
            try {
                final var ois = new ObjectInputStream(new ByteArrayInputStream(query.getDataObj()));
                return (SearchDTO) ois.readObject();
            }
            catch (final Exception e) {
                throw new IllegalStateException(e);
            }

        return null;
    }

    /* (non-Javadoc)
     * @see com.kontron.qdw.service.SavedQueryService#deleteSavedQuery(long, java.lang.String, java.lang.String)
     */
    @Override
    @Generated
    public void deleteSavedQuery(long ownerId, String viewName, String title) {
        logger.debug("Delete saved query '{}' of view '{}' (owner id: '{}')", title, viewName, ownerId);

        search(buildQueryStatement(ownerId, viewName, title, true)).forEach(this::deleteEntity);
    }

    /* (non-Javadoc)
     * @see com.kontron.qdw.service.SavedQueryService#getSavedQueries(long, java.lang.String)
     */
    @Override
    @Generated
    public Collection<String> getSavedQueries(long ownerId, String viewName) {
        logger.debug("Get saved queries of view '{}' (owner id: '{}')", viewName, ownerId);

        return search(buildQueryStatement(ownerId, viewName, null, false)).stream().map(SavedQuery::getTitle).toList();
    }

    public void renameQuery(long ownerId, String viewName, @NotEmpty String oldTitle, @NotEmpty String newTitle) {
        final List<SavedQuery> savedQueries = search(buildQueryStatement(ownerId, viewName, oldTitle, true));

        if (!savedQueries.isEmpty()) {
            logger.debug("Rename saved query '{}' of view '{}' (owner id: '{}') to '{}'", oldTitle, viewName, ownerId, newTitle);
            savedQueries.getFirst().setTitle(newTitle);
        }
    }

}