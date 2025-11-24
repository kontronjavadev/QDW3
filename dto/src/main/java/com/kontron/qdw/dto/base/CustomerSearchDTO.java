package com.kontron.qdw.dto.base;

import java.time.*;
import java.io.Serializable;
import net.sourceforge.jbizmo.commons.annotation.Generated;

public class CustomerSearchDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;
    @Generated
    public static final String ATTR_NAME = "name";
    @Generated
    public static final String ATTR_STREET = "street";
    @Generated
    public static final String ATTR_ZIPCODE = "zipCode";
    @Generated
    public static final String ATTR_CITY = "city";
    @Generated
    public static final String ATTR_INTERNAL = "internal";
    @Generated
    public static final String ATTR_CODE = "code";
    @Generated
    public static final String ATTR_SHORTTEXT = "shortText";
    @Generated
    public static final String ATTR_COMMENT = "comment";
    @Generated
    public static final String ATTR_CREATIONDATE = "creationDate";
    @Generated
    public static final String ATTR_LASTUPDATE = "lastUpdate";
    @Generated
    public static final String ATTR_COUNTRYNAME = "countryName";
    @Generated
    public static final String ATTR_COUNTRYCODE = "countryCode";
    @Generated
    public static final String SELECT_NAME = "a.name";
    @Generated
    public static final String SELECT_STREET = "a.street";
    @Generated
    public static final String SELECT_ZIPCODE = "a.zipCode";
    @Generated
    public static final String SELECT_CITY = "a.city";
    @Generated
    public static final String SELECT_INTERNAL = "a.internal";
    @Generated
    public static final String SELECT_CODE = "a.code";
    @Generated
    public static final String SELECT_SHORTTEXT = "a.shortText";
    @Generated
    public static final String SELECT_COMMENT = "a.comment";
    @Generated
    public static final String SELECT_CREATIONDATE = "a.creationDate";
    @Generated
    public static final String SELECT_LASTUPDATE = "a.lastUpdate";
    @Generated
    public static final String SELECT_COUNTRYNAME = "b.name";
    @Generated
    public static final String SELECT_COUNTRYCODE = "b.code";
    @Generated
    private String name;
    @Generated
    private String street;
    @Generated
    private String zipCode;
    @Generated
    private String city;
    @Generated
    private boolean internal;
    @Generated
    private String code;
    @Generated
    private String shortText;
    @Generated
    private String comment;
    @Generated
    private LocalDateTime creationDate;
    @Generated
    private LocalDateTime lastUpdate;
    @Generated
    private String countryName;
    @Generated
    private String countryCode;

    /**
     * Default constructor
     */
    @Generated
    public CustomerSearchDTO() {
    }

    /**
     * Constructor with ID attribute
     * @param code
     */
    @Generated
    public CustomerSearchDTO(String code) {
        this.code = code;
    }

    /**
     * Constructor with ID and display attribute
     * @param name
     * @param code
     */
    @Generated
    public CustomerSearchDTO(String name, String code) {
        this.name = name;
        this.code = code;
    }

    /**
     * Constructor using fields
     * @param name
     * @param street
     * @param zipCode
     * @param city
     * @param internal
     * @param code
     * @param shortText
     * @param comment
     * @param creationDate
     * @param lastUpdate
     * @param countryName
     * @param countryCode
     */
    @Generated
    public CustomerSearchDTO(String name, String street, String zipCode, String city, boolean internal, String code, String shortText, String comment,
            LocalDateTime creationDate, LocalDateTime lastUpdate, String countryName, String countryCode) {
        this.name = name;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
        this.internal = internal;
        this.code = code;
        this.shortText = shortText;
        this.comment = comment;
        this.creationDate = creationDate;
        this.lastUpdate = lastUpdate;
        this.countryName = countryName;
        this.countryCode = countryCode;
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
     * @return the code
     */
    @Generated
    public String getCode() {
        return this.code;
    }

    /**
     * @param code the code to set
     */
    @Generated
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the short text
     */
    @Generated
    public String getShortText() {
        return this.shortText;
    }

    /**
     * @param shortText the short text to set
     */
    @Generated
    public void setShortText(String shortText) {
        this.shortText = shortText;
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
     * @return the creation date
     */
    @Generated
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    /**
     * @param creationDate the creation date to set
     */
    @Generated
    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the last update
     */
    @Generated
    public LocalDateTime getLastUpdate() {
        return this.lastUpdate;
    }

    /**
     * @param lastUpdate the last update to set
     */
    @Generated
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return the name of the country
     */
    @Generated
    public String getCountryName() {
        return this.countryName;
    }

    /**
     * @param countryName the name of the country to set
     */
    @Generated
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the code of the country
     */
    @Generated
    public String getCountryCode() {
        return this.countryCode;
    }

    /**
     * @param countryCode the code of the country to set
     */
    @Generated
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
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

        final var dto = (CustomerSearchDTO) obj;

        if (this.code == null) {
            if (dto.getCode() != null)
                return false;
        }
        else if (!this.code.equals(dto.getCode()) && !this.name.equals(dto.getName()))
            return false;

        return true;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Generated
    @Override
    public int hashCode() {
        return (code == null) ? 0 : code.hashCode();
    }

}