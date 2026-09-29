package com.kontron.qdw.ui.view.util;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import jakarta.faces.context.FacesContext;
import net.sourceforge.jbizmo.commons.search.dto.SearchDTO;

/**
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 * @param <I> DTO-Id, etwa Long oder String
 * @param <D> DTO, etwa ProductionInfoSetSearchDTO mit
 *        id vom Typ <I>
 */
public class ViewHelper<I> {

    private static String SEARCH_DTO_KEY = "searchDTO";
    private static String SELECTED_OBJECT_KEY = "selectedObject";

    private Map<String, Object> sessionMap = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();

    public SearchDTO getStoredSearchDTO(Class<?> viewClazz) {
        return (SearchDTO) getStoredData(viewClazz, SEARCH_DTO_KEY);
    }

    public void storeSearchDTO(Class<?> viewClazz, SearchDTO searchDTO) {
        storeData(viewClazz, SEARCH_DTO_KEY, searchDTO);
    }

    public void resetStoredSearchDTO(Class<?> viewClazz) {
        resetData(viewClazz, SEARCH_DTO_KEY);
    }



    @SuppressWarnings("unchecked")
    public I getStoredSelectedObjectId(Class<?> viewClazz) {
        return (I) getStoredData(viewClazz, SELECTED_OBJECT_KEY);
    }

    public void storeSelectedObjectId(Class<?> viewClazz, I selectedObjectId) {
        storeData(viewClazz, SELECTED_OBJECT_KEY, selectedObjectId);
    }

    public void resetStoredSelectedObjectId(Class<?> viewClazz) {
        resetData(viewClazz, SELECTED_OBJECT_KEY);
    }



    @SuppressWarnings("unchecked")
    public List<I> getStoredSelectedObjectIds(Class<?> viewClazz) {
        return (List<I>) getStoredData(viewClazz, SELECTED_OBJECT_KEY);
    }

    public void storeSelectedObjectIds(Class<?> viewClazz, List<I> selectedObjectIds) {
        storeData(viewClazz, SELECTED_OBJECT_KEY, selectedObjectIds);
    }

    public <D> void storeSelectedObjectIds(Class<?> viewClazz, List<D> selectedObjects, Function<D, I> object2IdMapper) {
        List<I> selectedObjectIds = selectedObjects.stream().map(object2IdMapper::apply).collect(Collectors.toList());
        sessionMap.put(viewClazz.getName() + SELECTED_OBJECT_KEY, selectedObjectIds);
    }

    public void resetStoredSelectedObjectIds(Class<?> viewClazz) {
        resetData(viewClazz, SELECTED_OBJECT_KEY);
    }



    public <D> Optional<D> getStoredDataForSelectedObjectId(Class<?> viewClazz, String key, I selectedObjectId) {
        I storedSelectedObjectId = getStoredSelectedObjectId(viewClazz);
        Optional<D> storedSelectedData;
        if (storedSelectedObjectId != null && storedSelectedObjectId.equals(selectedObjectId)) {
            storedSelectedData = Optional.ofNullable(getStoredData(viewClazz, key));
        }
        else {
            resetStoredDataForSelectedObjectId(viewClazz, key);
            storedSelectedData = Optional.empty();
        }

        return storedSelectedData;
    }

    public <D> void storeDataForSelectedObjectId(Class<?> viewClazz, String key, D data, I selectedObjectId) {
        storeSelectedObjectId(viewClazz, selectedObjectId);
        storeData(viewClazz, key, data);
    }

    public void resetStoredDataForSelectedObjectId(Class<?> viewClazz, String key) {
        resetStoredSelectedObjectId(viewClazz);
        resetData(viewClazz, key);
    }



    @SuppressWarnings("unchecked")
    public <D> D getStoredData(Class<?> viewClazz, String key) {
        return (D) sessionMap.get(viewClazz.getName() + "!" + key);
    }

    public <D> void storeData(Class<?> viewClazz, String key, D data) {
        sessionMap.put(viewClazz.getName() + "!" + key, data);
    }

    public void resetData(Class<?> viewClazz, String key) {
        sessionMap.put(viewClazz.getName() + "!" + key, null);
    }

}
