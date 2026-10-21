package com.kontron.qdw.ui.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.kontron.qdw.dto.base.CustomerDTO;
import com.kontron.qdw.dto.base.PlantSearchDTO;
import com.kontron.qdw.dto.base.SupplierDTO;
import com.kontron.qdw.dto.material.MaterialNumberSetDTO;
import com.kontron.qdw.dto.material.SerialNumberSetDTO;

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

}
