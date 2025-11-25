package com.kontron.qdw.domain.material;

import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "material_class_tab")
@NamedQuery(name = MaterialClass.NQ_DELETE_ALL, query = "delete from MaterialClass a")
@NamedQuery(name = MaterialClass.NQ_DELETE, query = "delete from MaterialClass a where a.code = :code")
@NamedQuery(name = MaterialClass.NQ_GET_ALL, query = "select a from MaterialClass a")
@NamedQuery(name = MaterialClass.NQ_FIND, query = "select a from MaterialClass a where a.code = :code")
@NamedQuery(name = MaterialClass.NQ_CHECK, query = "select count(a) from MaterialClass a where a.code = :code")
@NamedQuery(name = MaterialClass.NQ_COUNT, query = "select count(a) from MaterialClass a")
public class MaterialClass extends AbstractFuntionalEntity {
    @Generated
    public static final String NQ_DELETE_ALL = "MaterialClass.deleteAll";
    @Generated
    public static final String NQ_COUNT = "MaterialClass.count";
    @Generated
    public static final String NQ_DELETE = "MaterialClass.delete";
    @Generated
    public static final String NQ_GET_ALL = "MaterialClass.getAll";
    @Generated
    public static final String NQ_CHECK = "MaterialClass.check";
    @Generated
    public static final String NQ_FIND = "MaterialClass.find";

    /**
     * Default constructor
     */
    @Generated
    public MaterialClass() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public MaterialClass(String code) {
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

        final var bean = (MaterialClass) obj;

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