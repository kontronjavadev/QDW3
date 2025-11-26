package com.kontron.qdw.domain.material;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "material_revision_tab")
@NamedQuery(name = MaterialRevision.NQ_GET_MATERIAL, query = "select b from MaterialRevision a join a.material b where a.id = :id")
@NamedQuery(name = MaterialRevision.NQ_GET_PLANT, query = "select b from MaterialRevision a join a.plant b where a.id = :id")
@NamedQuery(name = MaterialRevision.NQ_GET_BOMITEMS, query = "select b from MaterialRevision a join a.boMItems b where a.id = :id")
@NamedQuery(name = MaterialRevision.NQ_DELETE_ALL, query = "delete from MaterialRevision a")
@NamedQuery(name = MaterialRevision.NQ_DELETE, query = "delete from MaterialRevision a where a.id = :id")
@NamedQuery(name = MaterialRevision.NQ_GET_ALL, query = "select a from MaterialRevision a")
@NamedQuery(name = MaterialRevision.NQ_FIND, query = "select a from MaterialRevision a where a.id = :id")
@NamedQuery(name = MaterialRevision.NQ_CHECK, query = "select count(a) from MaterialRevision a where a.id = :id")
@NamedQuery(name = MaterialRevision.NQ_COUNT, query = "select count(a) from MaterialRevision a")
public class MaterialRevision extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "MaterialRevision.deleteAll";
    @Generated
    public static final String NQ_COUNT = "MaterialRevision.count";
    @Generated
    public static final String NQ_DELETE = "MaterialRevision.delete";
    @Generated
    public static final String NQ_GET_ALL = "MaterialRevision.getAll";
    @Generated
    public static final String NQ_GET_PLANT = "MaterialRevision.getPlant";
    @Generated
    public static final String NQ_GET_MATERIAL = "MaterialRevision.getMaterial";
    @Generated
    public static final String NQ_GET_BOMITEMS = "MaterialRevision.getBoMItems";
    @Generated
    public static final String NQ_CHECK = "MaterialRevision.check";
    @Generated
    public static final String NQ_FIND = "MaterialRevision.find";
    @Basic(optional = false)
    @Column(name = "revision_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"revisionNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"revisionNumber\" is illegal!")
    @Generated
    private String revisionNumber;
    @Column(name = "rev_2", nullable = true, updatable = true, insertable = true, length = 20)
    @Size(max = 20, message = "Length of field \"rev2\" is illegal!")
    @Generated
    private String rev2;
    @Column(name = "rev_6", nullable = true, updatable = true, insertable = true, length = 20)
    @Size(max = 20, message = "Length of field \"rev6\" is illegal!")
    @Generated
    private String rev6;
    @Column(name = "alternative_number", nullable = true, updatable = true, insertable = true, length = 20)
    @Size(max = 20, message = "Length of field \"alternativeNumber\" is illegal!")
    @Generated
    private String alternativeNumber;
    @Column(name = "comment", nullable = true, updatable = true, insertable = true, length = 4000)
    @Size(max = 4000, message = "Length of field \"comment\" is illegal!")
    @Generated
    private String comment;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"material\" must not be null!")
    @Generated
    private Material material;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "plant", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"plant\" must not be null!")
    @Generated
    private Plant plant;
    @OneToMany(targetEntity = BoMItem.class, mappedBy = "materialRevision", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<BoMItem> boMItems = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public MaterialRevision() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public MaterialRevision(long id) {
        super(id);
    }

    /**
     * @return the revision number
     */
    @Generated
    public String getRevisionNumber() {
        return this.revisionNumber;
    }

    /**
     * @param revisionNumber the revision number to set
     */
    @Generated
    public void setRevisionNumber(String revisionNumber) {
        this.revisionNumber = revisionNumber;
    }

    /**
     * @return the rev 2
     */
    @Generated
    public String getRev2() {
        return this.rev2;
    }

    /**
     * @param rev2 the rev 2 to set
     */
    @Generated
    public void setRev2(String rev2) {
        this.rev2 = rev2;
    }

    /**
     * @return the rev 6
     */
    @Generated
    public String getRev6() {
        return this.rev6;
    }

    /**
     * @param rev6 the rev 6 to set
     */
    @Generated
    public void setRev6(String rev6) {
        this.rev6 = rev6;
    }

    /**
     * @return the alternative number
     */
    @Generated
    public String getAlternativeNumber() {
        return this.alternativeNumber;
    }

    /**
     * @param alternativeNumber the alternative number to set
     */
    @Generated
    public void setAlternativeNumber(String alternativeNumber) {
        this.alternativeNumber = alternativeNumber;
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
     * @return the material
     */
    @Generated
    public Material getMaterial() {
        return this.material;
    }

    /**
     * @param material the material to set
     */
    @Generated
    public void setMaterial(Material material) {
        this.material = material;
    }

    /**
     * @return the plant
     */
    @Generated
    public Plant getPlant() {
        return this.plant;
    }

    /**
     * @param plant the plant to set
     */
    @Generated
    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    /**
     * @return a collection of bom items
     */
    @Generated
    public Collection<BoMItem> getBoMItems() {
        return this.boMItems;
    }

    /**
     * @param boMItems the bom items to set
     */
    @Generated
    public void setBoMItems(Collection<BoMItem> boMItems) {
        this.boMItems = boMItems;
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

        final var bean = (MaterialRevision) obj;

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