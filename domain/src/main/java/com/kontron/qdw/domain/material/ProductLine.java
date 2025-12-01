package com.kontron.qdw.domain.material;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "product_line_tab")
@NamedQuery(name = ProductLine.NQ_UK_FIND_BY_LABEL, query = "select a from ProductLine a where a.label = :label")
@NamedQuery(name = ProductLine.NQ_UK_SEARCH_BY_LABEL, query = "select a from ProductLine a where a.label like :label")
@NamedQuery(name = ProductLine.NQ_UK_EXISTS_BY_LABEL, query = "select count(a) from ProductLine a where a.label = :label")
@NamedQuery(name = ProductLine.NQ_UK_EXISTS_BY_LABEL_AND_ID, query = "select count(a) from ProductLine a where a.label = :label and a.id <> :id")
@NamedQuery(name = ProductLine.NQ_GET_MATERIALS, query = "select b from ProductLine a join a.materials b where a.id = :id")
@NamedQuery(name = ProductLine.NQ_DELETE_ALL, query = "delete from ProductLine a")
@NamedQuery(name = ProductLine.NQ_DELETE, query = "delete from ProductLine a where a.id = :id")
@NamedQuery(name = ProductLine.NQ_GET_ALL, query = "select a from ProductLine a")
@NamedQuery(name = ProductLine.NQ_FIND, query = "select a from ProductLine a where a.id = :id")
@NamedQuery(name = ProductLine.NQ_CHECK, query = "select count(a) from ProductLine a where a.id = :id")
@NamedQuery(name = ProductLine.NQ_COUNT, query = "select count(a) from ProductLine a")
public class ProductLine extends AbstractEntityWithId {
    @Generated
    public static final String NQ_UK_FIND_BY_LABEL = "ProductLine.getByLabel";
    @Generated
    public static final String NQ_UK_EXISTS_BY_LABEL_AND_ID = "ProductLine.checkByLabelAndId";
    @Generated
    public static final String NQ_DELETE_ALL = "ProductLine.deleteAll";
    @Generated
    public static final String NQ_UK_EXISTS_BY_LABEL = "ProductLine.checkByLabel";
    @Generated
    public static final String NQ_COUNT = "ProductLine.count";
    @Generated
    public static final String NQ_DELETE = "ProductLine.delete";
    @Generated
    public static final String NQ_GET_MATERIALS = "ProductLine.getMaterials";
    @Generated
    public static final String NQ_GET_ALL = "ProductLine.getAll";
    @Generated
    public static final String NQ_UK_SEARCH_BY_LABEL = "ProductLine.findByLabel";
    @Generated
    public static final String NQ_CHECK = "ProductLine.check";
    @Generated
    public static final String NQ_FIND = "ProductLine.find";
    @Basic(optional = false)
    @Column(name = "label", nullable = false, updatable = true, insertable = true, length = 50, unique = true)
    @NotNull(message = "Field \"label\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"label\" is illegal!")
    @Generated
    private String label;
    @ManyToMany(mappedBy = "productLines", fetch = FetchType.LAZY)
    @Generated
    private Collection<Material> materials = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public ProductLine() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public ProductLine(long id) {
        super(id);
    }

    /**
     * Constructor using primary key field and display attribute
     * @param id
     * @param label
     */
    @Generated
    public ProductLine(long id, String label) {
        super(id);

        this.label = label;
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
     * @return a collection of materials
     */
    @Generated
    public Collection<Material> getMaterials() {
        return this.materials;
    }

    /**
     * @param materials the materials to set
     */
    @Generated
    public void setMaterials(Collection<Material> materials) {
        this.materials = materials;
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

        final var bean = (ProductLine) obj;

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