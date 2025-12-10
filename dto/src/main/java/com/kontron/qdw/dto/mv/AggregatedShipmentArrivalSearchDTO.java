package com.kontron.qdw.dto.mv;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class AggregatedShipmentArrivalSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_SHIPMENTS = "shipments";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_YEAR = "year";
    @Generated
    public static final String ATTR_MONTH = "month";
    @Generated
    public static final String ATTR_PLANTCODE = "plantCode";
    @Generated
    public static final String ATTR_CUSTOMERNAME = "customerName";
    @Generated
    public static final String ATTR_CUSTOMERCODE = "customerCode";
    @Generated
    public static final String ATTR_SHIPMENTMOVEMENTTYPECODE = "shipmentMovementTypeCode";
    @Generated
    public static final String ATTR_MATERIALREVISIONID = "materialRevisionId";
    @Generated
    public static final String ATTR_CUSTCOUNTRYCODE = "custCountryCode";
    @Generated
    public static final String ATTR_CUSTCOUNTRYNAME = "custCountryName";
    @Generated
    public static final String ATTR_SUPPLIERCODE = "supplierCode";
    @Generated
    public static final String ATTR_SUPPLIERNAME = "supplierName";
    @Generated
    public static final String ATTR_ARRIVALMOVEMENTTYPECODE = "arrivalMovementTypeCode";
    @Generated
    public static final String ATTR_MATREVMATERIALID = "matrevMaterialId";
    @Generated
    public static final String ATTR_MATREVMATMATERIALNUMBER = "matrevMatMaterialNumber";
    @Generated
    public static final String ATTR_MATREVMATSAPNUMBER = "matrevMatSapNumber";
    @Generated
    public static final String ATTR_MATREVMATMATERIALHIERARCHY = "matrevMatMaterialHierarchy";
    @Generated
    public static final String ATTR_MATREVMATSHORTTEXT = "matrevMatShortText";
    @Generated
    public static final String ATTR_MATREVMATOWNERLOCATIONCODE = "matrevMatOwnerLocationCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALTYPECODE = "matrevMatMaterialTypeCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALCLASSCODE = "matrevMatMaterialClassCode";
    @Generated
    public static final String ATTR_MATERIALREVISIONREVISIONNUMBER = "materialRevisionRevisionNumber";
    @Generated
    public static final String SELECT_SHIPMENTS = "a.shipments";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_YEAR = "a.year";
    @Generated
    public static final String SELECT_MONTH = "a.month";
    @Generated
    public static final String SELECT_PLANTCODE = "c.code";
    @Generated
    public static final String SELECT_CUSTOMERNAME = "d.name";
    @Generated
    public static final String SELECT_CUSTOMERCODE = "d.code";
    @Generated
    public static final String SELECT_SHIPMENTMOVEMENTTYPECODE = "e.code";
    @Generated
    public static final String SELECT_MATERIALREVISIONID = "b.id";
    @Generated
    public static final String SELECT_CUSTCOUNTRYCODE = "h.code";
    @Generated
    public static final String SELECT_CUSTCOUNTRYNAME = "h.name";
    @Generated
    public static final String SELECT_SUPPLIERCODE = "g.code";
    @Generated
    public static final String SELECT_SUPPLIERNAME = "g.name";
    @Generated
    public static final String SELECT_ARRIVALMOVEMENTTYPECODE = "f.code";
    @Generated
    public static final String SELECT_MATREVMATERIALID = "k.id";
    @Generated
    public static final String SELECT_MATREVMATMATERIALNUMBER = "k.materialNumber";
    @Generated
    public static final String SELECT_MATREVMATSAPNUMBER = "k.sapNumber";
    @Generated
    public static final String SELECT_MATREVMATMATERIALHIERARCHY = "k.materialHierarchy";
    @Generated
    public static final String SELECT_MATREVMATSHORTTEXT = "k.shortText";
    @Generated
    public static final String SELECT_MATREVMATOWNERLOCATIONCODE = "n.code";
    @Generated
    public static final String SELECT_MATREVMATMATERIALTYPECODE = "p.code";
    @Generated
    public static final String SELECT_MATREVMATMATERIALCLASSCODE = "o.code";
    @Generated
    public static final String SELECT_MATERIALREVISIONREVISIONNUMBER = "b.revisionNumber";
    @Generated
    private int shipments;
    @Generated
    private long id;
    @Generated
    private int year;
    @Generated
    private int month;
    @Generated
    private String plantCode;
    @Generated
    private String customerName;
    @Generated
    private String customerCode;
    @Generated
    private String shipmentMovementTypeCode;
    @Generated
    private long materialRevisionId;
    @Generated
    private String custCountryCode;
    @Generated
    private String custCountryName;
    @Generated
    private String supplierCode;
    @Generated
    private String supplierName;
    @Generated
    private String arrivalMovementTypeCode;
    @Generated
    private long matrevMaterialId;
    @Generated
    private String matrevMatMaterialNumber;
    @Generated
    private String matrevMatSapNumber;
    @Generated
    private String matrevMatMaterialHierarchy;
    @Generated
    private String matrevMatShortText;
    @Generated
    private String matrevMatOwnerLocationCode;
    @Generated
    private String matrevMatMaterialTypeCode;
    @Generated
    private String matrevMatMaterialClassCode;
    @Generated
    private String materialRevisionRevisionNumber;

    /**
     * Default constructor
     */
    @Generated
    public AggregatedShipmentArrivalSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public AggregatedShipmentArrivalSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param shipments
     * @param id
     * @param year
     * @param month
     * @param plantCode
     * @param customerName
     * @param customerCode
     * @param shipmentMovementTypeCode
     * @param materialRevisionId
     * @param custCountryCode
     * @param custCountryName
     * @param supplierCode
     * @param supplierName
     * @param arrivalMovementTypeCode
     * @param matrevMaterialId
     * @param matrevMatMaterialNumber
     * @param matrevMatSapNumber
     * @param matrevMatMaterialHierarchy
     * @param matrevMatShortText
     * @param matrevMatOwnerLocationCode
     * @param matrevMatMaterialTypeCode
     * @param matrevMatMaterialClassCode
     * @param materialRevisionRevisionNumber
     */
    @Generated
    public AggregatedShipmentArrivalSearchDTO(int shipments, long id, int year, int month, String plantCode, String customerName, String customerCode,
            String shipmentMovementTypeCode, long materialRevisionId, String custCountryCode, String custCountryName, String supplierCode,
            String supplierName, String arrivalMovementTypeCode, long matrevMaterialId, String matrevMatMaterialNumber, String matrevMatSapNumber,
            String matrevMatMaterialHierarchy, String matrevMatShortText, String matrevMatOwnerLocationCode, String matrevMatMaterialTypeCode,
            String matrevMatMaterialClassCode, String materialRevisionRevisionNumber) {
        this.shipments = shipments;
        this.id = id;
        this.year = year;
        this.month = month;
        this.plantCode = plantCode;
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.shipmentMovementTypeCode = shipmentMovementTypeCode;
        this.materialRevisionId = materialRevisionId;
        this.custCountryCode = custCountryCode;
        this.custCountryName = custCountryName;
        this.supplierCode = supplierCode;
        this.supplierName = supplierName;
        this.arrivalMovementTypeCode = arrivalMovementTypeCode;
        this.matrevMaterialId = matrevMaterialId;
        this.matrevMatMaterialNumber = matrevMatMaterialNumber;
        this.matrevMatSapNumber = matrevMatSapNumber;
        this.matrevMatMaterialHierarchy = matrevMatMaterialHierarchy;
        this.matrevMatShortText = matrevMatShortText;
        this.matrevMatOwnerLocationCode = matrevMatOwnerLocationCode;
        this.matrevMatMaterialTypeCode = matrevMatMaterialTypeCode;
        this.matrevMatMaterialClassCode = matrevMatMaterialClassCode;
        this.materialRevisionRevisionNumber = materialRevisionRevisionNumber;
    }

    /**
     * @return the shipments
     */
    @Generated
    public int getShipments() {
        return this.shipments;
    }

    /**
     * @param shipments the shipments to set
     */
    @Generated
    public void setShipments(int shipments) {
        this.shipments = shipments;
    }

    /**
     * @return the id
     */
    @Generated
    public long getId() {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    @Generated
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the year
     */
    @Generated
    public int getYear() {
        return this.year;
    }

    /**
     * @param year the year to set
     */
    @Generated
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * @return the month
     */
    @Generated
    public int getMonth() {
        return this.month;
    }

    /**
     * @param month the month to set
     */
    @Generated
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * @return the code of the plant
     */
    @Generated
    public String getPlantCode() {
        return this.plantCode;
    }

    /**
     * @param plantCode the code of the plant to set
     */
    @Generated
    public void setPlantCode(String plantCode) {
        this.plantCode = plantCode;
    }

    /**
     * @return the name of the customer
     */
    @Generated
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * @param customerName the name of the customer to set
     */
    @Generated
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the code of the customer
     */
    @Generated
    public String getCustomerCode() {
        return this.customerCode;
    }

    /**
     * @param customerCode the code of the customer to set
     */
    @Generated
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * @return the code of the movement type
     */
    @Generated
    public String getShipmentMovementTypeCode() {
        return this.shipmentMovementTypeCode;
    }

    /**
     * @param shipmentMovementTypeCode the code of the movement type to set
     */
    @Generated
    public void setShipmentMovementTypeCode(String shipmentMovementTypeCode) {
        this.shipmentMovementTypeCode = shipmentMovementTypeCode;
    }

    /**
     * @return the id of the material revision
     */
    @Generated
    public long getMaterialRevisionId() {
        return this.materialRevisionId;
    }

    /**
     * @param materialRevisionId the id of the material revision to set
     */
    @Generated
    public void setMaterialRevisionId(long materialRevisionId) {
        this.materialRevisionId = materialRevisionId;
    }

    /**
     * @return the code of the country
     */
    @Generated
    public String getCustCountryCode() {
        return this.custCountryCode;
    }

    /**
     * @param custCountryCode the code of the country to set
     */
    @Generated
    public void setCustCountryCode(String custCountryCode) {
        this.custCountryCode = custCountryCode;
    }

    /**
     * @return the name of the country
     */
    @Generated
    public String getCustCountryName() {
        return this.custCountryName;
    }

    /**
     * @param custCountryName the name of the country to set
     */
    @Generated
    public void setCustCountryName(String custCountryName) {
        this.custCountryName = custCountryName;
    }

    /**
     * @return the code of the supplier
     */
    @Generated
    public String getSupplierCode() {
        return this.supplierCode;
    }

    /**
     * @param supplierCode the code of the supplier to set
     */
    @Generated
    public void setSupplierCode(String supplierCode) {
        this.supplierCode = supplierCode;
    }

    /**
     * @return the name of the supplier
     */
    @Generated
    public String getSupplierName() {
        return this.supplierName;
    }

    /**
     * @param supplierName the name of the supplier to set
     */
    @Generated
    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    /**
     * @return the code of the movement type
     */
    @Generated
    public String getArrivalMovementTypeCode() {
        return this.arrivalMovementTypeCode;
    }

    /**
     * @param arrivalMovementTypeCode the code of the movement type to set
     */
    @Generated
    public void setArrivalMovementTypeCode(String arrivalMovementTypeCode) {
        this.arrivalMovementTypeCode = arrivalMovementTypeCode;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public long getMatrevMaterialId() {
        return this.matrevMaterialId;
    }

    /**
     * @param matrevMaterialId the id of the material to set
     */
    @Generated
    public void setMatrevMaterialId(long matrevMaterialId) {
        this.matrevMaterialId = matrevMaterialId;
    }

    /**
     * @return the material number of the material
     */
    @Generated
    public String getMatrevMatMaterialNumber() {
        return this.matrevMatMaterialNumber;
    }

    /**
     * @param matrevMatMaterialNumber the material number of the material to set
     */
    @Generated
    public void setMatrevMatMaterialNumber(String matrevMatMaterialNumber) {
        this.matrevMatMaterialNumber = matrevMatMaterialNumber;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getMatrevMatSapNumber() {
        return this.matrevMatSapNumber;
    }

    /**
     * @param matrevMatSapNumber the sap number of the material to set
     */
    @Generated
    public void setMatrevMatSapNumber(String matrevMatSapNumber) {
        this.matrevMatSapNumber = matrevMatSapNumber;
    }

    /**
     * @return the material hierarchy of the material
     */
    @Generated
    public String getMatrevMatMaterialHierarchy() {
        return this.matrevMatMaterialHierarchy;
    }

    /**
     * @param matrevMatMaterialHierarchy the material hierarchy of the material to set
     */
    @Generated
    public void setMatrevMatMaterialHierarchy(String matrevMatMaterialHierarchy) {
        this.matrevMatMaterialHierarchy = matrevMatMaterialHierarchy;
    }

    /**
     * @return the short text of the material
     */
    @Generated
    public String getMatrevMatShortText() {
        return this.matrevMatShortText;
    }

    /**
     * @param matrevMatShortText the short text of the material to set
     */
    @Generated
    public void setMatrevMatShortText(String matrevMatShortText) {
        this.matrevMatShortText = matrevMatShortText;
    }

    /**
     * @return the code of the location
     */
    @Generated
    public String getMatrevMatOwnerLocationCode() {
        return this.matrevMatOwnerLocationCode;
    }

    /**
     * @param matrevMatOwnerLocationCode the code of the location to set
     */
    @Generated
    public void setMatrevMatOwnerLocationCode(String matrevMatOwnerLocationCode) {
        this.matrevMatOwnerLocationCode = matrevMatOwnerLocationCode;
    }

    /**
     * @return the code of the material type
     */
    @Generated
    public String getMatrevMatMaterialTypeCode() {
        return this.matrevMatMaterialTypeCode;
    }

    /**
     * @param matrevMatMaterialTypeCode the code of the material type to set
     */
    @Generated
    public void setMatrevMatMaterialTypeCode(String matrevMatMaterialTypeCode) {
        this.matrevMatMaterialTypeCode = matrevMatMaterialTypeCode;
    }

    /**
     * @return the code of the material class
     */
    @Generated
    public String getMatrevMatMaterialClassCode() {
        return this.matrevMatMaterialClassCode;
    }

    /**
     * @param matrevMatMaterialClassCode the code of the material class to set
     */
    @Generated
    public void setMatrevMatMaterialClassCode(String matrevMatMaterialClassCode) {
        this.matrevMatMaterialClassCode = matrevMatMaterialClassCode;
    }

    /**
     * @return the revision number of the material revision
     */
    @Generated
    public String getMaterialRevisionRevisionNumber() {
        return this.materialRevisionRevisionNumber;
    }

    /**
     * @param materialRevisionRevisionNumber the revision number of the material revision to set
     */
    @Generated
    public void setMaterialRevisionRevisionNumber(String materialRevisionRevisionNumber) {
        this.materialRevisionRevisionNumber = materialRevisionRevisionNumber;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Generated
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final var dto = (AggregatedShipmentArrivalSearchDTO) obj;

        return this.id == dto.getId();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

}