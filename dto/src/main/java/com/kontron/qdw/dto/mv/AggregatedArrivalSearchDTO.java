package com.kontron.qdw.dto.mv;

import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class AggregatedArrivalSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ARRIVALS = "arrivals";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_YEAR = "year";
    @Generated
    public static final String ATTR_MONTH = "month";
    @Generated
    public static final String ATTR_PLANTCODE = "plantCode";
    @Generated
    public static final String ATTR_MOVEMENTTYPECODE = "movementTypeCode";
    @Generated
    public static final String ATTR_SUPPLIERNAME = "supplierName";
    @Generated
    public static final String ATTR_SUPPLIERCODE = "supplierCode";
    @Generated
    public static final String ATTR_MATERIALREVISIONID = "materialRevisionId";
    @Generated
    public static final String ATTR_COUNTRYCODE = "countryCode";
    @Generated
    public static final String ATTR_COUNTRYNAME = "countryName";
    @Generated
    public static final String ATTR_MATREVMATERIALID = "matrevMaterialId";
    @Generated
    public static final String ATTR_MATREVMATMATERIALNUMBER = "matrevMatMaterialNumber";
    @Generated
    public static final String ATTR_MATREVMATSAPNUMBER = "matrevMatSapNumber";
    @Generated
    public static final String ATTR_MATREVMATTYPECODE = "matrevMatTypeCode";
    @Generated
    public static final String ATTR_MATREVMATCLASSCODE = "matrevMatClassCode";
    @Generated
    public static final String ATTR_MATREVMATOWNERLOCATIONCODE = "matrevMatOwnerLocationCode";
    @Generated
    public static final String ATTR_MATREVMATSHORTTEXT = "matrevMatShortText";
    @Generated
    public static final String ATTR_MATREVREVISIONNUMBER = "matrevRevisionNumber";
    @Generated
    public static final String SELECT_ARRIVALS = "a.arrivals";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_YEAR = "a.year";
    @Generated
    public static final String SELECT_MONTH = "a.month";
    @Generated
    public static final String SELECT_PLANTCODE = "c.code";
    @Generated
    public static final String SELECT_MOVEMENTTYPECODE = "d.code";
    @Generated
    public static final String SELECT_SUPPLIERNAME = "e.name";
    @Generated
    public static final String SELECT_SUPPLIERCODE = "e.code";
    @Generated
    public static final String SELECT_MATERIALREVISIONID = "b.id";
    @Generated
    public static final String SELECT_COUNTRYCODE = "f.code";
    @Generated
    public static final String SELECT_COUNTRYNAME = "f.name";
    @Generated
    public static final String SELECT_MATREVMATERIALID = "g.id";
    @Generated
    public static final String SELECT_MATREVMATMATERIALNUMBER = "g.materialNumber";
    @Generated
    public static final String SELECT_MATREVMATSAPNUMBER = "g.sapNumber";
    @Generated
    public static final String SELECT_MATREVMATTYPECODE = "l.code";
    @Generated
    public static final String SELECT_MATREVMATCLASSCODE = "k.code";
    @Generated
    public static final String SELECT_MATREVMATOWNERLOCATIONCODE = "j.code";
    @Generated
    public static final String SELECT_MATREVMATSHORTTEXT = "g.shortText";
    @Generated
    public static final String SELECT_MATREVREVISIONNUMBER = "b.revisionNumber";
    @Generated
    private int arrivals;
    @Generated
    private long id;
    @Generated
    private int year;
    @Generated
    private int month;
    @Generated
    private String plantCode;
    @Generated
    private String movementTypeCode;
    @Generated
    private String supplierName;
    @Generated
    private String supplierCode;
    @Generated
    private long materialRevisionId;
    @Generated
    private String countryCode;
    @Generated
    private String countryName;
    @Generated
    private long matrevMaterialId;
    @Generated
    private String matrevMatMaterialNumber;
    @Generated
    private String matrevMatSapNumber;
    @Generated
    private String matrevMatTypeCode;
    @Generated
    private String matrevMatClassCode;
    @Generated
    private String matrevMatOwnerLocationCode;
    @Generated
    private String matrevMatShortText;
    @Generated
    private String matrevRevisionNumber;

    /**
     * Default constructor
     */
    @Generated
    public AggregatedArrivalSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public AggregatedArrivalSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param arrivals
     * @param id
     * @param year
     * @param month
     * @param plantCode
     * @param movementTypeCode
     * @param supplierName
     * @param supplierCode
     * @param materialRevisionId
     * @param countryCode
     * @param countryName
     * @param matrevMaterialId
     * @param matrevMatMaterialNumber
     * @param matrevMatSapNumber
     * @param matrevMatTypeCode
     * @param matrevMatClassCode
     * @param matrevMatOwnerLocationCode
     * @param matrevMatShortText
     * @param matrevRevisionNumber
     */
    @Generated
    public AggregatedArrivalSearchDTO(int arrivals, long id, int year, int month, String plantCode, String movementTypeCode, String supplierName,
            String supplierCode, long materialRevisionId, String countryCode, String countryName, long matrevMaterialId,
            String matrevMatMaterialNumber, String matrevMatSapNumber, String matrevMatTypeCode, String matrevMatClassCode,
            String matrevMatOwnerLocationCode, String matrevMatShortText, String matrevRevisionNumber) {
        this.arrivals = arrivals;
        this.id = id;
        this.year = year;
        this.month = month;
        this.plantCode = plantCode;
        this.movementTypeCode = movementTypeCode;
        this.supplierName = supplierName;
        this.supplierCode = supplierCode;
        this.materialRevisionId = materialRevisionId;
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.matrevMaterialId = matrevMaterialId;
        this.matrevMatMaterialNumber = matrevMatMaterialNumber;
        this.matrevMatSapNumber = matrevMatSapNumber;
        this.matrevMatTypeCode = matrevMatTypeCode;
        this.matrevMatClassCode = matrevMatClassCode;
        this.matrevMatOwnerLocationCode = matrevMatOwnerLocationCode;
        this.matrevMatShortText = matrevMatShortText;
        this.matrevRevisionNumber = matrevRevisionNumber;
    }

    /**
     * @return the arrivals
     */
    @Generated
    public int getArrivals() {
        return this.arrivals;
    }

    /**
     * @param arrivals the arrivals to set
     */
    @Generated
    public void setArrivals(int arrivals) {
        this.arrivals = arrivals;
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
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * @param countryCode the code of the country to set
     */
    @Generated
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the name of the country
     */
    @Generated
    public String getCountryName() {
        return this.countryName;
    }

    /**
     * @param countryName the name of the country to set
     */
    @Generated
    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
     * @return the code of the material type
     */
    @Generated
    public String getMatrevMatTypeCode() {
        return this.matrevMatTypeCode;
    }

    /**
     * @param matrevMatTypeCode the code of the material type to set
     */
    @Generated
    public void setMatrevMatTypeCode(String matrevMatTypeCode) {
        this.matrevMatTypeCode = matrevMatTypeCode;
    }

    /**
     * @return the code of the material class
     */
    @Generated
    public String getMatrevMatClassCode() {
        return this.matrevMatClassCode;
    }

    /**
     * @param matrevMatClassCode the code of the material class to set
     */
    @Generated
    public void setMatrevMatClassCode(String matrevMatClassCode) {
        this.matrevMatClassCode = matrevMatClassCode;
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

        final var dto = (AggregatedArrivalSearchDTO) obj;

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