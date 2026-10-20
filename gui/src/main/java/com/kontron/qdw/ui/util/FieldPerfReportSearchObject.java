package com.kontron.qdw.ui.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.kontron.qdw.dto.base.CustomerDTO;
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

    private List<Integer> analInterval;
    private Integer selectedAnalInterval;

    private List<Integer> returnInterval;
    private Integer selectedReturnInterval;

    private LocalDate startDate;


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
        return analInterval;
    }

    public void setAnalInterval(List<Integer> analInterval) {
        this.analInterval = analInterval;
    }

    public Integer getSelectedAnalInterval() {
        return selectedAnalInterval;
    }

    public void setSelectedAnalInterval(Integer selectedAnalInterval) {
        this.selectedAnalInterval = selectedAnalInterval;
    }


    public List<Integer> getReturnIntervals() {
        return returnInterval;
    }

    public void setReturnInterval(List<Integer> returnInterval) {
        this.returnInterval = returnInterval;
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
}
