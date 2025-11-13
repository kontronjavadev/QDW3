package com.kontron.qdw.service;

import java.util.*;
import net.sourceforge.jbizmo.commons.search.dto.SearchDTO;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public interface SavedQueryService {
    @Generated
    String LAST_QUERY_TITLE = "LAST_QUERY";

    /**
     * Get last query of given view and user
     * @param ownerId
     * @param viewName
     * @return the search object
     */
    @Generated
    SearchDTO getLastQuery(long ownerId, String viewName);

    /**
     * Save query
     * @param ownerId
     * @param viewName
     * @param title
     * @param query
     */
    @Generated
    void saveQuery(long ownerId, String viewName, String title, SearchDTO query);

    /**
     * Get saved query
     * @param ownerId
     * @param viewName
     * @param title
     * @return the search object
     */
    @Generated
    SearchDTO getSavedQuery(long ownerId, String viewName, String title);

    /**
     * Delete saved query
     * @param ownerId
     * @param viewName
     * @param title
     */
    @Generated
    void deleteSavedQuery(long ownerId, String viewName, String title);

    /**
     * Get all saved queries of given view and user
     * @param ownerId
     * @param viewName
     * @return a string collection of saved query titles
     */
    @Generated
    Collection<String> getSavedQueries(long ownerId, String viewName);

}