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
import com.kontron.qdw.boundary.service.RMATypeBoundaryService;
import com.kontron.qdw.boundary.service.RepairErrorCodeBoundaryService;
import com.kontron.qdw.boundary.service.RepairStateBoundaryService;
import com.kontron.qdw.boundary.service.RepairTaskBoundaryService;
import com.kontron.qdw.dto.base.CountryListDTO;
import com.kontron.qdw.dto.base.CustomerListDTO;
import com.kontron.qdw.dto.base.MovementTypeListDTO;
import com.kontron.qdw.dto.base.SupplierListDTO;
import com.kontron.qdw.dto.material.MaterialListDTO;
import com.kontron.qdw.dto.material.MaterialListSapDTO;
import com.kontron.qdw.dto.material.MaterialTypeListDTO;
import com.kontron.qdw.dto.service.FaultAnalysisListDTO;
import com.kontron.qdw.dto.service.RMATypeListDTO;
import com.kontron.qdw.dto.service.RepairErrorCodeGroupListDTO;
import com.kontron.qdw.dto.service.RepairErrorCodeListDTO;
import com.kontron.qdw.dto.service.RepairStateListDTO;
import com.kontron.qdw.dto.service.RepairTaskListDTO;

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
        return onCompleteStartsWith(supplierService::findSuppliers, searchText, SupplierListDTO::getName);
    }

    public static List<String> onCompleteCustomerName(CustomerBoundaryService customerBoundaryService, String searchText) {
        return onCompleteStartsWith(customerBoundaryService::findCustomers, searchText, CustomerListDTO::getName);
    }

    public static List<String> onCompleteCountryName(CountryBoundaryService countryService, String searchText) {
        return onCompleteContains(countryService::findCountries, searchText, CountryListDTO::getName);
    }

    public static List<String> onCompleteMatTypeCode(MaterialTypeBoundaryService matTypeService, String searchText) {
        return onCompleteContains(matTypeService::findMaterialTypes, searchText, MaterialTypeListDTO::getCode);
    }

    public static List<String> onCompleteMvtTypeCode(MovementTypeBoundaryService mvtTypeService, String searchText) {
        return onCompleteContains(mvtTypeService::findMovementTypes, searchText, MovementTypeListDTO::getCode);
    }

    public static List<String> onCompleteSymptomCode(FaultAnalysisBoundaryService faultAnalysisService, String searchText) {
        return onCompleteContains(faultAnalysisService::findFaultAnalyses, searchText, FaultAnalysisListDTO::getCode);
    }

    public static List<String> onCompleteRmaTypeCode(RMATypeBoundaryService rMATypeService, String searchText) {
        return onCompleteContains(rMATypeService::findRMATypes, searchText, RMATypeListDTO::getCode);
    }

    public static List<String> onCompleteRepairErrorCodeName(RepairErrorCodeBoundaryService errorCodeService, String searchText) {
        return onCompleteContains(errorCodeService::findRepairErrorCodes, searchText, RepairErrorCodeListDTO::getName);
    }

    public static List<String> onCompleteRepairErrorCodeGroupName(RepairErrorCodeBoundaryService errorCodeService, String searchText) {
        return onCompleteContains(errorCodeService::findRepairErrorCodesGroup, searchText, RepairErrorCodeGroupListDTO::getGroupName);
    }

    public static List<String> onCompleteRepairTaskCode(RepairTaskBoundaryService repairTaskService, String searchText) {
        return onCompleteContains(repairTaskService::findRepairTasks, searchText, RepairTaskListDTO::getCode);
    }

    public static List<String> onCompleteRepairStateName(RepairStateBoundaryService repairStateService, String searchText) {
        return onCompleteContains(repairStateService::findRepairStates, searchText, RepairStateListDTO::getName);
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
