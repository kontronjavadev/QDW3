package com.kontron.qdw.domain.material;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "material_number_set_tab")
@NamedQuery(name = MaterialNumberSet.NQ_UK_FIND_BY_LABEL, query = "select a from MaterialNumberSet a where a.label = :label")
@NamedQuery(name = MaterialNumberSet.NQ_UK_SEARCH_BY_LABEL, query = "select a from MaterialNumberSet a where a.label like :label")
@NamedQuery(name = MaterialNumberSet.NQ_UK_EXISTS_BY_LABEL, query = "select count(a) from MaterialNumberSet a where a.label = :label")
@NamedQuery(name = MaterialNumberSet.NQ_UK_EXISTS_BY_LABEL_AND_ID, query = "select count(a) from MaterialNumberSet a where a.label = :label and a.id <> :id")
@NamedQuery(name = MaterialNumberSet.NQ_GET_MATERIALS, query = "select b from MaterialNumberSet a join a.materials b where a.id = :id")
@NamedQuery(name = MaterialNumberSet.NQ_DELETE_ALL, query = "delete from MaterialNumberSet a")
@NamedQuery(name = MaterialNumberSet.NQ_DELETE, query = "delete from MaterialNumberSet a where a.id = :id")
@NamedQuery(name = MaterialNumberSet.NQ_GET_ALL, query = "select a from MaterialNumberSet a")
@NamedQuery(name = MaterialNumberSet.NQ_FIND, query = "select a from MaterialNumberSet a where a.id = :id")
@NamedQuery(name = MaterialNumberSet.NQ_CHECK, query = "select count(a) from MaterialNumberSet a where a.id = :id")
@NamedQuery(name = MaterialNumberSet.NQ_COUNT, query = "select count(a) from MaterialNumberSet a")
public class MaterialNumberSet extends AbstractEntityWithId {
    @Generated
    public static final String NQ_UK_FIND_BY_LABEL = "MaterialNumberSet.getByLabel";
    @Generated
    public static final String NQ_UK_EXISTS_BY_LABEL_AND_ID = "MaterialNumberSet.checkByLabelAndId";
    @Generated
    public static final String NQ_DELETE_ALL = "MaterialNumberSet.deleteAll";
    @Generated
    public static final String NQ_UK_EXISTS_BY_LABEL = "MaterialNumberSet.checkByLabel";
    @Generated
    public static final String NQ_COUNT = "MaterialNumberSet.count";
    @Generated
    public static final String NQ_DELETE = "MaterialNumberSet.delete";
    @Generated
    public static final String NQ_GET_MATERIALS = "MaterialNumberSet.getMaterials";
    @Generated
    public static final String NQ_GET_ALL = "MaterialNumberSet.getAll";
    @Generated
    public static final String NQ_UK_SEARCH_BY_LABEL = "MaterialNumberSet.findByLabel";
    @Generated
    public static final String NQ_CHECK = "MaterialNumberSet.check";
    @Generated
    public static final String NQ_FIND = "MaterialNumberSet.find";
    @Basic(optional = false)
    @Column(name = "label", nullable = false, updatable = true, insertable = true, length = 50, unique = true)
    @NotNull(message = "Field \"label\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"label\" is illegal!")
    @Generated
    private String label;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "material_number_set_materials_tab", joinColumns = { @JoinColumn(name = "material_number_set_pk") }, inverseJoinColumns = {
            @JoinColumn(name = "material_pk") })
    @Generated
    private Collection<Material> materials = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public MaterialNumberSet() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public MaterialNumberSet(long id) {
        super(id);
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

        final var bean = (MaterialNumberSet) obj;

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