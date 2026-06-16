package com.kontron.qdw.boundary.service.mapping.bom.dto;

public class BoMItemComparingDto {

    private String materialNumber;
    private String label;
    private Double quantity;
    private boolean consignment;
    private String alternativeGroup;
    private boolean assembly;
    private Integer usageProbability;
    private String lineNumber;


    public static BoMItemComparingDto newInstance() {
        return new BoMItemComparingDto();
    }


    public BoMItemComparingDto withMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
        return this;
    }

    public BoMItemComparingDto withLabel(String label) {
        this.label = label;
        return this;
    }

    public BoMItemComparingDto withQuantity(Double quantity) {
        this.quantity = quantity;
        return this;
    }

    public BoMItemComparingDto withConsignment(boolean consignment) {
        this.consignment = consignment;
        return this;
    }

    public BoMItemComparingDto withAlternativeGroup(String alternativeGroup) {
        this.alternativeGroup = alternativeGroup;
        return this;
    }

    public BoMItemComparingDto withAssembly(boolean assembly) {
        this.assembly = assembly;
        return this;
    }

    public BoMItemComparingDto withUsageProbability(Integer usageProbability) {
        this.usageProbability = usageProbability;
        return this;
    }

    public BoMItemComparingDto withLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
        return this;
    }



    public String getMaterialNumber() {
        return materialNumber;
    }

    public String getLabel() {
        return label;
    }

    public Double getQuantity() {
        return quantity;
    }

    public boolean isConsignment() {
        return consignment;
    }

    public String getAlternativeGroup() {
        return alternativeGroup;
    }

    public boolean isAssembly() {
        return assembly;
    }

    public Integer getUsageProbability() {
        return usageProbability;
    }

    public String getLineNumber() {
        return lineNumber;
    }

}
