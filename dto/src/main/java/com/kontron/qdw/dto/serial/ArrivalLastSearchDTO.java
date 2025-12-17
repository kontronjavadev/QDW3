package com.kontron.qdw.dto.serial;

import java.time.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

import net.sourceforge.jbizmo.commons.annotation.Customized;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class ArrivalLastSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_ARRIVALDATE = "arrivalDate";
    @Generated
    public static final String ATTR_ORDERNUMBER = "orderNumber";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_MOVEMENTTYPECODE = "movementTypeCode";
    @Generated
    public static final String ATTR_PLANTCODE = "plantCode";
    @Generated
    public static final String ATTR_SUPPLIERNAME = "supplierName";
    @Generated
    public static final String ATTR_SUPPLIERCODE = "supplierCode";
    @Generated
    public static final String ATTR_MATERIALREVISIONID = "materialRevisionId";
    @Generated
    public static final String ATTR_SERIALOBJECTID = "serialObjectId";
    @Generated
    public static final String ATTR_SERIALOBJECTSERIALNUMBER = "serialObjectSerialNumber";
    @Generated
    public static final String ATTR_MATREVMATID = "matrevMatId";
    @Generated
    public static final String ATTR_MATREVMATMATERIALNUMBER = "matrevMatMaterialNumber";
    @Generated
    public static final String ATTR_MATREVMATSAPNUMBER = "matrevMatSapNumber";
    @Generated
    public static final String ATTR_MATREVMATSHORTTEXT = "matrevMatShortText";
    @Generated
    public static final String ATTR_MATREVMATOWNERLOCATIONCODE = "matrevMatOwnerLocationCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALTYPECODE = "matrevMatMaterialTypeCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALCLASSCODE = "matrevMatMaterialClassCode";
    @Generated
    public static final String ATTR_MATREVMATMATERIALHIERARCHY = "matrevMatMaterialHierarchy";
    @Generated
    public static final String ATTR_MATREVREVISIONNUMBER = "matrevRevisionNumber";
    @Customized
    public static final String SELECT_ARRIVALDATE = "a.arrival_date";
    @Customized
    public static final String SELECT_ORDERNUMBER = "a.order_number";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Customized
    public static final String SELECT_CREATIONDATE = "a.creation_date";
    @Customized
    public static final String SELECT_LASTUPDATE = "a.last_update";
    @Customized
    public static final String SELECT_MOVEMENTTYPECODE = "a.movement_type";
    @Generated
    public static final String SELECT_PLANTCODE = "a.plant";
    @Generated
    public static final String SELECT_SUPPLIERNAME = "f.name";
    @Generated
    public static final String SELECT_SUPPLIERCODE = "f.code";
    @Generated
    public static final String SELECT_MATERIALREVISIONID = "b.id";
    @Generated
    public static final String SELECT_SERIALOBJECTID = "e.id";
    @Customized
    public static final String SELECT_SERIALOBJECTSERIALNUMBER = "e.serial_number";
    @Generated
    public static final String SELECT_MATREVMATID = "l.id";
    @Customized
    public static final String SELECT_MATREVMATMATERIALNUMBER = "l.material_number";
    @Customized
    public static final String SELECT_MATREVMATSAPNUMBER = "l.sap_number";
    @Customized
    public static final String SELECT_MATREVMATSHORTTEXT = "l.short_text";
    @Customized
    public static final String SELECT_MATREVMATMATERIALTYPECODE = "l.material_type";
    @Customized
    public static final String SELECT_MATREVMATMATERIALCLASSCODE = "l.material_class";
    @Customized
    public static final String SELECT_MATREVMATOWNERLOCATIONCODE = "l.owner_location";
    @Customized
    public static final String SELECT_MATREVMATMATERIALHIERARCHY = "l.material_hierarchy";
    @Customized
    public static final String SELECT_MATREVREVISIONNUMBER = "b.revision_number";
    @Generated
    private LocalDate arrivalDate;
    @Generated
    private String orderNumber;
    @Generated
    private long id;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private String movementTypeCode;
    @Generated
    private String plantCode;
    @Generated
    private String supplierName;
    @Generated
    private String supplierCode;
    @Generated
    private long materialRevisionId;
    @Generated
    private long serialObjectId;
    @Generated
    private String serialObjectSerialNumber;
    @Generated
    private long matrevMatId;
    @Generated
    private String matrevMatMaterialNumber;
    @Generated
    private String matrevMatSapNumber;
    @Generated
    private String matrevMatShortText;
    @Generated
    private String matrevMatOwnerLocationCode;
    @Generated
    private String matrevMatMaterialTypeCode;
    @Generated
    private String matrevMatMaterialClassCode;
    @Generated
    private String matrevMatMaterialHierarchy;
    @Generated
    private String matrevRevisionNumber;

    /**
     * Default constructor
     */
    @Generated
    public ArrivalLastSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public ArrivalLastSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param arrivalDate
     * @param orderNumber
     * @param id
     * @param creationDate
     * @param lastUpdate
     * @param movementTypeCode
     * @param plantCode
     * @param supplierName
     * @param supplierCode
     * @param materialRevisionId
     * @param serialObjectId
     * @param serialObjectSerialNumber
     * @param matrevMatId
     * @param matrevMatMaterialNumber
     * @param matrevMatSapNumber
     * @param matrevMatShortText
     * @param matrevMatOwnerLocationCode
     * @param matrevMatMaterialTypeCode
     * @param matrevMatMaterialClassCode
     * @param matrevMatMaterialHierarchy
     * @param matrevRevisionNumber
     */
    @Customized
    public ArrivalLastSearchDTO(Date arrivalDate, String orderNumber, long id, Timestamp creationDate, Timestamp lastUpdate,
            String movementTypeCode, String plantCode, String supplierName, String supplierCode, long materialRevisionId, long serialObjectId,
            String serialObjectSerialNumber, long matrevMatId, String matrevMatMaterialNumber, String matrevMatSapNumber, String matrevMatShortText,
            String matrevMatOwnerLocationCode, String matrevMatMaterialTypeCode, String matrevMatMaterialClassCode, String matrevMatMaterialHierarchy,
            String matrevRevisionNumber) {
        this.arrivalDate = arrivalDate == null ? null : arrivalDate.toLocalDate();
        this.orderNumber = orderNumber;
        this.id = id;
        this.creationDate = creationDate == null ? null : creationDate.toLocalDateTime();
        this.lastUpdate = lastUpdate == null ? null : lastUpdate.toLocalDateTime();
        this.movementTypeCode = movementTypeCode;
        this.plantCode = plantCode;
        this.supplierName = supplierName;
        this.supplierCode = supplierCode;
        this.materialRevisionId = materialRevisionId;
        this.serialObjectId = serialObjectId;
        this.serialObjectSerialNumber = serialObjectSerialNumber;
        this.matrevMatId = matrevMatId;
        this.matrevMatMaterialNumber = matrevMatMaterialNumber;
        this.matrevMatSapNumber = matrevMatSapNumber;
        this.matrevMatShortText = matrevMatShortText;
        this.matrevMatOwnerLocationCode = matrevMatOwnerLocationCode;
        this.matrevMatMaterialTypeCode = matrevMatMaterialTypeCode;
        this.matrevMatMaterialClassCode = matrevMatMaterialClassCode;
        this.matrevMatMaterialHierarchy = matrevMatMaterialHierarchy;
        this.matrevRevisionNumber = matrevRevisionNumber;
    }

    /**
     * @return the arrival date
     */
    @Generated
    public LocalDate getArrivalDate() {
        return this.arrivalDate;
    }

    /**
     * @param arrivalDate the arrival date to set
     */
    @Generated
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * @return the order number
     */
    @Generated
    public String getOrderNumber() {
        return this.orderNumber;
    }

    /**
     * @param orderNumber the order number to set
     */
    @Generated
    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
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
     * @return the creation date
     */
    @Generated
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * @param creationDate the creation date to set
     */
    @Generated
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the last update
     */
    @Generated
    public LocalDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * @param lastUpdate the last update to set
     */
    @Generated
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
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
     * @return the id of the serial object
     */
    @Generated
    public long getSerialObjectId() {
        return this.serialObjectId;
    }

    /**
     * @param serialObjectId the id of the serial object to set
     */
    @Generated
    public void setSerialObjectId(long serialObjectId) {
        this.serialObjectId = serialObjectId;
    }

    /**
     * @return the serial number of the serial object
     */
    @Generated
    public String getSerialObjectSerialNumber() {
        return this.serialObjectSerialNumber;
    }

    /**
     * @param serialObjectSerialNumber the serial number of the serial object to set
     */
    @Generated
    public void setSerialObjectSerialNumber(String serialObjectSerialNumber) {
        this.serialObjectSerialNumber = serialObjectSerialNumber;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public long getMatrevMatId() {
        return this.matrevMatId;
    }

    /**
     * @param matrevMatId the id of the material to set
     */
    @Generated
    public void setMatrevMatId(long matrevMatId) {
        this.matrevMatId = matrevMatId;
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

        final var dto = (ArrivalLastSearchDTO) obj;

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