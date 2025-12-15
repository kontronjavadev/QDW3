package com.kontron.qdw.dto.mv;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class AggregatedShipmentSearchDTO implements Serializable {
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
    public static final String ATTR_MOVEMENTTYPECODE = "movementTypeCode";
    @Generated
    public static final String ATTR_MATERIALREVISIONID = "materialRevisionId";
    @Generated
    public static final String ATTR_CUSTCOUNTRYCODE = "custCountryCode";
    @Generated
    public static final String ATTR_CUSTCOUNTRYNAME = "custCountryName";
    @Generated
    public static final String ATTR_MATREVMATERIALID = "matrevMaterialId";
    @Generated
    public static final String ATTR_MATREVMATLMATERIALNUMBER = "matrevMatlMaterialNumber";
    @Generated
    public static final String ATTR_MATREVMATSAPNUMBER = "matrevMatSapNumber";
    @Generated
    public static final String ATTR_MATREVMATSHORTTEXT = "matrevMatShortText";
    @Generated
    public static final String ATTR_MATREVMATMATERIALTYPECODE = "matrevMatMaterialTypeCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALCLASSCODE = "matrevMatMaterialClassCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALHIERARCHY = "matrevMatMaterialHierarchy";
    @Generated
    public static final String ATTR_MATREVMATOWNERLOCATIONCODE = "matrevMatOwnerLocationCode";
    @Generated
    public static final String ATTR_MATREVREVISIONNUMBER = "matrevRevisionNumber";
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
    public static final String SELECT_MOVEMENTTYPECODE = "e.code";
    @Generated
    public static final String SELECT_MATERIALREVISIONID = "b.id";
    @Generated
    public static final String SELECT_CUSTCOUNTRYCODE = "f.code";
    @Generated
    public static final String SELECT_CUSTCOUNTRYNAME = "f.name";
    @Generated
    public static final String SELECT_MATREVMATERIALID = "h.id";
    @Generated
    public static final String SELECT_MATREVMATLMATERIALNUMBER = "h.materialNumber";
    @Generated
    public static final String SELECT_MATREVMATSAPNUMBER = "h.sapNumber";
    @Generated
    public static final String SELECT_MATREVMATSHORTTEXT = "h.shortText";
    @Generated
    public static final String SELECT_MATREVMATMATERIALTYPECODE = "m.code";
    @Generated
    public static final String SELECT_MATREVMATMATERIALCLASSCODE = "l.code";
    @Generated
    public static final String SELECT_MATREVMATMATERIALHIERARCHY = "h.materialHierarchy";
    @Generated
    public static final String SELECT_MATREVMATOWNERLOCATIONCODE = "k.code";
    @Generated
    public static final String SELECT_MATREVREVISIONNUMBER = "b.revisionNumber";
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
    private String movementTypeCode;
    @Generated
    private long materialRevisionId;
    @Generated
    private String custCountryCode;
    @Generated
    private String custCountryName;
    @Generated
    private long matrevMaterialId;
    @Generated
    private String matrevMatlMaterialNumber;
    @Generated
    private String matrevMatSapNumber;
    @Generated
    private String matrevMatShortText;
    @Generated
    private String matrevMatMaterialTypeCode;
    @Generated
    private String matrevMatMaterialClassCode;
    @Generated
    private String matrevMatMaterialHierarchy;
    @Generated
    private String matrevMatOwnerLocationCode;
    @Generated
    private String matrevRevisionNumber;

    /**
     * Default constructor
     */
    @Generated
    public AggregatedShipmentSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public AggregatedShipmentSearchDTO(long id) {
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
     * @param movementTypeCode
     * @param materialRevisionId
     * @param custCountryCode
     * @param custCountryName
     * @param matrevMaterialId
     * @param matrevMatlMaterialNumber
     * @param matrevMatSapNumber
     * @param matrevMatShortText
     * @param matrevMatMaterialTypeCode
     * @param matrevMatMaterialClassCode
     * @param matrevMatMaterialHierarchy
     * @param matrevMatOwnerLocationCode
     * @param matrevRevisionNumber
     */
    @Generated
    public AggregatedShipmentSearchDTO(int shipments, long id, int year, int month, String plantCode, String customerName, String customerCode,
            String movementTypeCode, long materialRevisionId, String custCountryCode, String custCountryName, long matrevMaterialId,
            String matrevMatlMaterialNumber, String matrevMatSapNumber, String matrevMatShortText, String matrevMatMaterialTypeCode,
            String matrevMatMaterialClassCode, String matrevMatMaterialHierarchy, String matrevMatOwnerLocationCode, String matrevRevisionNumber) {
        this.shipments = shipments;
        this.id = id;
        this.year = year;
        this.month = month;
        this.plantCode = plantCode;
        this.customerName = customerName;
        this.customerCode = customerCode;
        this.movementTypeCode = movementTypeCode;
        this.materialRevisionId = materialRevisionId;
        this.custCountryCode = custCountryCode;
        this.custCountryName = custCountryName;
        this.matrevMaterialId = matrevMaterialId;
        this.matrevMatlMaterialNumber = matrevMatlMaterialNumber;
        this.matrevMatSapNumber = matrevMatSapNumber;
        this.matrevMatShortText = matrevMatShortText;
        this.matrevMatMaterialTypeCode = matrevMatMaterialTypeCode;
        this.matrevMatMaterialClassCode = matrevMatMaterialClassCode;
        this.matrevMatMaterialHierarchy = matrevMatMaterialHierarchy;
        this.matrevMatOwnerLocationCode = matrevMatOwnerLocationCode;
        this.matrevRevisionNumber = matrevRevisionNumber;
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
    public String getMovementTypeCode() {
        return this.movementTypeCode;
    }

    /**
     * @param movementTypeCode the code of the movement type to set
     */
    @Generated
    public void setMovementTypeCode(String movementTypeCode) {
        this.movementTypeCode = movementTypeCode;
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
    public String getMatrevMatlMaterialNumber() {
        return this.matrevMatlMaterialNumber;
    }

    /**
     * @param matrevMatlMaterialNumber the material number of the material to set
     */
    @Generated
    public void setMatrevMatlMaterialNumber(String matrevMatlMaterialNumber) {
        this.matrevMatlMaterialNumber = matrevMatlMaterialNumber;
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
     * @return the revision number of the material revision
     */
    @Generated
    public String getMatrevRevisionNumber() {
        return this.matrevRevisionNumber;
    }

    /**
     * @param matrevRevisionNumber the revision number of the material revision to set
     */
    @Generated
    public void setMatrevRevisionNumber(String matrevRevisionNumber) {
        this.matrevRevisionNumber = matrevRevisionNumber;
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

        final var dto = (AggregatedShipmentSearchDTO) obj;

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