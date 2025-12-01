package com.kontron.qdw.domain.material;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "serial_number_set_item_tab")
@NamedQuery(name = SerialNumberSetItem.NQ_GET_SERIALNUMBERSET, query = "select b from SerialNumberSetItem a join a.serialNumberSet b where a.id = :id")
@NamedQuery(name = SerialNumberSetItem.NQ_DELETE_ALL, query = "delete from SerialNumberSetItem a")
@NamedQuery(name = SerialNumberSetItem.NQ_DELETE, query = "delete from SerialNumberSetItem a where a.id = :id")
@NamedQuery(name = SerialNumberSetItem.NQ_GET_ALL, query = "select a from SerialNumberSetItem a")
@NamedQuery(name = SerialNumberSetItem.NQ_FIND, query = "select a from SerialNumberSetItem a where a.id = :id")
@NamedQuery(name = SerialNumberSetItem.NQ_CHECK, query = "select count(a) from SerialNumberSetItem a where a.id = :id")
@NamedQuery(name = SerialNumberSetItem.NQ_COUNT, query = "select count(a) from SerialNumberSetItem a")
public class SerialNumberSetItem {
    @Generated
    public static final String NQ_DELETE_ALL = "SerialNumberSetItem.deleteAll";
    @Generated
    public static final String NQ_COUNT = "SerialNumberSetItem.count";
    @Generated
    public static final String NQ_DELETE = "SerialNumberSetItem.delete";
    @Generated
    public static final String NQ_GET_ALL = "SerialNumberSetItem.getAll";
    @Generated
    public static final String NQ_GET_SERIALNUMBERSET = "SerialNumberSetItem.getSerialNumberSet";
    @Generated
    public static final String NQ_CHECK = "SerialNumberSetItem.check";
    @Generated
    public static final String NQ_FIND = "SerialNumberSetItem.find";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Generated
    private long id;
    @Basic(optional = false)
    @Column(name = "serial_number", nullable = false, updatable = true, insertable = true, length = 50)
    @NotNull(message = "Field \"serialNumber\" must not be null!")
    @Size(min = 1, max = 50, message = "Length of field \"serialNumber\" is illegal!")
    @Generated
    private String serialNumber;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "serial_number_set", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"serialNumberSet\" must not be null!")
    @Generated
    private SerialNumberSet serialNumberSet;

    /**
     * Default constructor
     */
    @Generated
    public SerialNumberSetItem() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public SerialNumberSetItem(long id) {
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
     * @return the serial number set
     */
    @Generated
    public SerialNumberSet getSerialNumberSet() {
        return this.serialNumberSet;
    }

    /**
     * @param serialNumberSet the serial number set to set
     */
    @Generated
    public void setSerialNumberSet(SerialNumberSet serialNumberSet) {
        this.serialNumberSet = serialNumberSet;
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

        final var bean = (SerialNumberSetItem) obj;

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