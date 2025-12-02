package com.kontron.qdw.domain.mv;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@MappedSuperclass
public class MaterializedEntitiy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Generated
    private long id;
    @Basic(optional = false)
    @Column(name = "serial_object_id", nullable = false, updatable = true, insertable = true)
    @Generated
    private long serialObjectId;
    @Column(name = "parent_serial_object_id", nullable = true, updatable = true, insertable = true)
    @Generated
    private Long parentSerialObjectId;
    @Basic(optional = false)
    @Column(name = "serial_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"serialNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"serialNumber\" is illegal!")
    @Generated
    private String serialNumber;
    @Column(name = "parent_serial_number", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"parentSerialNumber\" is illegal!")
    @Generated
    private String parentSerialNumber;
    @Basic(optional = false)
    @Column(name = "material_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"materialNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"materialNumber\" is illegal!")
    @Generated
    private String materialNumber;
    @Column(name = "parent_material_number", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"parentMaterialNumber\" is illegal!")
    @Generated
    private String parentMaterialNumber;
    @Basic(optional = false)
    @Column(name = "material_type", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"materialType\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"materialType\" is illegal!")
    @Generated
    private String materialType;
    @Column(name = "parent_material_type", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"parentMaterialType\" is illegal!")
    @Generated
    private String parentMaterialType;
    @Basic(optional = false)
    @Column(name = "material_short_text", nullable = false, updatable = true, insertable = true, length = 200)
    @NotNull(message = "Field \"materialShortText\" must not be null!")
    @Size(max = 200, message = "Length of field \"materialShortText\" is illegal!")
    @Generated
    private String materialShortText;
    @Column(name = "parent_material_short_text", nullable = true, updatable = true, insertable = true, length = 200)
    @Size(max = 200, message = "Length of field \"parentMaterialShortText\" is illegal!")
    @Generated
    private String parentMaterialShortText;
    @Basic(optional = false)
    @Column(name = "sap_number", nullable = false, updatable = true, insertable = true, length = 20)
    @NotNull(message = "Field \"sapNumber\" must not be null!")
    @Size(min = 1, max = 20, message = "Length of field \"sapNumber\" is illegal!")
    @Generated
    private String sapNumber;
    @Column(name = "parent_sap_number", nullable = true, updatable = true, insertable = true, length = 20)
    @Size(max = 20, message = "Length of field \"parentSapNumber\" is illegal!")
    @Generated
    private String parentSapNumber;
    @Basic(optional = false)
    @Column(name = "material_hierarchy", nullable = false, updatable = true, insertable = true, length = 250)
    @NotNull(message = "Field \"materialHierarchy\" must not be null!")
    @Size(max = 250, message = "Length of field \"materialHierarchy\" is illegal!")
    @Generated
    private String materialHierarchy;
    @Column(name = "parent_material_hierarchy", nullable = true, updatable = true, insertable = true, length = 250)
    @Size(max = 250, message = "Length of field \"parentMaterialHierarchy\" is illegal!")
    @Generated
    private String parentMaterialHierarchy;
    @Basic(optional = false)
    @Column(name = "revision_id", nullable = false, updatable = true, insertable = true)
    @Generated
    private long revisionId;
    @Column(name = "parent_revision_id", nullable = true, updatable = true, insertable = true)
    @Generated
    private Long parentRevisionId;
    @Basic(optional = false)
    @Column(name = "revision_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"revisionNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"revisionNumber\" is illegal!")
    @Generated
    private String revisionNumber;
    @Column(name = "parent_revision_number", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"parentRevisionNumber\" is illegal!")
    @Generated
    private String parentRevisionNumber;
    @Column(name = "assembly_date", nullable = true, updatable = true, insertable = true)
    @Generated
    private LocalDate assemblyDate;
    @Column(name = "assembly_po", nullable = true, updatable = true, insertable = true, length = 50)
    @Size(max = 50, message = "Length of field \"assemblyPO\" is illegal!")
    @Generated
    private String assemblyPO;
    @Basic(optional = false)
    @Column(name = "plant", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"plant\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"plant\" is illegal!")
    @Generated
    private String plant;

    /**
     * Default constructor
     */
    @Generated
    public MaterializedEntitiy() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public MaterializedEntitiy(long id) {
        this.id = id;
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
     * @return the serial object id
     */
    @Generated
    public long getSerialObjectId() {
        return this.serialObjectId;
    }

    /**
     * @param serialObjectId the serial object id to set
     */
    @Generated
    public void setSerialObjectId(long serialObjectId) {
        this.serialObjectId = serialObjectId;
    }

    /**
     * @return the parent serial object id
     */
    @Generated
    public Long getParentSerialObjectId() {
        return this.parentSerialObjectId;
    }

    /**
     * @param parentSerialObjectId the parent serial object id to set
     */
    @Generated
    public void setParentSerialObjectId(Long parentSerialObjectId) {
        this.parentSerialObjectId = parentSerialObjectId;
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
     * @return the material number
     */
    @Generated
    public String getMaterialNumber() {
        return this.materialNumber;
    }

    /**
     * @param materialNumber the material number to set
     */
    @Generated
    public void setMaterialNumber(String materialNumber) {
        this.materialNumber = materialNumber;
    }

    /**
     * @return the parent material number
     */
    @Generated
    public String getParentMaterialNumber() {
        return this.parentMaterialNumber;
    }

    /**
     * @param parentMaterialNumber the parent material number to set
     */
    @Generated
    public void setParentMaterialNumber(String parentMaterialNumber) {
        this.parentMaterialNumber = parentMaterialNumber;
    }

    /**
     * @return the material type
     */
    @Generated
    public String getMaterialType() {
        return this.materialType;
    }

    /**
     * @param materialType the material type to set
     */
    @Generated
    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    /**
     * @return the parent material type
     */
    @Generated
    public String getParentMaterialType() {
        return this.parentMaterialType;
    }

    /**
     * @param parentMaterialType the parent material type to set
     */
    @Generated
    public void setParentMaterialType(String parentMaterialType) {
        this.parentMaterialType = parentMaterialType;
    }

    /**
     * @return the material short text
     */
    @Generated
    public String getMaterialShortText() {
        return this.materialShortText;
    }

    /**
     * @param materialShortText the material short text to set
     */
    @Generated
    public void setMaterialShortText(String materialShortText) {
        this.materialShortText = materialShortText;
    }

    /**
     * @return the parent material short text
     */
    @Generated
    public String getParentMaterialShortText() {
        return this.parentMaterialShortText;
    }

    /**
     * @param parentMaterialShortText the parent material short text to set
     */
    @Generated
    public void setParentMaterialShortText(String parentMaterialShortText) {
        this.parentMaterialShortText = parentMaterialShortText;
    }

    /**
     * @return the sap number
     */
    @Generated
    public String getSapNumber() {
        return this.sapNumber;
    }

    /**
     * @param sapNumber the sap number to set
     */
    @Generated
    public void setSapNumber(String sapNumber) {
        this.sapNumber = sapNumber;
    }

    /**
     * @return the parent sap number
     */
    @Generated
    public String getParentSapNumber() {
        return this.parentSapNumber;
    }

    /**
     * @param parentSapNumber the parent sap number to set
     */
    @Generated
    public void setParentSapNumber(String parentSapNumber) {
        this.parentSapNumber = parentSapNumber;
    }

    /**
     * @return the material hierarchy
     */
    @Generated
    public String getMaterialHierarchy() {
        return this.materialHierarchy;
    }

    /**
     * @param materialHierarchy the material hierarchy to set
     */
    @Generated
    public void setMaterialHierarchy(String materialHierarchy) {
        this.materialHierarchy = materialHierarchy;
    }

    /**
     * @return the parent material hierarchy
     */
    @Generated
    public String getParentMaterialHierarchy() {
        return this.parentMaterialHierarchy;
    }

    /**
     * @param parentMaterialHierarchy the parent material hierarchy to set
     */
    @Generated
    public void setParentMaterialHierarchy(String parentMaterialHierarchy) {
        this.parentMaterialHierarchy = parentMaterialHierarchy;
    }

    /**
     * @return the revision id
     */
    @Generated
    public long getRevisionId() {
        return this.revisionId;
    }

    /**
     * @param revisionId the revision id to set
     */
    @Generated
    public void setRevisionId(long revisionId) {
        this.revisionId = revisionId;
    }

    /**
     * @return the parent revision id
     */
    @Generated
    public Long getParentRevisionId() {
        return this.parentRevisionId;
    }

    /**
     * @param parentRevisionId the parent revision id to set
     */
    @Generated
    public void setParentRevisionId(Long parentRevisionId) {
        this.parentRevisionId = parentRevisionId;
    }

    /**
     * @return the revision no.
     */
    @Generated
    public String getRevisionNumber() {
        return this.revisionNumber;
    }

    /**
     * @param revisionNumber the revision no. to set
     */
    @Generated
    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    /**
     * @return the parent revision number
     */
    @Generated
    public String getParentRevisionNumber() {
        return this.parentRevisionNumber;
    }

    /**
     * @param parentRevisionNumber the parent revision number to set
     */
    @Generated
    public void setParentRevisionNumber(String parentRevisionNumber) {
        this.parentRevisionNumber = parentRevisionNumber;
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

        final var bean = (MaterializedEntitiy) obj;

        return getId() == bean.getId();
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }

}