package com.kontron.qdw.domain.material;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import java.util.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@Entity
@Table(name = "ews_entry_tab")
@NamedQuery(name = EWSEntry.NQ_GET_MATERIAL, query = "select b from EWSEntry a join a.material b where a.id = :id")
@NamedQuery(name = EWSEntry.NQ_GET_RECEIVERS, query = "select b from EWSEntry a join a.receivers b where a.id = :id")
@NamedQuery(name = EWSEntry.NQ_DELETE_ALL, query = "delete from EWSEntry a")
@NamedQuery(name = EWSEntry.NQ_DELETE, query = "delete from EWSEntry a where a.id = :id")
@NamedQuery(name = EWSEntry.NQ_GET_ALL, query = "select a from EWSEntry a")
@NamedQuery(name = EWSEntry.NQ_FIND, query = "select a from EWSEntry a where a.id = :id")
@NamedQuery(name = EWSEntry.NQ_CHECK, query = "select count(a) from EWSEntry a where a.id = :id")
@NamedQuery(name = EWSEntry.NQ_COUNT, query = "select count(a) from EWSEntry a")
public class EWSEntry extends AbstractEntityWithId {
    @Generated
    public static final String NQ_DELETE_ALL = "EWSEntry.deleteAll";
    @Generated
    public static final String NQ_COUNT = "EWSEntry.count";
    @Generated
    public static final String NQ_DELETE = "EWSEntry.delete";
    @Generated
    public static final String NQ_GET_RECEIVERS = "EWSEntry.getReceivers";
    @Generated
    public static final String NQ_GET_ALL = "EWSEntry.getAll";
    @Generated
    public static final String NQ_GET_MATERIAL = "EWSEntry.getMaterial";
    @Generated
    public static final String NQ_CHECK = "EWSEntry.check";
    @Generated
    public static final String NQ_FIND = "EWSEntry.find";
    @Basic(optional = false)
    @Column(name = "threshold", nullable = false, updatable = true, insertable = true)
    @Generated
    private double threshold;
    @Basic(optional = false)
    @Column(name = "board_or_system", nullable = false, updatable = true, insertable = true)
    @Generated
    private boolean boardOrSystem;
    @Column(name = "filter_criterion", nullable = true, updatable = true, insertable = true, length = 1000)
    @Size(max = 1000, message = "Length of field \"filterCriterion\" is illegal!")
    @Generated
    private String filterCriterion;
    @Enumerated(EnumType.STRING)
    @Basic(optional = false)
    @Column(name = "ews_type", nullable = false, updatable = true, insertable = true)
    @NotNull(message = "Field \"type\" must not be null!")
    @Generated
    private EWSType type;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "material", referencedColumnName = "id", nullable = false)
    @NotNull(message = "Field \"material\" must not be null!")
    @Generated
    private Material material;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "ews_entry_users_tab", joinColumns = { @JoinColumn(name = "ews_entry_pk") }, inverseJoinColumns = {
            @JoinColumn(name = "user_pk") })
    @Generated
    private Collection<User> receivers = new ArrayList<>();

    /**
     * Default constructor
     */
    @Generated
    public EWSEntry() {
    }

    /**
     * Constructor using primary key field
     * @param id
     */
    @Generated
    public EWSEntry(long id) {
        super(id);
    }

    /**
     * @return the threshold
     */
    @Generated
    public double getThreshold() {
        return this.threshold;
    }

    /**
     * @param threshold the threshold to set
     */
    @Generated
    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    /**
     * @return true if the board or system flag is set
     */
    @Generated
    public boolean isBoardOrSystem() {
        return this.boardOrSystem;
    }

    /**
     * @param boardOrSystem the value of the board or system flag to set
     */
    @Generated
    public void setBoardOrSystem(boolean boardOrSystem) {
        this.boardOrSystem = boardOrSystem;
    }

    /**
     * @return the filter criterion
     */
    @Generated
    public String getFilterCriterion() {
        return this.filterCriterion;
    }

    /**
     * @param filterCriterion the filter criterion to set
     */
    @Generated
    public void setFilterCriterion(String filterCriterion) {
        this.filterCriterion = filterCriterion;
    }

    /**
     * @return the type
     */
    @Generated
    public EWSType getType() {
        return this.type;
    }

    /**
     * @param type the type to set
     */
    @Generated
    public void setType(EWSType type) {
        this.type = type;
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
     * @return a collection of users
     */
    @Generated
    public Collection<User> getReceivers() {
        return this.receivers;
    }

    /**
     * @param receivers the users to set
     */
    @Generated
    public void setReceivers(Collection<User> receivers) {
        this.receivers = receivers;
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

        final var bean = (EWSEntry) obj;

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