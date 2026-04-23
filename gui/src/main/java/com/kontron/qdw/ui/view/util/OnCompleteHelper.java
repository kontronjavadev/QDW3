package com.kontron.qdw.ui.view.util;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.base.CountryBoundaryService;
import com.kontron.qdw.boundary.base.CustomerBoundaryService;
import com.kontron.qdw.boundary.base.MovementTypeBoundaryService;
import com.kontron.qdw.boundary.base.SupplierBoundaryService;
import com.kontron.qdw.boundary.material.MaterialBoundaryService;
import com.kontron.qdw.boundary.material.MaterialTypeBoundaryService;
import com.kontron.qdw.boundary.service.FaultAnalysisBoundaryService;
import com.kontron.qdw.dto.base.CountryListDTO;
import com.kontron.qdw.dto.base.CustomerListDTO;
import com.kontron.qdw.dto.base.MovementTypeListDTO;
import com.kontron.qdw.dto.base.SupplierListDTO;
import com.kontron.qdw.dto.material.MaterialListDTO;
import com.kontron.qdw.dto.material.MaterialListSapDTO;
import com.kontron.qdw.dto.material.MaterialTypeListDTO;
import com.kontron.qdw.dto.service.FaultAnalysisListDTO;

public final class OnCompleteHelper {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private OnCompleteHelper() {
        // private ctor für Helperklassen
    }

    public static List<String> onCompleteMaterialNumber(MaterialBoundaryService materialService, String searchText) {
        return onCompleteStartsWith(materialService::findMaterials, searchText, MaterialListDTO::getMaterialNumber);
    }

    public static List<String> onCompleteSapNumber(MaterialBoundaryService materialService, String searchText) {
        return onCompleteStartsWith(materialService::findMaterialsBySapNumber, searchText, MaterialListSapDTO::getSapNumber);
    }

    public static List<String> onCompleteSupplierName(SupplierBoundaryService supplierService, String searchText) {
        return onCompleteStartsWith(supplierService::findSuppliers, searchText, SupplierListDTO::getCode);
    }

    public static List<String> onCompleteCustomerName(CustomerBoundaryService customerBoundaryService, String searchText) {
        return onCompleteStartsWith(customerBoundaryService::findCustomers, searchText, CustomerListDTO::getCode);
    }

    public static List<String> onCompleteCountry(CountryBoundaryService countryService, String searchText) {
        return onCompleteContains(countryService::findCountries, searchText, CountryListDTO::getCode);
    }

    public static List<String> onCompleteMatType(MaterialTypeBoundaryService matTypeService, String searchText) {
        return onCompleteContains(matTypeService::findMaterialTypes, searchText, MaterialTypeListDTO::getCode);
    }

    public static List<String> onCompleteMvtType(MovementTypeBoundaryService mvtTypeService, String searchText) {
        return onCompleteContains(mvtTypeService::findMovementTypes, searchText, MovementTypeListDTO::getCode);
    }

    public static List<String> onCompleteSymptom(FaultAnalysisBoundaryService faultAnalysisService, String searchText) {
        return onCompleteContains(faultAnalysisService::findFaultAnalyses, searchText, FaultAnalysisListDTO::getCode);
    }



    private static <D, R> List<R> onCompleteStartsWith(Function<String, Collection<D>> search, String searchText, Function<D, R> resultMapper) {
        return onComplete(search, () -> searchText + "%", resultMapper);
    }

    private static <D, R> List<R> onCompleteContains(Function<String, Collection<D>> search, String searchText, Function<D, R> resultMapper) {
        return onComplete(search, () -> "%" + searchText + "%", resultMapper);
    }

    private static <D, R> List<R> onComplete(Function<String, Collection<D>> search,
            Supplier<String> searchTextSupplier,
            Function<D, R> resultMapper) {
        try {
            Collection<D> items = search.apply(searchTextSupplier.get());
            return items.stream()
                    .map(resultMapper)
                    .collect(Collectors.toCollection(ArrayList::new));
        }
        catch (final Exception e) {
            logger.error("Error while searching for auto-complete items by using the entered text '{}'!",
                    searchTextSupplier.get().replace("%", ""), e);
            return Collections.emptyList();
        }
    }

}
