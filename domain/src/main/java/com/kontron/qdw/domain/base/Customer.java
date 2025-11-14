package com.kontron.qdw.domain.base;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@Entity
@Table(name = "customer_tab")
@NamedQuery(name = Customer.NQ_UK_FIND_BY_NAME, query = "select a from Customer a where a.name = :name")
@NamedQuery(name = Customer.NQ_UK_SEARCH_BY_NAME, query = "select a from Customer a where a.name like :name")
@NamedQuery(name = Customer.NQ_UK_EXISTS_BY_NAME, query = "select count(a) from Customer a where a.name = :name")
@NamedQuery(name = Customer.NQ_UK_EXISTS_BY_NAME_AND_CODE, query = "select count(a) from Customer a where a.name = :name and a.code <> :code")
@NamedQuery(name = Customer.NQ_GET_COUNTRY, query = "select b from Customer a join a.country b where a.code = :code")
@NamedQuery(name = Customer.NQ_GET_VERTICALSECTOR, query = "select b from Customer a join a.verticalSector b where a.code = :code")
@NamedQuery(name = Customer.NQ_DELETE_ALL, query = "delete from Customer a")
@NamedQuery(name = Customer.NQ_DELETE, query = "delete from Customer a where a.code = :code")
@NamedQuery(name = Customer.NQ_GET_ALL, query = "select a from Customer a")
@NamedQuery(name = Customer.NQ_FIND, query = "select a from Customer a where a.code = :code")
@NamedQuery(name = Customer.NQ_CHECK, query = "select count(a) from Customer a where a.code = :code")
@NamedQuery(name = Customer.NQ_COUNT, query = "select count(a) from Customer a")
public class Customer extends AbstractFuntionalEntity {
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME = "Customer.checkByName";
    @Generated
    public static final String NQ_UK_EXISTS_BY_NAME_AND_CODE = "Customer.checkByNameAndCode";
    @Generated
    public static final String NQ_UK_FIND_BY_NAME = "Customer.getByName";
    @Generated
    public static final String NQ_DELETE_ALL = "Customer.deleteAll";
    @Generated
    public static final String NQ_GET_COUNTRY = "Customer.getCountry";
    @Generated
    public static final String NQ_GET_VERTICALSECTOR = "Customer.getVerticalSector";
    @Generated
    public static final String NQ_COUNT = "Customer.count";
    @Generated
    public static final String NQ_DELETE = "Customer.delete";
    @Generated
    public static final String NQ_GET_ALL = "Customer.getAll";
    @Generated
    public static final String NQ_UK_SEARCH_BY_NAME = "Customer.findByName";
    @Generated
    public static final String NQ_CHECK = "Customer.check";
    @Generated
    public static final String NQ_FIND = "Customer.find";
    @Basic(optional = false)
    @Column(name = "name", nullable = false, updatable = true, insertable = true, length = 100, unique = true)
    @NotNull(message = "Field \"name\" must not be null!")
    @Size(max = 100, message = "Length of field \"name\" is illegal!")
    @Generated
    private String name;
    @Basic(optional = false)
    @Column(name = "street", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"street\" must not be null!")
    @Size(max = 100, message = "Length of field \"street\" is illegal!")
    @Generated
    private String street;
    @Basic(optional = false)
    @Column(name = "zip_code", nullable = false, updatable = true, insertable = true, length = 20)
    @NotNull(message = "Field \"zipCode\" must not be null!")
    @Size(max = 20, message = "Length of field \"zipCode\" is illegal!")
    @Generated
    private String zipCode;
    @Basic(optional = false)
    @Column(name = "city", nullable = false, updatable = true, insertable = true, length = 100)
    @NotNull(message = "Field \"city\" must not be null!")
    @Size(max = 100, message = "Length of field \"city\" is illegal!")
    @Generated
    private String city;
    @Basic(optional = false)
    @Column(name = "internal", nullable = false, updatable = true, insertable = true)
    @Generated
    private boolean internal;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "country", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"country\" must not be null!")
    @Generated
    private Country country;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "vertical_sector", referencedColumnName = "code", nullable = false)
    @NotNull(message = "Field \"verticalSector\" must not be null!")
    @Generated
    private VerticalSector verticalSector;

    /**
     * Default constructor
     */
    @Generated
    public Customer() {
    }

    /**
     * Constructor using primary key field
     * @param code
     */
    @Generated
    public Customer(String code) {
        super(code);
    }

    /**
     * Constructor using primary key field and display attribute
     * @param code
     * @param name
     */
    @Generated
    public Customer(String code, String name) {
        super(code);

        this.name = name;
    }

    /**
     * @return the name
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * @param name the name to set
     */
    @Generated
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the street
     */
    @Generated
    public String getStreet() {
        return this.street;
    }

    /**
     * @param street the street to set
     */
    @Generated
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * @return the zip code
     */
    @Generated
    public String getZipCode() {
        return this.zipCode;
    }

    /**
     * @param zipCode the zip code to set
     */
    @Generated
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return the city
     */
    @Generated
    public String getCity() {
        return this.city;
    }

    /**
     * @param city the city to set
     */
    @Generated
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return true if the internal flag is set
     */
    @Generated
    public boolean isInternal() {
        return this.internal;
    }

    /**
     * @param internal the value of the internal flag to set
     */
    @Generated
    public void setInternal(boolean internal) {
        this.internal = internal;
    }

    /**
     * @return the country
     */
    @Generated
    public Country getCountry() {
        return this.country;
    }

    /**
     * @param country the country to set
     */
    @Generated
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * @return the vertical sector
     */
    @Generated
    public VerticalSector getVerticalSector() {
        return this.verticalSector;
    }

    /**
     * @param verticalSector the vertical sector to set
     */
    @Generated
    public void setVerticalSector(VerticalSector verticalSector) {
        this.verticalSector = verticalSector;
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

        final var bean = (Customer) obj;

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