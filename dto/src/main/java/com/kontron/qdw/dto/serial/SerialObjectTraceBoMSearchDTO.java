package com.kontron.qdw.dto.serial;

import java.util.*;
import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class SerialObjectTraceBoMSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_SERIALNUMBER = "serialNumber";
    @Generated
    public static final String ATTR_ASSEMBLYDATE = "assemblyDate";
    @Generated
    public static final String ATTR_CUSTOMERSERIALNUMBER = "customerSerialNumber";
    @Generated
    public static final String ATTR_PRODUCTIONORDERNUMBER = "productionOrderNumber";
    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_SOMATID = "soMatId";
    @Generated
    public static final String ATTR_TBOMSUPPLIERCODE = "tbomSupplierCode";
    @Generated
    public static final String ATTR_TBOMSUPPLIERNAME = "tbomSupplierName";
    @Generated
    public static final String ATTR_TBOMDELIVERYNOTENUMBER = "tbomDeliveryNoteNumber";
    @Generated
    public static final String ATTR_TBOMMATREVID = "tbomMatrevId";
    @Generated
    public static final String ATTR_TBOMMATREVMATID = "tbomMatrevMatId";
    @Generated
    public static final String ATTR_TBOMMATREVMATMATERIALNUMBER = "tbomMatrevMatMaterialNumber";
    @Generated
    public static final String ATTR_TBOMMATREVMATSAPNUMBER = "tbomMatrevMatSapNumber";
    @Generated
    public static final String ATTR_TBOMMATREVMATMATERIALHIERARCHY = "tbomMatrevMatMaterialHierarchy";
    @Generated
    public static final String ATTR_TBOMMATREVMATSHORTTEXT = "tbomMatrevMatShortText";
    @Generated
    public static final String ATTR_TBOMTBOMITEMID = "tbomTbomitemId";
    @Generated
    public static final String ATTR_TBOMTBOMITEMMATID = "tbomTbomitemMatId";
    @Generated
    public static final String ATTR_TBOMTBOMITEMMATMATERIALNUMBER = "tbomTbomitemMatMaterialNumber";
    @Generated
    public static final String ATTR_TBOMTBOMITEMMATSAPNUMBER = "tbomTbomitemMatSapNumber";
    @Generated
    public static final String ATTR_TBOMTBOMITEMMATMATERIALHIERARCHY = "tbomTbomitemMatMaterialHierarchy";
    @Generated
    public static final String ATTR_TBOMTBOMITEMMATSHORTTEXT = "tbomTbomitemMatShortText";
    @Generated
    public static final String ATTR_TBOMMATREVREVISIONNUMBER = "tbomMatrevRevisionNumber";
    @Generated
    public static final String ATTR_TBOMPRODUCTIONDATE = "tbomProductionDate";
    @Generated
    public static final String ATTR_TBOMORDERNUMBER = "tbomOrderNumber";
    @Generated
    public static final String ATTR_TBOMLOTNUMBER = "tbomLotNumber";
    @Generated
    public static final String ATTR_TBOMTBOMITEMMANUFACTURERNAME = "tbomTbomitemManufacturerName";
    @Generated
    public static final String ATTR_TBOMTBOMITEMORDERCODE = "tbomTbomitemOrderCode";
    @Generated
    public static final String ATTR_TBOMTBOMITEMDATECODE = "tbomTbomitemDateCode";
    @Generated
    public static final String ATTR_TBOMTBOMITEMQUANTITY = "tbomTbomitemQuantity";
    @Generated
    public static final String ATTR_TBOMTBOMITEMINFOFIELD1 = "tbomTbomitemInfoField1";
    @Generated
    public static final String ATTR_TBOMTBOMITEMINFOFIELD2 = "tbomTbomitemInfoField2";
    @Generated
    public static final String SELECT_SERIALNUMBER = "a.serialNumber";
    @Generated
    public static final String SELECT_ASSEMBLYDATE = "a.assemblyDate";
    @Generated
    public static final String SELECT_CUSTOMERSERIALNUMBER = "a.customerSerialNumber";
    @Generated
    public static final String SELECT_PRODUCTIONORDERNUMBER = "a.productionOrderNumber";
    @Generated
    public static final String SELECT_ID = "a.id";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_SOMATID = "b.id";
    @Generated
    public static final String SELECT_TBOMSUPPLIERCODE = "i.code";
    @Generated
    public static final String SELECT_TBOMSUPPLIERNAME = "i.name";
    @Generated
    public static final String SELECT_TBOMDELIVERYNOTENUMBER = "f.deliveryNoteNumber";
    @Generated
    public static final String SELECT_TBOMMATREVID = "h.id";
    @Generated
    public static final String SELECT_TBOMMATREVMATID = "m.id";
    @Generated
    public static final String SELECT_TBOMMATREVMATMATERIALNUMBER = "m.materialNumber";
    @Generated
    public static final String SELECT_TBOMMATREVMATSAPNUMBER = "m.sapNumber";
    @Generated
    public static final String SELECT_TBOMMATREVMATMATERIALHIERARCHY = "m.materialHierarchy";
    @Generated
    public static final String SELECT_TBOMMATREVMATSHORTTEXT = "m.shortText";
    @Generated
    public static final String SELECT_TBOMTBOMITEMID = "j.id";
    @Generated
    public static final String SELECT_TBOMTBOMITEMMATID = "u.id";
    @Generated
    public static final String SELECT_TBOMTBOMITEMMATMATERIALNUMBER = "u.materialNumber";
    @Generated
    public static final String SELECT_TBOMTBOMITEMMATSAPNUMBER = "u.sapNumber";
    @Generated
    public static final String SELECT_TBOMTBOMITEMMATMATERIALHIERARCHY = "u.materialHierarchy";
    @Generated
    public static final String SELECT_TBOMTBOMITEMMATSHORTTEXT = "u.shortText";
    @Generated
    public static final String SELECT_TBOMMATREVREVISIONNUMBER = "h.revisionNumber";
    @Generated
    public static final String SELECT_TBOMPRODUCTIONDATE = "f.productionDate";
    @Generated
    public static final String SELECT_TBOMORDERNUMBER = "f.orderNumber";
    @Generated
    public static final String SELECT_TBOMLOTNUMBER = "f.lotNumber";
    @Generated
    public static final String SELECT_TBOMTBOMITEMMANUFACTURERNAME = "j.manufacturerName";
    @Generated
    public static final String SELECT_TBOMTBOMITEMORDERCODE = "j.orderCode";
    @Generated
    public static final String SELECT_TBOMTBOMITEMDATECODE = "j.dateCode";
    @Generated
    public static final String SELECT_TBOMTBOMITEMQUANTITY = "j.quantity";
    @Generated
    public static final String SELECT_TBOMTBOMITEMINFOFIELD1 = "j.infoField1";
    @Generated
    public static final String SELECT_TBOMTBOMITEMINFOFIELD2 = "j.infoField2";
    @Generated
    private String serialNumber;
    @Generated
    private LocalDate assemblyDate;
    @Generated
    private String customerSerialNumber;
    @Generated
    private String productionOrderNumber;
    @Generated
    private long id;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private long soMatId;
    @Generated
    private String tbomSupplierCode;
    @Generated
    private String tbomSupplierName;
    @Generated
    private String tbomDeliveryNoteNumber;
    @Generated
    private Long tbomMatrevId;
    @Generated
    private Long tbomMatrevMatId;
    @Generated
    private String tbomMatrevMatMaterialNumber;
    @Generated
    private String tbomMatrevMatSapNumber;
    @Generated
    private String tbomMatrevMatMaterialHierarchy;
    @Generated
    private String tbomMatrevMatShortText;
    @Generated
    private Long tbomTbomitemId;
    @Generated
    private Long tbomTbomitemMatId;
    @Generated
    private String tbomTbomitemMatMaterialNumber;
    @Generated
    private String tbomTbomitemMatSapNumber;
    @Generated
    private String tbomTbomitemMatMaterialHierarchy;
    @Generated
    private String tbomTbomitemMatShortText;
    @Generated
    private String tbomMatrevRevisionNumber;
    @Generated
    private LocalDate tbomProductionDate;
    @Generated
    private String tbomOrderNumber;
    @Generated
    private String tbomLotNumber;
    @Generated
    private String tbomTbomitemManufacturerName;
    @Generated
    private String tbomTbomitemOrderCode;
    @Generated
    private String tbomTbomitemDateCode;
    @Generated
    private Integer tbomTbomitemQuantity;
    @Generated
    private String tbomTbomitemInfoField1;
    @Generated
    private String tbomTbomitemInfoField2;

    /**
     * Default constructor
     */
    @Generated
    public SerialObjectTraceBoMSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param id
     */
    @Generated
    public SerialObjectTraceBoMSearchDTO(long id) {
        this.id = id;
    }

    /**
     * Constructor using fields
     * @param serialNumber
     * @param assemblyDate
     * @param customerSerialNumber
     * @param productionOrderNumber
     * @param id
     * @param creationDate
     * @param lastUpdate
     * @param soMatId
     * @param tbomSupplierCode
     * @param tbomSupplierName
     * @param tbomDeliveryNoteNumber
     * @param tbomMatrevId
     * @param tbomMatrevMatId
     * @param tbomMatrevMatMaterialNumber
     * @param tbomMatrevMatSapNumber
     * @param tbomMatrevMatMaterialHierarchy
     * @param tbomMatrevMatShortText
     * @param tbomTbomitemId
     * @param tbomTbomitemMatId
     * @param tbomTbomitemMatMaterialNumber
     * @param tbomTbomitemMatSapNumber
     * @param tbomTbomitemMatMaterialHierarchy
     * @param tbomTbomitemMatShortText
     * @param tbomMatrevRevisionNumber
     * @param tbomProductionDate
     * @param tbomOrderNumber
     * @param tbomLotNumber
     * @param tbomTbomitemManufacturerName
     * @param tbomTbomitemOrderCode
     * @param tbomTbomitemDateCode
     * @param tbomTbomitemQuantity
     * @param tbomTbomitemInfoField1
     * @param tbomTbomitemInfoField2
     */
    @Generated
    public SerialObjectTraceBoMSearchDTO(String serialNumber, LocalDate assemblyDate, String customerSerialNumber, String productionOrderNumber,
            long id, LocalDateTime creationDate, LocalDateTime lastUpdate, long soMatId, String tbomSupplierCode, String tbomSupplierName,
            String tbomDeliveryNoteNumber, Long tbomMatrevId, Long tbomMatrevMatId, String tbomMatrevMatMaterialNumber, String tbomMatrevMatSapNumber,
            String tbomMatrevMatMaterialHierarchy, String tbomMatrevMatShortText, Long tbomTbomitemId, Long tbomTbomitemMatId,
            String tbomTbomitemMatMaterialNumber, String tbomTbomitemMatSapNumber, String tbomTbomitemMatMaterialHierarchy,
            String tbomTbomitemMatShortText, String tbomMatrevRevisionNumber, LocalDate tbomProductionDate, String tbomOrderNumber,
            String tbomLotNumber, String tbomTbomitemManufacturerName, String tbomTbomitemOrderCode, String tbomTbomitemDateCode,
            Integer tbomTbomitemQuantity, String tbomTbomitemInfoField1, String tbomTbomitemInfoField2) {
        this.serialNumber = serialNumber;
        this.assemblyDate = assemblyDate;
        this.customerSerialNumber = customerSerialNumber;
        this.productionOrderNumber = productionOrderNumber;
        this.id = id;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.soMatId = soMatId;
        this.tbomSupplierCode = tbomSupplierCode;
        this.tbomSupplierName = tbomSupplierName;
        this.tbomDeliveryNoteNumber = tbomDeliveryNoteNumber;
        this.tbomMatrevId = tbomMatrevId;
        this.tbomMatrevMatId = tbomMatrevMatId;
        this.tbomMatrevMatMaterialNumber = tbomMatrevMatMaterialNumber;
        this.tbomMatrevMatSapNumber = tbomMatrevMatSapNumber;
        this.tbomMatrevMatMaterialHierarchy = tbomMatrevMatMaterialHierarchy;
        this.tbomMatrevMatShortText = tbomMatrevMatShortText;
        this.tbomTbomitemId = tbomTbomitemId;
        this.tbomTbomitemMatId = tbomTbomitemMatId;
        this.tbomTbomitemMatMaterialNumber = tbomTbomitemMatMaterialNumber;
        this.tbomTbomitemMatSapNumber = tbomTbomitemMatSapNumber;
        this.tbomTbomitemMatMaterialHierarchy = tbomTbomitemMatMaterialHierarchy;
        this.tbomTbomitemMatShortText = tbomTbomitemMatShortText;
        this.tbomMatrevRevisionNumber = tbomMatrevRevisionNumber;
        this.tbomProductionDate = tbomProductionDate;
        this.tbomOrderNumber = tbomOrderNumber;
        this.tbomLotNumber = tbomLotNumber;
        this.tbomTbomitemManufacturerName = tbomTbomitemManufacturerName;
        this.tbomTbomitemOrderCode = tbomTbomitemOrderCode;
        this.tbomTbomitemDateCode = tbomTbomitemDateCode;
        this.tbomTbomitemQuantity = tbomTbomitemQuantity;
        this.tbomTbomitemInfoField1 = tbomTbomitemInfoField1;
        this.tbomTbomitemInfoField2 = tbomTbomitemInfoField2;
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
     * @return the customer serial number
     */
    @Generated
    public String getCustomerSerialNumber() {
        return this.customerSerialNumber;
    }

    /**
     * @param customerSerialNumber the customer serial number to set
     */
    @Generated
    public void setCustomerSerialNumber(String customerSerialNumber) {
        this.customerSerialNumber = customerSerialNumber;
    }

    /**
     * @return the production order number
     */
    @Generated
    public String getProductionOrderNumber() {
        return this.productionOrderNumber;
    }

    /**
     * @param productionOrderNumber the production order number to set
     */
    @Generated
    public void setProductionOrderNumber(String productionOrderNumber) {
        this.productionOrderNumber = productionOrderNumber;
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
     * @return the id of the material
     */
    @Generated
    public long getSoMatId() {
        return this.soMatId;
    }

    /**
     * @param soMatId the id of the material to set
     */
    @Generated
    public void setSoMatId(long soMatId) {
        this.soMatId = soMatId;
    }

    /**
     * @return the code of the supplier
     */
    @Generated
    public String getTbomSupplierCode() {
        return this.tbomSupplierCode;
    }

    /**
     * @param tbomSupplierCode the code of the supplier to set
     */
    @Generated
    public void setTbomSupplierCode(String tbomSupplierCode) {
        this.tbomSupplierCode = tbomSupplierCode;
    }

    /**
     * @return the name of the supplier
     */
    @Generated
    public String getTbomSupplierName() {
        return this.tbomSupplierName;
    }

    /**
     * @param tbomSupplierName the name of the supplier to set
     */
    @Generated
    public void setTbomSupplierName(String tbomSupplierName) {
        this.tbomSupplierName = tbomSupplierName;
    }

    /**
     * @return the delivery note number of the trace BoM
     */
    @Generated
    public String getTbomDeliveryNoteNumber() {
        return this.tbomDeliveryNoteNumber;
    }

    /**
     * @param tbomDeliveryNoteNumber the delivery note number of the trace BoM to set
     */
    @Generated
    public void setTbomDeliveryNoteNumber(String tbomDeliveryNoteNumber) {
        this.tbomDeliveryNoteNumber = tbomDeliveryNoteNumber;
    }

    /**
     * @return the id of the material revision
     */
    @Generated
    public Long getTbomMatrevId() {
        return this.tbomMatrevId;
    }

    /**
     * @param tbomMatrevId the id of the material revision to set
     */
    @Generated
    public void setTbomMatrevId(Long tbomMatrevId) {
        this.tbomMatrevId = tbomMatrevId;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public Long getTbomMatrevMatId() {
        return this.tbomMatrevMatId;
    }

    /**
     * @param tbomMatrevMatId the id of the material to set
     */
    @Generated
    public void setTbomMatrevMatId(Long tbomMatrevMatId) {
        this.tbomMatrevMatId = tbomMatrevMatId;
    }

    /**
     * @return the material number of the material
     */
    @Generated
    public String getTbomMatrevMatMaterialNumber() {
        return this.tbomMatrevMatMaterialNumber;
    }

    /**
     * @param tbomMatrevMatMaterialNumber the material number of the material to set
     */
    @Generated
    public void setTbomMatrevMatMaterialNumber(String tbomMatrevMatMaterialNumber) {
        this.tbomMatrevMatMaterialNumber = tbomMatrevMatMaterialNumber;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getTbomMatrevMatSapNumber() {
        return this.tbomMatrevMatSapNumber;
    }

    /**
     * @param tbomMatrevMatSapNumber the sap number of the material to set
     */
    @Generated
    public void setTbomMatrevMatSapNumber(String tbomMatrevMatSapNumber) {
        this.tbomMatrevMatSapNumber = tbomMatrevMatSapNumber;
    }

    /**
     * @return the material hierarchy of the material
     */
    @Generated
    public String getTbomMatrevMatMaterialHierarchy() {
        return this.tbomMatrevMatMaterialHierarchy;
    }

    /**
     * @param tbomMatrevMatMaterialHierarchy the material hierarchy of the material to set
     */
    @Generated
    public void setTbomMatrevMatMaterialHierarchy(String tbomMatrevMatMaterialHierarchy) {
        this.tbomMatrevMatMaterialHierarchy = tbomMatrevMatMaterialHierarchy;
    }

    /**
     * @return the short text of the material
     */
    @Generated
    public String getTbomMatrevMatShortText() {
        return this.tbomMatrevMatShortText;
    }

    /**
     * @param tbomMatrevMatShortText the short text of the material to set
     */
    @Generated
    public void setTbomMatrevMatShortText(String tbomMatrevMatShortText) {
        this.tbomMatrevMatShortText = tbomMatrevMatShortText;
    }

    /**
     * @return the id of the trace BoM item
     */
    @Generated
    public Long getTbomTbomitemId() {
        return this.tbomTbomitemId;
    }

    /**
     * @param tbomTbomitemId the id of the trace BoM item to set
     */
    @Generated
    public void setTbomTbomitemId(Long tbomTbomitemId) {
        this.tbomTbomitemId = tbomTbomitemId;
    }

    /**
     * @return the id of the material
     */
    @Generated
    public Long getTbomTbomitemMatId() {
        return this.tbomTbomitemMatId;
    }

    /**
     * @param tbomTbomitemMatId the id of the material to set
     */
    @Generated
    public void setTbomTbomitemMatId(Long tbomTbomitemMatId) {
        this.tbomTbomitemMatId = tbomTbomitemMatId;
    }

    /**
     * @return the material number of the material
     */
    @Generated
    public String getTbomTbomitemMatMaterialNumber() {
        return this.tbomTbomitemMatMaterialNumber;
    }

    /**
     * @param tbomTbomitemMatMaterialNumber the material number of the material to set
     */
    @Generated
    public void setTbomTbomitemMatMaterialNumber(String tbomTbomitemMatMaterialNumber) {
        this.tbomTbomitemMatMaterialNumber = tbomTbomitemMatMaterialNumber;
    }

    /**
     * @return the sap number of the material
     */
    @Generated
    public String getTbomTbomitemMatSapNumber() {
        return this.tbomTbomitemMatSapNumber;
    }

    /**
     * @param tbomTbomitemMatSapNumber the sap number of the material to set
     */
    @Generated
    public void setTbomTbomitemMatSapNumber(String tbomTbomitemMatSapNumber) {
        this.tbomTbomitemMatSapNumber = tbomTbomitemMatSapNumber;
    }

    /**
     * @return the material hierarchy of the material
     */
    @Generated
    public String getTbomTbomitemMatMaterialHierarchy() {
        return this.tbomTbomitemMatMaterialHierarchy;
    }

    /**
     * @param tbomTbomitemMatMaterialHierarchy the material hierarchy of the material to set
     */
    @Generated
    public void setTbomTbomitemMatMaterialHierarchy(String tbomTbomitemMatMaterialHierarchy) {
        this.tbomTbomitemMatMaterialHierarchy = tbomTbomitemMatMaterialHierarchy;
    }

    /**
     * @return the short text of the material
     */
    @Generated
    public String getTbomTbomitemMatShortText() {
        return this.tbomTbomitemMatShortText;
    }

    /**
     * @param tbomTbomitemMatShortText the short text of the material to set
     */
    @Generated
    public void setTbomTbomitemMatShortText(String tbomTbomitemMatShortText) {
        this.tbomTbomitemMatShortText = tbomTbomitemMatShortText;
    }

    /**
     * @return the revision number of the material revision
     */
    @Generated
    public String getTbomMatrevRevisionNumber() {
        return this.tbomMatrevRevisionNumber;
    }

    /**
     * @param tbomMatrevRevisionNumber the revision number of the material revision to set
     */
    @Generated
    public void setTbomMatrevRevisionNumber(String tbomMatrevRevisionNumber) {
        this.tbomMatrevRevisionNumber = tbomMatrevRevisionNumber;
    }

    /**
     * @return the production date of the trace BoM
     */
    @Generated
    public LocalDate getTbomProductionDate() {
        return this.tbomProductionDate;
    }

    /**
     * @param tbomProductionDate the production date of the trace BoM to set
     */
    @Generated
    public void setTbomProductionDate(LocalDate tbomProductionDate) {
        this.tbomProductionDate = tbomProductionDate;
    }

    /**
     * @return the order number of the trace BoM
     */
    @Generated
    public String getTbomOrderNumber() {
        return this.tbomOrderNumber;
    }

    /**
     * @param tbomOrderNumber the order number of the trace BoM to set
     */
    @Generated
    public void setTbomOrderNumber(String tbomOrderNumber) {
        this.tbomOrderNumber = tbomOrderNumber;
    }

    /**
     * @return the lot number of the trace BoM
     */
    @Generated
    public String getTbomLotNumber() {
        return this.tbomLotNumber;
    }

    /**
     * @param tbomLotNumber the lot number of the trace BoM to set
     */
    @Generated
    public void setTbomLotNumber(String tbomLotNumber) {
        this.tbomLotNumber = tbomLotNumber;
    }

    /**
     * @return the manufacturer name of the trace BoM item
     */
    @Generated
    public String getTbomTbomitemManufacturerName() {
        return this.tbomTbomitemManufacturerName;
    }

    /**
     * @param tbomTbomitemManufacturerName the manufacturer name of the trace BoM item to set
     */
    @Generated
    public void setTbomTbomitemManufacturerName(String tbomTbomitemManufacturerName) {
        this.tbomTbomitemManufacturerName = tbomTbomitemManufacturerName;
    }

    /**
     * @return the order code of the trace BoM item
     */
    @Generated
    public String getTbomTbomitemOrderCode() {
        return this.tbomTbomitemOrderCode;
    }

    /**
     * @param tbomTbomitemOrderCode the order code of the trace BoM item to set
     */
    @Generated
    public void setTbomTbomitemOrderCode(String tbomTbomitemOrderCode) {
        this.tbomTbomitemOrderCode = tbomTbomitemOrderCode;
    }

    /**
     * @return the date code of the trace BoM item
     */
    @Generated
    public String getTbomTbomitemDateCode() {
        return this.tbomTbomitemDateCode;
    }

    /**
     * @param tbomTbomitemDateCode the date code of the trace BoM item to set
     */
    @Generated
    public void setTbomTbomitemDateCode(String tbomTbomitemDateCode) {
        this.tbomTbomitemDateCode = tbomTbomitemDateCode;
    }

    /**
     * @return the quantity of the trace BoM item
     */
    @Generated
    public Integer getTbomTbomitemQuantity() {
        return this.tbomTbomitemQuantity;
    }

    /**
     * @param tbomTbomitemQuantity the quantity of the trace BoM item to set
     */
    @Generated
    public void setTbomTbomitemQuantity(Integer tbomTbomitemQuantity) {
        this.tbomTbomitemQuantity = tbomTbomitemQuantity;
    }

    /**
     * @return the info field 1 of the trace BoM item
     */
    @Generated
    public String getTbomTbomitemInfoField1() {
        return this.tbomTbomitemInfoField1;
    }

    /**
     * @param tbomTbomitemInfoField1 the info field 1 of the trace BoM item to set
     */
    @Generated
    public void setTbomTbomitemInfoField1(String tbomTbomitemInfoField1) {
        this.tbomTbomitemInfoField1 = tbomTbomitemInfoField1;
    }

    /**
     * @return the info field 2 of the trace BoM item
     */
    @Generated
    public String getTbomTbomitemInfoField2() {
        return this.tbomTbomitemInfoField2;
    }

    /**
     * @param tbomTbomitemInfoField2 the info field 2 of the trace BoM item to set
     */
    @Generated
    public void setTbomTbomitemInfoField2(String tbomTbomitemInfoField2) {
        this.tbomTbomitemInfoField2 = tbomTbomitemInfoField2;
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

        final var dto = (SerialObjectTraceBoMSearchDTO) obj;

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