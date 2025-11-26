package com.kontron.qdw.domain.material;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "bom_item_tab")
@NamedQuery(name = BoMItem.NQ_GET_MATERIAL, query = "select b from BoMItem a join a.material b where a.id = :id")
@NamedQuery(name = BoMItem.NQ_GET_MATERIALREVISION, query = "select b from BoMItem a join a.materialRevision b where a.id = :id")
@NamedQuery(name = BoMItem.NQ_DELETE_ALL, query = "delete from BoMItem a")
@NamedQuery(name = BoMItem.NQ_DELETE, query = "delete from BoMItem a where a.id = :id")
@NamedQuery(name = BoMItem.NQ_GET_ALL, query = "select a from BoMItem a")
@NamedQuery(name = BoMItem.NQ_FIND, query = "select a from BoMItem a where a.id = :id")
@NamedQuery(name = BoMItem.NQ_CHECK, query = "select count(a) from BoMItem a where a.id = :id")
@NamedQuery(name = BoMItem.NQ_COUNT, query = "select count(a) from BoMItem a")
public class BoMItem extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "BoMItem.deleteAll";
    @Generated
    public static final String NQ_GET_MATERIALREVISION = "BoMItem.getMaterialRevision";
    @Generated
    public static final String NQ_COUNT = "BoMItem.count";
    @Generated
    public static final String NQ_DELETE = "BoMItem.delete";
    @Generated
    public static final String NQ_GET_ALL = "BoMItem.getAll";
    @Generated
    public static final String NQ_GET_MATERIAL = "BoMItem.getMaterial";
    @Generated
    public static final String NQ_CHECK = "BoMItem.check";
    @Generated
    public static final String NQ_FIND = "BoMItem.find";
    @Column(name = "quantity", nullable = true, updatable = true, insertable = true)
    @Generated
    private Double quantity;
    @Column(name = "label", nullable = true, updatable = true, insertable = true, length = 200)
    @Size(max = 200, message = "Length of field \"label\" is illegal!")
    @Generated
    private String label;
    @Column(name = "position", nullable = true, updatable = true, insertable = true, length = 20)
    @Size(max = 20, message = "Length of field \"position\" is illegal!")
    @Generated
    private String position;
    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "material", referencedColumnName = "id", nullable = true)
    @Generated
    private Material material;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "material_revision", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"materialRevision\" must not be null!")
    @Generated
    private MaterialRevision materialRevision;

    /**
     * Default constructor
     */
    @Generated
    public BoMItem() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public BoMItem(long id) {
        super(id);
    }

    /**
     * @return the quantity
     */
    @Generated
    public Double getQuantity() {
        return this.quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    @Generated
    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    /**
     * @return the label
     */
    @Generated
    public String getLabel() {
        return this.label;
    }

    /**
     * @param label the label to set
     */
    @Generated
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the position
     */
    @Generated
    public String getPosition() {
        return this.position;
    }

    /**
     * @param position the position to set
     */
    @Generated
    public void setPosition(String position) {
        this.position = position;
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
     * @return the material revision
     */
    @Generated
    public MaterialRevision getMaterialRevision() {
        return this.materialRevision;
    }

    /**
     * @param materialRevision the material revision to set
     */
    @Generated
    public void setMaterialRevision(MaterialRevision materialRevision) {
        this.materialRevision = materialRevision;
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

        final var bean = (BoMItem) obj;

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