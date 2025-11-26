package com.kontron.qdw.domain.material;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "material_tab")
@NamedQuery(name = Material.NQ_UK_FIND_BY_MATERIALNUMBER, query = "select a from Material a where a.materialNumber = :materialNumber")
@NamedQuery(name = Material.NQ_UK_SEARCH_BY_MATERIALNUMBER, query = "select a from Material a where a.materialNumber like :materialNumber")
@NamedQuery(name = Material.NQ_UK_EXISTS_BY_MATERIALNUMBER, query = "select count(a) from Material a where a.materialNumber = :materialNumber")
@NamedQuery(name = Material.NQ_UK_EXISTS_BY_MATERIALNUMBER_AND_ID, query = "select count(a) from Material a where a.materialNumber = :materialNumber and a.id <> :id")
@NamedQuery(name = Material.NQ_UK_FIND_BY_SAPNUMBER, query = "select a from Material a where a.sapNumber = :sapNumber")
@NamedQuery(name = Material.NQ_UK_SEARCH_BY_SAPNUMBER, query = "select a from Material a where a.sapNumber like :sapNumber")
@NamedQuery(name = Material.NQ_UK_EXISTS_BY_SAPNUMBER, query = "select count(a) from Material a where a.sapNumber = :sapNumber")
@NamedQuery(name = Material.NQ_UK_EXISTS_BY_SAPNUMBER_AND_ID, query = "select count(a) from Material a where a.sapNumber = :sapNumber and a.id <> :id")
@NamedQuery(name = Material.NQ_GET_OWNERLOCATION, query = "select b from Material a join a.ownerLocation b where a.id = :id")
@NamedQuery(name = Material.NQ_GET_MATERIALCLASS, query = "select b from Material a join a.materialClass b where a.id = :id")
@NamedQuery(name = Material.NQ_GET_MATERIALTYPE, query = "select b from Material a join a.materialType b where a.id = :id")
@NamedQuery(name = Material.NQ_GET_REVISIONS, query = "select b from Material a join a.revisions b where a.id = :id")
@NamedQuery(name = Material.NQ_DELETE_ALL, query = "delete from Material a")
@NamedQuery(name = Material.NQ_DELETE, query = "delete from Material a where a.id = :id")
@NamedQuery(name = Material.NQ_GET_ALL, query = "select a from Material a")
@NamedQuery(name = Material.NQ_FIND, query = "select a from Material a where a.id = :id")
@NamedQuery(name = Material.NQ_CHECK, query = "select count(a) from Material a where a.id = :id")
@NamedQuery(name = Material.NQ_COUNT, query = "select count(a) from Material a")
public class Material extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "Material.deleteAll";
    @Generated
    public static final String NQ_GET_MATERIALCLASS = "Material.getMaterialClass";
    @Generated
    public static final String NQ_DELETE = "Material.delete";
    @Generated
    public static final String NQ_UK_FIND_BY_MATERIALNUMBER = "Material.getByMaterialNumber";
    @Generated
    public static final String NQ_GET_ALL = "Material.getAll";
    @Generated
    public static final String NQ_UK_SEARCH_BY_MATERIALNUMBER = "Material.findByMaterialNumber";
    @Generated
    public static final String NQ_UK_SEARCH_BY_SAPNUMBER = "Material.findBySapNumber";
    @Generated
    public static final String NQ_GET_MATERIALTYPE = "Material.getMaterialType";
    @Generated
    public static final String NQ_UK_FIND_BY_SAPNUMBER = "Material.getBySapNumber";
    @Generated
    public static final String NQ_CHECK = "Material.check";
    @Generated
    public static final String NQ_FIND = "Material.find";
    @Generated
    public static final String NQ_UK_EXISTS_BY_SAPNUMBER_AND_ID = "Material.checkBySapNumberAndId";
    @Generated
    public static final String NQ_UK_EXISTS_BY_MATERIALNUMBER = "Material.checkByMaterialNumber";
    @Generated
    public static final String NQ_COUNT = "Material.count";
    @Generated
    public static final String NQ_UK_EXISTS_BY_SAPNUMBER = "Material.checkBySapNumber";
    @Generated
    public static final String NQ_GET_REVISIONS = "Material.getRevisions";
    @Generated
    public static final String NQ_GET_OWNERLOCATION = "Material.getOwnerLocation";
    @Generated
    public static final String NQ_UK_EXISTS_BY_MATERIALNUMBER_AND_ID = "Material.checkByMaterialNumberAndId";
    @Basic(optional = false)
    @Column(name = "material_number", nullable = false, updatable = true, insertable = true, length = 50, unique = true)
    @NotNull(message = "Field \"materialNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"materialNumber\" is illegal!")
    @Generated
    private String materialNumber;
    @Basic(optional = false)
    @Column(name = "sap_number", nullable = false, updatable = true, insertable = true, length = 20, unique = true)
    @NotNull(message = "Field \"sapNumber\" must not be null!")
    @Size(min = 1, max = 20, message = "Length of field \"sapNumber\" is illegal!")
    @Generated
    private String sapNumber;
    @Basic(optional = false)
    @Column(name = "short_text", nullable = false, updatable = true, insertable = true, length = 200)
    @NotNull(message = "Field \"shortText\" must not be null!")
    @Size(max = 200, message = "Length of field \"shortText\" is illegal!")
    @Generated
    private String shortText;
    @Column(name = "comment", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"comment\" is illegal!")
    @Generated
    private String comment;
    @Column(name = "fit_value", nullable = true, updatable = true, insertable = true)
    @Generated
    private Double fitValue;
    @Column(name = "material_hierarchy", nullable = true, updatable = true, insertable = true, length = 250)
    @Size(max = 250, message = "Length of field \"materialHierarchy\" is illegal!")
    @Generated
    private String materialHierarchy;
    @Basic(optional = false)
    @Column(name = "search_sub_assemblies", nullable = false, updatable = true, insertable = true)
    @Generated
    private boolean searchSubAssemblies;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "owner_location", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"ownerLocation\" must not be null!")
    @Generated
    private Location ownerLocation;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "material_class", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"materialClass\" must not be null!")
    @Generated
    private MaterialClass materialClass;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "material_type", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"materialType\" must not be null!")
    @Generated
    private MaterialType materialType;
    @OneToMany(targetEntity = MaterialRevision.class, mappedBy = "material", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<MaterialRevision> revisions = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public Material() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public Material(long id) {
        super(id);
    }

    /**
     * Constructor using primary key field and display attribute
     * @param id
     * @param materialNumber
     */
    @Generated
    public Material(long id, String materialNumber) {
        super(id);

        this.materialNumber = materialNumber;
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
     * @return the short text
     */
    @Generated
    public String getShortText() {
        return this.shortText;
    }

    /**
     * @param shortText the short text to set
     */
    @Generated
    public void setShortText(String shortText) {
        this.shortText = shortText;
    }

    /**
     * @return the comment
     */
    @Generated
    public String getComment() {
        return this.comment;
    }

    /**
     * @param comment the comment to set
     */
    @Generated
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the fit value
     */
    @Generated
    public Double getFitValue() {
        return this.fitValue;
    }

    /**
     * @param fitValue the fit value to set
     */
    @Generated
    public void setFitValue(Double fitValue) {
        this.fitValue = fitValue;
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
     * @return true if the search sub assemblies flag is set
     */
    @Generated
    public boolean isSearchSubAssemblies() {
        return this.searchSubAssemblies;
    }

    /**
     * @param searchSubAssemblies the value of the search sub assemblies flag to set
     */
    @Generated
    public void setSearchSubAssemblies(boolean searchSubAssemblies) {
        this.searchSubAssemblies = searchSubAssemblies;
    }

    /**
     * @return the location
     */
    @Generated
    public Location getOwnerLocation() {
        return this.ownerLocation;
    }

    /**
     * @param ownerLocation the location to set
     */
    @Generated
    public void setOwnerLocation(Location ownerLocation) {
        this.ownerLocation = ownerLocation;
    }

    /**
     * @return the material class
     */
    @Generated
    public MaterialClass getMaterialClass() {
        return this.materialClass;
    }

    /**
     * @param materialClass the material class to set
     */
    @Generated
    public void setMaterialClass(MaterialClass materialClass) {
        this.materialClass = materialClass;
    }

    /**
     * @return the material type
     */
    @Generated
    public MaterialType getMaterialType() {
        return this.materialType;
    }

    /**
     * @param materialType the material type to set
     */
    @Generated
    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    /**
     * @return a collection of material revisions
     */
    @Generated
    public Collection<MaterialRevision> getRevisions() {
        return this.revisions;
    }

    /**
     * @param revisions the material revisions to set
     */
    @Generated
    public void setRevisions(Collection<MaterialRevision> revisions) {
        this.revisions = revisions;
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

        final var bean = (Material) obj;

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