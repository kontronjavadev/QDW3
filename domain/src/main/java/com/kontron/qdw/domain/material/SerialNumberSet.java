package com.kontron.qdw.domain.material;

import jakarta.persistence.*;
import java.util.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "serial_number_set_tab")
@NamedQuery(name = SerialNumberSet.NQ_GET_SERIALNUMBERS, query = "select b from SerialNumberSet a join a.serialNumbers b where a.code = :code")
@NamedQuery(name = SerialNumberSet.NQ_DELETE_ALL, query = "delete from SerialNumberSet a")
@NamedQuery(name = SerialNumberSet.NQ_DELETE, query = "delete from SerialNumberSet a where a.code = :code")
@NamedQuery(name = SerialNumberSet.NQ_GET_ALL, query = "select a from SerialNumberSet a")
@NamedQuery(name = SerialNumberSet.NQ_FIND, query = "select a from SerialNumberSet a where a.code = :code")
@NamedQuery(name = SerialNumberSet.NQ_CHECK, query = "select count(a) from SerialNumberSet a where a.code = :code")
@NamedQuery(name = SerialNumberSet.NQ_COUNT, query = "select count(a) from SerialNumberSet a")
public class SerialNumberSet extends AbstractFuntionalEntity {
    @Generated
    public static final String NQ_DELETE_ALL = "SerialNumberSet.deleteAll";
    @Generated
    public static final String NQ_COUNT = "SerialNumberSet.count";
    @Generated
    public static final String NQ_DELETE = "SerialNumberSet.delete";
    @Generated
    public static final String NQ_GET_ALL = "SerialNumberSet.getAll";
    @Generated
    public static final String NQ_GET_SERIALNUMBERS = "SerialNumberSet.getSerialNumbers";
    @Generated
    public static final String NQ_CHECK = "SerialNumberSet.check";
    @Generated
    public static final String NQ_FIND = "SerialNumberSet.find";
    @OneToMany(targetEntity = SerialNumberSetItem.class, mappedBy = "serialNumberSet", fetch = FetchType.LAZY, cascade = { CascadeType.ALL })
    @Generated
    private Collection<SerialNumberSetItem> serialNumbers = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public SerialNumberSet() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public SerialNumberSet(String code) {
        super(code);
    }

    /**
     * @return a collection of serial number set items
     */
    @Generated
    public Collection<SerialNumberSetItem> getSerialNumbers() {
        return this.serialNumbers;
    }

    /**
     * @param serialNumbers the serial number set items to set
     */
    @Generated
    public void setSerialNumbers(Collection<SerialNumberSetItem> serialNumbers) {
        this.serialNumbers = serialNumbers;
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

        final var bean = (SerialNumberSet) obj;

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