package com.kontron.qdw.ui.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.kontron.qdw.dto.base.CustomerDTO;
import com.kontron.qdw.dto.base.PlantSearchDTO;
import com.kontron.qdw.dto.base.SupplierDTO;
import com.kontron.qdw.dto.material.MaterialNumberSetDTO;
import com.kontron.qdw.dto.material.SerialNumberSetDTO;
import com.kontron.qdw.dto.service.RepairErrorCodeListDTO;

public class FieldPerfReportSearchObject {

    private List<String> matNrs = new ArrayList<>();

    private List<CustomerDTO> customers = new ArrayList<>();

    private List<SupplierDTO> supplier = new ArrayList<>();

    private List<String> rmaTypes = new ArrayList<>();
    private List<String> selectedRmaTypes = new ArrayList<>();

    private List<MaterialNumberSetDTO> matNrSets = new ArrayList<>();
    private MaterialNumberSetDTO selectedMatNrSet;

    private List<SerialNumberSetDTO> serNrSets = new ArrayList<>();
    private SerialNumberSetDTO selectedSerNrSet;

    private List<String> matTypes = new ArrayList<>();
    private List<String> selectedMatTypes = new ArrayList<>();

    private List<String> matClasses = new ArrayList<>();
    private List<String> selectedMatClasses = new ArrayList<>();

    private List<Integer> analIntervals;
    private Integer selectedAnalInterval;

    private List<Integer> returnIntervals;
    private Integer selectedReturnInterval;

    private LocalDate startDate;

    private List<PlantSearchDTO> plants = new ArrayList<>();
    private List<PlantSearchDTO> selectedPlants = new ArrayList<>();

    private List<String> origins = new ArrayList<>();
    private List<String> selectedOrigins = new ArrayList<>();

    private Boolean epidemic;

    private DistributionType selectedDistType;

    private String revNo;

    private List<String> pMatNrs = new ArrayList<>();

    private String defCmpt;

    private List<String> errGrps = new ArrayList<>();
    private List<String> selectedErrGrps = new ArrayList<>();

    private List<RepairErrorCodeListDTO> selectedErr = new ArrayList<>();

    private List<Integer> qualityPeriods;
    private Integer selectedQualityPeriod;

    private String matHier;

    private String errorId;

    private RMACountingType selectedRmaCntType;



    public List<String> getMatNrs() {
        return matNrs;
    }

    public void setMatNrs(List<String> matNrs) {
        this.matNrs = matNrs;
    }



    public List<CustomerDTO> getCustomers() {
        return customers;
    }

    public void setCustomers(List<CustomerDTO> customers) {
        this.customers = customers;
    }



    public List<SupplierDTO> getSupplier() {
        return supplier;
    }

    public void setSupplier(List<SupplierDTO> supplier) {
        this.supplier = supplier;
    }



    public List<String> getRmaTypes() {
        return rmaTypes;
    }

    public void setRmaTypes(List<String> rmaTypes) {
        this.rmaTypes = rmaTypes;
    }

    public List<String> getSelectedRmaTypes() {
        return selectedRmaTypes;
    }

    public void setSelectedRmaTypes(List<String> selectedRmaTypes) {
        this.selectedRmaTypes = selectedRmaTypes;
    }



    public List<MaterialNumberSetDTO> getMatNrSets() {
        return matNrSets;
    }

    public void setMatNrSets(List<MaterialNumberSetDTO> matNrSets) {
        this.matNrSets = matNrSets;
    }

    public MaterialNumberSetDTO getSelectedMatNrSet() {
        return selectedMatNrSet;
    }

    public void setSelectedMatNrSet(MaterialNumberSetDTO selectedMatNrSet) {
        this.selectedMatNrSet = selectedMatNrSet;
    }



    public List<SerialNumberSetDTO> getSerNrSets() {
        return serNrSets;
    }

    public void setSerNrSets(List<SerialNumberSetDTO> serNrSets) {
        this.serNrSets = serNrSets;
    }

    public SerialNumberSetDTO getSelectedSerNrSet() {
        return selectedSerNrSet;
    }

    public void setSelectedSerNrSet(SerialNumberSetDTO selectedSerNrSet) {
        this.selectedSerNrSet = selectedSerNrSet;
    }



    public List<String> getMatTypes() {
        return matTypes;
    }

    public void setMatTypes(List<String> matTypes) {
        this.matTypes = matTypes;
    }

    public List<String> getSelectedMatTypes() {
        return selectedMatTypes;
    }

    public void setSelectedMatTypes(List<String> selectedMatTypes) {
        this.selectedMatTypes = selectedMatTypes;
    }



    public List<String> getMatClasses() {
        return matClasses;
    }

    public void setMatClasses(List<String> matClasses) {
        this.matClasses = matClasses;
    }

    public List<String> getSelectedMatClasses() {
        return selectedMatClasses;
    }

    public void setSelectedMatClasses(List<String> selectedMatClasses) {
        this.selectedMatClasses = selectedMatClasses;
    }



    public List<Integer> getAnalIntervals() {
        return analIntervals;
    }

    public void setAnalIntervals(List<Integer> analIntervals) {
        this.analIntervals = analIntervals;
    }

    public Integer getSelectedAnalInterval() {
        return selectedAnalInterval;
    }

    public void setSelectedAnalInterval(Integer selectedAnalInterval) {
        this.selectedAnalInterval = selectedAnalInterval;
    }



    public List<Integer> getReturnIntervals() {
        return returnIntervals;
    }

    public void setReturnIntervals(List<Integer> returnIntervals) {
        this.returnIntervals = returnIntervals;
    }

    public Integer getSelectedReturnInterval() {
        return selectedReturnInterval;
    }

    public void setSelectedReturnInterval(Integer selectedReturnInterval) {
        this.selectedReturnInterval = selectedReturnInterval;
    }



    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }



    public List<PlantSearchDTO> getPlants() {
        return plants;
    }

    public void setPlants(List<PlantSearchDTO> plants) {
        this.plants = plants;
    }

    public List<PlantSearchDTO> getSelectedPlants() {
        return selectedPlants;
    }

    public void setSelectedPlants(List<PlantSearchDTO> selectedPlants) {
        this.selectedPlants = selectedPlants;
    }



    public List<String> getOrigins() {
        return origins;
    }

    public void setOrigins(List<String> origins) {
        this.origins = origins;
    }

    public List<String> getSelectedOrigins() {
        return selectedOrigins;
    }

    public void setSelectedOrigins(List<String> selectedOrigins) {
        this.selectedOrigins = selectedOrigins;
    }



    public Boolean getEpidemic() {
        return epidemic;
    }

    public void setEpidemic(Boolean epidemic) {
        this.epidemic = epidemic;
    }



    public DistributionType[] getDistTypes() {
        return DistributionType.values();
    }

    public DistributionType getSelectedDistType() {
        return selectedDistType;
    }

    public void setSelectedDistType(DistributionType selectedDistType) {
        this.selectedDistType = selectedDistType;
    }



    public String getRevNo() {
        return revNo;
    }

    public void setRevNo(String revNo) {
        this.revNo = revNo;
    }



    public List<String> getpMatNrs() {
        return pMatNrs;
    }

    public void setpMatNrs(List<String> pMatNrs) {
        this.pMatNrs = pMatNrs;
    }



    public String getDefCmpt() {
        return defCmpt;
    }

    public void setDefCmpt(String defCmpt) {
        this.defCmpt = defCmpt;
    }



    public List<String> getErrGrps() {
        return errGrps;
    }

    public void setErrGrps(List<String> errGrps) {
        this.errGrps = errGrps;
    }

    public List<String> getSelectedErrGrps() {
        return selectedErrGrps;
    }

    public void setSelectedErrGrps(List<String> selectedErrGrps) {
        this.selectedErrGrps = selectedErrGrps;
    }



    public List<RepairErrorCodeListDTO> getSelectedErr() {
        return selectedErr;
    }

    public void setSelectedErr(List<RepairErrorCodeListDTO> selectedErr) {
        this.selectedErr = selectedErr;
    }



    public List<Integer> getQualityPeriods() {
        return qualityPeriods;
    }

    public void setQualityPeriods(List<Integer> qualityPeriods) {
        this.qualityPeriods = qualityPeriods;
    }

    public Integer getSelectedQualityPeriod() {
        return selectedQualityPeriod;
    }

    public void setSelectedQualityPeriod(Integer selectedQualityPeriod) {
        this.selectedQualityPeriod = selectedQualityPeriod;
    }



    public String getMatHier() {
        return matHier;
    }

    public void setMatHier(String matHier) {
        this.matHier = matHier;
    }



    public String getErrorId() {
        return errorId;
    }

    public void setErrorId(String errorId) {
        this.errorId = errorId;
    }



    public RMACountingType[] getRmaCntTypes() {
        return RMACountingType.values();
    }

    public RMACountingType getSelectedRmaCntType() {
        return selectedRmaCntType;
    }

    public void setSelectedRmaCntType(RMACountingType selectedRmaCntType) {
        this.selectedRmaCntType = selectedRmaCntType;
    }

}
