package com.kontron.qdw.dto.mv;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class MaterializedArrivalShipmentSerObjTBoMSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_COUNTRYCODE = "countryCode";
    @Generated
    public static final String ATTR_COUNTRYNAME = "countryName";
    @Generated
    public static final String ATTR_CUSTOMERCODE = "customerCode";
    @Generated
    public static final String ATTR_CUSTOMERNAME = "customerName";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_SERIALNUMBER = "serialNumber";
    @Generated
    public static final String ATTR_PARENTSERIALNUMBER = "parentSerialNumber";
    @Generated
    public static final String ATTR_ASSEMBLYDATE = "assemblyDate";
    @Generated
    public static final String ATTR_ASSEMBLYPO = "assemblyPO";
    @Generated
    public static final String ATTR_PLANT = "plant";
    @Generated
    public static final String ATTR_A_MATID = "a_MatId";
    @Generated
    public static final String ATTR_SEROBJID = "serObjId";
    @Generated
    public static final String ATTR_SEROBJIDCUSTOMERSERIALNUMBER = "serObjIdCustomerSerialNumber";
    @Generated
    public static final String ATTR_SEROBJTBOMID = "serObjTBomId";
    @Generated
    public static final String ATTR_SEROBJTBOMSUPPLIERCODE = "serObjTBomSupplierCode";
    @Generated
    public static final String ATTR_SEROBJTBOMSUPPLIERNAME = "serObjTBomSupplierName";
    @Generated
    public static final String ATTR_SEROBJTBOMMATREVID = "serObjTBomMatrevId";
    @Generated
    public static final String ATTR_SEROBJTBOMMATREVMATID = "serObjTBomMatrevMatId";
    @Generated
    public static final String ATTR_SEROBJTBOMMATREVMATMATERIALNUMBER = "serObjTBomMatrevMatMaterialNumber";
    @Generated
    public static final String ATTR_SEROBJTBOMMATREVREVISIONNUMBER = "serObjTBomMatrevRevisionNumber";
    @Generated
    public static final String ATTR_SEROBJTBOMMATREVMATSAPNUMBER = "serObjTBomMatrevMatSapNumber";
    @Generated
    public static final String ATTR_SEROBJTBOMMATREVMATMATERIALHIERARCHY = "serObjTBomMatrevMatMaterialHierarchy";
    @Generated
    public static final String ATTR_SEROBJTBOMMATREVMATSHORTTEXT = "serObjTBomMatrevMatShortText";
    @Generated
    public static final String ATTR_SEROBJTBOMMATREVMATOWNERLOCATIONCODE = "serObjTBomMatrevMatOwnerLocationCode";
    @Generated
    public static final String ATTR_SEROBJTBOMMATREVMATMATERIALTYPECODE = "serObjTBomMatrevMatMaterialTypeCode";
    @Generated
    public static final String ATTR_SEROBJTBOMMATREVMATMATERIALCLASSCODE = "serObjTBomMatrevMatMaterialClassCode";
    @Generated
    public static final String ATTR_SEROBJTBOMPRODUCTIONDATE = "serObjTBomProductionDate";
    @Generated
    public static final String ATTR_SEROBJTBOMDELIVERYNOTENUMBER = "serObjTBomDeliveryNoteNumber";
    @Generated
    public static final String ATTR_SEROBJTBOMORDERNUMBER = "serObjTBomOrderNumber";
    @Generated
    public static final String ATTR_SEROBJTBOMLOTNUMBER = "serObjTBomLotNumber";
    @Generated
    public static final String SELECT_COUNTRYCODE = "a.countryCode";
    @Generated
    public static final String SELECT_COUNTRYNAME = "a.countryName";
    @Generated
    public static final String SELECT_CUSTOMERCODE = "a.customerCode";
    @Generated
    public static final String SELECT_CUSTOMERNAME = "a.customerName";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_SERIALNUMBER = "a.serialNumber";
    @Generated
    public static final String SELECT_PARENTSERIALNUMBER = "a.parentSerialNumber";
    @Generated
    public static final String SELECT_ASSEMBLYDATE = "a.assemblyDate";
    @Generated
    public static final String SELECT_ASSEMBLYPO = "a.assemblyPO";
    @Generated
    public static final String SELECT_PLANT = "a.plant";
    @Generated
    public static final String SELECT_A_MATID = "b.id";
    @Generated
    public static final String SELECT_SEROBJID = "c.id";
    @Generated
    public static final String SELECT_SEROBJIDCUSTOMERSERIALNUMBER = "c.customerSerialNumber";
    @Generated
    public static final String SELECT_SEROBJTBOMID = "h.id";
    @Generated
    public static final String SELECT_SEROBJTBOMSUPPLIERCODE = "k.code";
    @Generated
    public static final String SELECT_SEROBJTBOMSUPPLIERNAME = "k.name";
    @Generated
    public static final String SELECT_SEROBJTBOMMATREVID = "j.id";
    @Generated
    public static final String SELECT_SEROBJTBOMMATREVMATID = "o.id";
    @Generated
    public static final String SELECT_SEROBJTBOMMATREVMATMATERIALNUMBER = "o.materialNumber";
    @Generated
    public static final String SELECT_SEROBJTBOMMATREVREVISIONNUMBER = "j.revisionNumber";
    @Generated
    public static final String SELECT_SEROBJTBOMMATREVMATSAPNUMBER = "o.sapNumber";
    @Generated
    public static final String SELECT_SEROBJTBOMMATREVMATMATERIALHIERARCHY = "o.materialHierarchy";
    @Generated
    public static final String SELECT_SEROBJTBOMMATREVMATSHORTTEXT = "o.shortText";
    @Generated
    public static final String SELECT_SEROBJTBOMMATREVMATOWNERLOCATIONCODE = "r.code";
    @Generated
    public static final String SELECT_SEROBJTBOMMATREVMATMATERIALTYPECODE = "t.code";
    @Generated
    public static final String SELECT_SEROBJTBOMMATREVMATMATERIALCLASSCODE = "s.code";
    @Generated
    public static final String SELECT_SEROBJTBOMPRODUCTIONDATE = "h.productionDate";
    @Generated
    public static final String SELECT_SEROBJTBOMDELIVERYNOTENUMBER = "h.deliveryNoteNumber";
    @Generated
    public static final String SELECT_SEROBJTBOMORDERNUMBER = "h.orderNumber";
    @Generated
    public static final String SELECT_SEROBJTBOMLOTNUMBER = "h.lotNumber";
    @Generated
    private String countryCode;
    @Generated
    private String countryName;
    @Generated
    private String customerCode;
    @Generated
    private String customerName;
    @Generated
    private long id;
    @Generated
    private String serialNumber;
    @Generated
    private String parentSerialNumber;
    @Generated
    private LocalDate assemblyDate;
    @Generated
    private String assemblyPO;
    @Generated
    private String plant;
    @Generated
    private long a_MatId;
    @Generated
    private long serObjId;
    @Generated
    private String serObjIdCustomerSerialNumber;
    @Generated
    private Long serObjTBomId;
    @Generated
    private String serObjTBomSupplierCode;
    @Generated
    private String serObjTBomSupplierName;
    @Generated
    private Long serObjTBomMatrevId;
    @Generated
    private Long serObjTBomMatrevMatId;
    @Generated
    private String serObjTBomMatrevMatMaterialNumber;
    @Generated
    private String serObjTBomMatrevRevisionNumber;
    @Generated
    private String serObjTBomMatrevMatSapNumber;
    @Generated
    private String serObjTBomMatrevMatMaterialHierarchy;
    @Generated
    private String serObjTBomMatrevMatShortText;
    @Generated
    private String serObjTBomMatrevMatOwnerLocationCode;
    @Generated
    private String serObjTBomMatrevMatMaterialTypeCode;
    @Generated
    private String serObjTBomMatrevMatMaterialClassCode;
    @Generated
    private LocalDate serObjTBomProductionDate;
    @Generated
    private String serObjTBomDeliveryNoteNumber;
    @Generated
    private String serObjTBomOrderNumber;
    @Generated
    private String serObjTBomLotNumber;

    /**
     * Default constructor
     */
    @Generated
    public MaterializedArrivalShipmentSerObjTBoMSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public MaterializedArrivalShipmentSerObjTBoMSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param countryCode
     * @param countryName
     * @param customerCode
     * @param customerName
     * @param id
     * @param serialNumber
     * @param parentSerialNumber
     * @param assemblyDate
     * @param assemblyPO
     * @param plant
     * @param a_MatId
     * @param serObjId
     * @param serObjIdCustomerSerialNumber
     * @param serObjTBomId
     * @param serObjTBomSupplierCode
     * @param serObjTBomSupplierName
     * @param serObjTBomMatrevId
     * @param serObjTBomMatrevMatId
     * @param serObjTBomMatrevMatMaterialNumber
     * @param serObjTBomMatrevRevisionNumber
     * @param serObjTBomMatrevMatSapNumber
     * @param serObjTBomMatrevMatMaterialHierarchy
     * @param serObjTBomMatrevMatShortText
     * @param serObjTBomMatrevMatOwnerLocationCode
     * @param serObjTBomMatrevMatMaterialTypeCode
     * @param serObjTBomMatrevMatMaterialClassCode
     * @param serObjTBomProductionDate
     * @param serObjTBomDeliveryNoteNumber
     * @param serObjTBomOrderNumber
     * @param serObjTBomLotNumber
     */
    @Generated
    public MaterializedArrivalShipmentSerObjTBoMSearchDTO(String countryCode, String countryName, String customerCode, String customerName, long id,
            String serialNumber, String parentSerialNumber, LocalDate assemblyDate, String assemblyPO, String plant, long a_MatId, long serObjId,
            String serObjIdCustomerSerialNumber, Long serObjTBomId, String serObjTBomSupplierCode, String serObjTBomSupplierName,
            Long serObjTBomMatrevId, Long serObjTBomMatrevMatId, String serObjTBomMatrevMatMaterialNumber, String serObjTBomMatrevRevisionNumber,
            String serObjTBomMatrevMatSapNumber, String serObjTBomMatrevMatMaterialHierarchy, String serObjTBomMatrevMatShortText,
            String serObjTBomMatrevMatOwnerLocationCode, String serObjTBomMatrevMatMaterialTypeCode, String serObjTBomMatrevMatMaterialClassCode,
            LocalDate serObjTBomProductionDate, String serObjTBomDeliveryNoteNumber, String serObjTBomOrderNumber, String serObjTBomLotNumber) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.customerCode = customerCode;
        this.customerName = customerName;
        this.id = id;
        this.serialNumber = serialNumber;
        this.parentSerialNumber = parentSerialNumber;
        this.assemblyDate = assemblyDate;
        this.assemblyPO = assemblyPO;
        this.plant = plant;
        this.a_MatId = a_MatId;
        this.serObjId = serObjId;
        this.serObjIdCustomerSerialNumber = serObjIdCustomerSerialNumber;
        this.serObjTBomId = serObjTBomId;
        this.serObjTBomSupplierCode = serObjTBomSupplierCode;
        this.serObjTBomSupplierName = serObjTBomSupplierName;
        this.serObjTBomMatrevId = serObjTBomMatrevId;
        this.serObjTBomMatrevMatId = serObjTBomMatrevMatId;
        this.serObjTBomMatrevMatMaterialNumber = serObjTBomMatrevMatMaterialNumber;
        this.serObjTBomMatrevRevisionNumber = serObjTBomMatrevRevisionNumber;
        this.serObjTBomMatrevMatSapNumber = serObjTBomMatrevMatSapNumber;
        this.serObjTBomMatrevMatMaterialHierarchy = serObjTBomMatrevMatMaterialHierarchy;
        this.serObjTBomMatrevMatShortText = serObjTBomMatrevMatShortText;
        this.serObjTBomMatrevMatOwnerLocationCode = serObjTBomMatrevMatOwnerLocationCode;
        this.serObjTBomMatrevMatMaterialTypeCode = serObjTBomMatrevMatMaterialTypeCode;
        this.serObjTBomMatrevMatMaterialClassCode = serObjTBomMatrevMatMaterialClassCode;
        this.serObjTBomProductionDate = serObjTBomProductionDate;
        this.serObjTBomDeliveryNoteNumber = serObjTBomDeliveryNoteNumber;
        this.serObjTBomOrderNumber = serObjTBomOrderNumber;
        this.serObjTBomLotNumber = serObjTBomLotNumber;
    }

    /**
     * @return the country code
     */
    @Generated
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * @param countryCode the country code to set
     */
    @Generated
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    /**
     * @return the country name
     */
    @Generated
    public String getCountryName() {
        return this.countryName;
    }

    /**
     * @param countryName the country name to set
     */
    @Generated
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the customer code
     */
    @Generated
    public String getCustomerCode() {
        return this.customerCode;
    }

    /**
     * @param customerCode the customer code to set
     */
    @Generated
    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    /**
     * @return the customer name
     */
    @Generated
    public String getCustomerName() {
        return this.customerName;
    }

    /**
     * @param customerName the customer name to set
     */
    @Generated
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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
     * @return the serial number
     */
    @Generated
    public String getSerialNumber() {
        return this.serialNumber;
    }

    /**
     * @param serialNumber the serial number to set
     */
    @Generated
    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    /**
     * @return the parent serial number
     */
    @Generated
    public String getParentSerialNumber() {
        return this.parentSerialNumber;
    }

    /**
     * @param parentSerialNumber the parent serial number to set
     */
    @Generated
    public void setParentSerialNumber(String parentSerialNumber) {
        this.parentSerialNumber = parentSerialNumber;
    }

    /**
     * @return the assembly date
     */
    @Generated
    public LocalDate getAssemblyDate() {
        return this.assemblyDate;
    }

    /**
     * @param assemblyDate the assembly date to set
     */
    @Generated
    public void setAssemblyDate(LocalDate assemblyDate) {
        this.assemblyDate = assemblyDate;
    }

    /**
     * @return the assembly PO
     */
    @Generated
    public String getAssemblyPO() {
        return this.assemblyPO;
    }

    /**
     * @param assemblyPO the assembly PO to set
     */
    @Generated
    public void setAssemblyPO(String assemblyPO) {
        this.assemblyPO = assemblyPO;
    }

    /**
     * @return the plant
     */
    @Generated
    public String getPlant() {
        return this.plant;
    }

    /**
     * @param plant the plant to set
     */
    @Generated
    public void setPlant(String plant) {
        this.plant = plant;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public long getA_MatId() {
        return this.a_MatId;
    }

    /**
     * @param a_MatId the id of the material to set
     */
    @Generated
    public void setA_MatId(long a_MatId) {
        this.a_MatId = a_MatId;
    }

    /**
     * @return the id of the serial object
     */
    @Generated
    public long getSerObjId() {
        return this.serObjId;
    }

    /**
     * @param serObjId the id of the serial object to set
     */
    @Generated
    public void setSerObjId(long serObjId) {
        this.serObjId = serObjId;
    }

    /**
     * @return the customer serial number of the serial object
     */
    @Generated
    public String getSerObjIdCustomerSerialNumber() {
        return this.serObjIdCustomerSerialNumber;
    }

    /**
     * @param serObjIdCustomerSerialNumber the customer serial number of the serial object to set
     */
    @Generated
    public void setSerObjIdCustomerSerialNumber(String serObjIdCustomerSerialNumber) {
        this.serObjIdCustomerSerialNumber = serObjIdCustomerSerialNumber;
    }

    /**
     * @return the id of the trace BoM
     */
    @Generated
    public Long getSerObjTBomId() {
        return this.serObjTBomId;
    }

    /**
     * @param serObjTBomId the id of the trace BoM to set
     */
    @Generated
    public void setSerObjTBomId(Long serObjTBomId) {
        this.serObjTBomId = serObjTBomId;
    }

    /**
     * @return the code of the supplier
     */
    @Generated
    public String getSerObjTBomSupplierCode() {
        return this.serObjTBomSupplierCode;
    }

    /**
     * @param serObjTBomSupplierCode the code of the supplier to set
     */
    @Generated
    public void setSerObjTBomSupplierCode(String serObjTBomSupplierCode) {
        this.serObjTBomSupplierCode = serObjTBomSupplierCode;
    }

    /**
     * @return the name of the supplier
     */
    @Generated
    public String getSerObjTBomSupplierName() {
        return this.serObjTBomSupplierName;
    }

    /**
     * @param serObjTBomSupplierName the name of the supplier to set
     */
    @Generated
    public void setSerObjTBomSupplierName(String serObjTBomSupplierName) {
        this.serObjTBomSupplierName = serObjTBomSupplierName;
    }

    /**
     * @return the id of the material revision
     */
    @Generated
    public Long getSerObjTBomMatrevId() {
        return this.serObjTBomMatrevId;
    }

    /**
     * @param serObjTBomMatrevId the id of the material revision to set
     */
    @Generated
    public void setSerObjTBomMatrevId(Long serObjTBomMatrevId) {
        this.serObjTBomMatrevId = serObjTBomMatrevId;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public Long getSerObjTBomMatrevMatId() {
        return this.serObjTBomMatrevMatId;
    }

    /**
     * @param serObjTBomMatrevMatId the id of the material to set
     */
    @Generated
    public void setSerObjTBomMatrevMatId(Long serObjTBomMatrevMatId) {
        this.serObjTBomMatrevMatId = serObjTBomMatrevMatId;
    }

    /**
     * @return the material number of the material
     */
    @Generated
    public String getSerObjTBomMatrevMatMaterialNumber() {
        return this.serObjTBomMatrevMatMaterialNumber;
    }

    /**
     * @param serObjTBomMatrevMatMaterialNumber the material number of the material to set
     */
    @Generated
    public void setSerObjTBomMatrevMatMaterialNumber(String serObjTBomMatrevMatMaterialNumber) {
        this.serObjTBomMatrevMatMaterialNumber = serObjTBomMatrevMatMaterialNumber;
    }

    /**
     * @return the revision number of the material revision
     */
    @Generated
    public String getSerObjTBomMatrevRevisionNumber() {
        return this.serObjTBomMatrevRevisionNumber;
    }

    /**
     * @param serObjTBomMatrevRevisionNumber the revision number of the material revision to set
     */
    @Generated
    public void setSerObjTBomMatrevRevisionNumber(String serObjTBomMatrevRevisionNumber) {
        this.serObjTBomMatrevRevisionNumber = serObjTBomMatrevRevisionNumber;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getSerObjTBomMatrevMatSapNumber() {
        return this.serObjTBomMatrevMatSapNumber;
    }

    /**
     * @param serObjTBomMatrevMatSapNumber the sap number of the material to set
     */
    @Generated
    public void setSerObjTBomMatrevMatSapNumber(String serObjTBomMatrevMatSapNumber) {
        this.serObjTBomMatrevMatSapNumber = serObjTBomMatrevMatSapNumber;
    }

    /**
     * @return the material hierarchy of the material
     */
    @Generated
    public String getSerObjTBomMatrevMatMaterialHierarchy() {
        return this.serObjTBomMatrevMatMaterialHierarchy;
    }

    /**
     * @param serObjTBomMatrevMatMaterialHierarchy the material hierarchy of the material to set
     */
    @Generated
    public void setSerObjTBomMatrevMatMaterialHierarchy(String serObjTBomMatrevMatMaterialHierarchy) {
        this.serObjTBomMatrevMatMaterialHierarchy = serObjTBomMatrevMatMaterialHierarchy;
    }

    /**
     * @return the short text of the material
     */
    @Generated
    public String getSerObjTBomMatrevMatShortText() {
        return this.serObjTBomMatrevMatShortText;
    }

    /**
     * @param serObjTBomMatrevMatShortText the short text of the material to set
     */
    @Generated
    public void setSerObjTBomMatrevMatShortText(String serObjTBomMatrevMatShortText) {
        this.serObjTBomMatrevMatShortText = serObjTBomMatrevMatShortText;
    }

    /**
     * @return the code of the location
     */
    @Generated
    public String getSerObjTBomMatrevMatOwnerLocationCode() {
        return this.serObjTBomMatrevMatOwnerLocationCode;
    }

    /**
     * @param serObjTBomMatrevMatOwnerLocationCode the code of the location to set
     */
    @Generated
    public void setSerObjTBomMatrevMatOwnerLocationCode(String serObjTBomMatrevMatOwnerLocationCode) {
        this.serObjTBomMatrevMatOwnerLocationCode = serObjTBomMatrevMatOwnerLocationCode;
    }

    /**
     * @return the code of the material type
     */
    @Generated
    public String getSerObjTBomMatrevMatMaterialTypeCode() {
        return this.serObjTBomMatrevMatMaterialTypeCode;
    }

    /**
     * @param serObjTBomMatrevMatMaterialTypeCode the code of the material type to set
     */
    @Generated
    public void setSerObjTBomMatrevMatMaterialTypeCode(String serObjTBomMatrevMatMaterialTypeCode) {
        this.serObjTBomMatrevMatMaterialTypeCode = serObjTBomMatrevMatMaterialTypeCode;
    }

    /**
     * @return the code of the material class
     */
    @Generated
    public String getSerObjTBomMatrevMatMaterialClassCode() {
        return this.serObjTBomMatrevMatMaterialClassCode;
    }

    /**
     * @param serObjTBomMatrevMatMaterialClassCode the code of the material class to set
     */
    @Generated
    public void setSerObjTBomMatrevMatMaterialClassCode(String serObjTBomMatrevMatMaterialClassCode) {
        this.serObjTBomMatrevMatMaterialClassCode = serObjTBomMatrevMatMaterialClassCode;
    }

    /**
     * @return the production date of the trace BoM
     */
    @Generated
    public LocalDate getSerObjTBomProductionDate() {
        return this.serObjTBomProductionDate;
    }

    /**
     * @param serObjTBomProductionDate the production date of the trace BoM to set
     */
    @Generated
    public void setSerObjTBomProductionDate(LocalDate serObjTBomProductionDate) {
        this.serObjTBomProductionDate = serObjTBomProductionDate;
    }

    /**
     * @return the delivery note number of the trace BoM
     */
    @Generated
    public String getSerObjTBomDeliveryNoteNumber() {
        return this.serObjTBomDeliveryNoteNumber;
    }

    /**
     * @param serObjTBomDeliveryNoteNumber the delivery note number of the trace BoM to set
     */
    @Generated
    public void setSerObjTBomDeliveryNoteNumber(String serObjTBomDeliveryNoteNumber) {
        this.serObjTBomDeliveryNoteNumber = serObjTBomDeliveryNoteNumber;
    }

    /**
     * @return the order number of the trace BoM
     */
    @Generated
    public String getSerObjTBomOrderNumber() {
        return this.serObjTBomOrderNumber;
    }

    /**
     * @param serObjTBomOrderNumber the order number of the trace BoM to set
     */
    @Generated
    public void setSerObjTBomOrderNumber(String serObjTBomOrderNumber) {
        this.serObjTBomOrderNumber = serObjTBomOrderNumber;
    }

    /**
     * @return the lot number of the trace BoM
     */
    @Generated
    public String getSerObjTBomLotNumber() {
        return this.serObjTBomLotNumber;
    }

    /**
     * @param serObjTBomLotNumber the lot number of the trace BoM to set
     */
    @Generated
    public void setSerObjTBomLotNumber(String serObjTBomLotNumber) {
        this.serObjTBomLotNumber = serObjTBomLotNumber;
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

        final var dto = (MaterializedArrivalShipmentSerObjTBoMSearchDTO) obj;

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