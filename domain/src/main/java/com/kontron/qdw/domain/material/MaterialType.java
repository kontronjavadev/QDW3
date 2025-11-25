package com.kontron.qdw.domain.material;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "material_type_tab")
@NamedQuery(name = MaterialType.NQ_DELETE_ALL, query = "delete from MaterialType a")
@NamedQuery(name = MaterialType.NQ_DELETE, query = "delete from MaterialType a where a.code = :code")
@NamedQuery(name = MaterialType.NQ_GET_ALL, query = "select a from MaterialType a")
@NamedQuery(name = MaterialType.NQ_FIND, query = "select a from MaterialType a where a.code = :code")
@NamedQuery(name = MaterialType.NQ_CHECK, query = "select count(a) from MaterialType a where a.code = :code")
@NamedQuery(name = MaterialType.NQ_COUNT, query = "select count(a) from MaterialType a")
public class MaterialType extends AbstractFuntionalEntity {
    @Generated
    public static final String NQ_DELETE_ALL = "MaterialType.deleteAll";
    @Generated
    public static final String NQ_COUNT = "MaterialType.count";
    @Generated
    public static final String NQ_DELETE = "MaterialType.delete";
    @Generated
    public static final String NQ_GET_ALL = "MaterialType.getAll";
    @Generated
    public static final String NQ_CHECK = "MaterialType.check";
    @Generated
    public static final String NQ_FIND = "MaterialType.find";

    /**
     * Default constructor
     */
    @Generated
    public MaterialType() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public MaterialType(String code) {
        super(code);
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

        final var bean = (MaterialType) obj;

        if (getCode() == null) {
            if (bean.getCode() != null)
                return false;
        }
        else if (!getCode().equals(bean.getCode()))
            return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        // Return hash code of current date if primary key field is not yet set!
        if (getCode() == null)
            return new java.util.Date().hashCode();

        return getCode().hashCode();
    }

}