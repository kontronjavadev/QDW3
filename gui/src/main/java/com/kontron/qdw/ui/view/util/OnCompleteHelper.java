package com.kontron.qdw.ui.view.util;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.base.CountryBoundaryService;
import com.kontron.qdw.boundary.base.MovementTypeBoundaryService;
import com.kontron.qdw.boundary.base.SupplierBoundaryService;
import com.kontron.qdw.boundary.material.MaterialBoundaryService;
import com.kontron.qdw.boundary.material.MaterialTypeBoundaryService;
import com.kontron.qdw.dto.base.CountryListDTO;
import com.kontron.qdw.dto.base.MovementTypeListDTO;
import com.kontron.qdw.dto.base.SupplierListDTO;
import com.kontron.qdw.dto.material.MaterialListDTO;
import com.kontron.qdw.dto.material.MaterialListSapDTO;
import com.kontron.qdw.dto.material.MaterialTypeListDTO;

public final class OnCompleteHelper {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private OnCompleteHelper() {
        // private ctor für Helperklassen
    }

    public static List<String> onCompleteMaterialNumber(MaterialBoundaryService materialService, String searchText) {
        final var results = new ArrayList<String>();

        try {
            final Collection<MaterialListDTO> items = materialService.findMaterials(searchText + "%");

            for (final MaterialListDTO item : items) {
                results.add(item.getMaterialNumber());
            }
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", searchText, e);
        }

        return results;
    }

    public static List<String> onCompleteSapNumber(MaterialBoundaryService materialService, String searchText) {
        final var results = new ArrayList<String>();

        try {
            final Collection<MaterialListSapDTO> items = materialService.findMaterialsBySapNumber(searchText + "%");

            for (final MaterialListSapDTO item : items) {
                results.add(item.getSapNumber());
            }
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", searchText, e);
        }

        return results;
    }

    public static List<String> onCompleteSupplierName(SupplierBoundaryService supplierService, String searchText) {
        final var results = new ArrayList<String>();

        try {
            final Collection<SupplierListDTO> items = supplierService.findSuppliers(searchText + "%");

            for (final SupplierListDTO item : items) {
                results.add(item.getName());
            }
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", searchText, e);
        }

        return results;
    }

    public static List<String> onCompleteCountry(CountryBoundaryService countryService, String searchText) {
        final var results = new ArrayList<String>();

        try {
            final Collection<CountryListDTO> items = countryService.findCountries("%" + searchText + "%");

            for (final CountryListDTO item : items) {
                results.add(item.getName());
            }
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", searchText, e);
        }

        return results;
    }

    public static List<String> onCompleteMatType(MaterialTypeBoundaryService matTypeService, String searchText) {
        final var results = new ArrayList<String>();

        try {
            final Collection<MaterialTypeListDTO> items = matTypeService.findMaterialTypes("%" + searchText + "%");

            for (final MaterialTypeListDTO item : items) {
                results.add(item.getCode());
            }
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", searchText, e);
        }

        return results;
    }

    public static List<String> onCompleteMvtType(MovementTypeBoundaryService mvtTypeService, String searchText) {
        final var results = new ArrayList<String>();

        try {
            final Collection<MovementTypeListDTO> items = mvtTypeService.findMovementTypes("%" + searchText + "%");

            for (final MovementTypeListDTO item : items) {
                results.add(item.getCode());
            }
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!", searchText, e);
        }

        return results;
    }

}
