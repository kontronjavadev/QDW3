package com.kontron.qdw.dto.base;

import java.io.Serializable;

import net.sourceforge.jbizmo.commons.annotation.Generated;

public class UserPropertyDTO implements Serializable {
    @Generated
    private static final long serialVersionUID = 1L;

    @Generated
    public static final String ATTR_ID = "id";
    @Generated
    public static final String ATTR_PROPKEY = "propKey";
    @Generated
    public static final String ATTR_VIEWNAME = "viewName";
    @Generated
    public static final String ATTR_VALUE = "value";
    @Generated
    public static final String ATTR_USERID = "userId";

    public static final String SELECT_ID = "a." + ATTR_ID;
    public static final String SELECT_PROPKEY = "a." + ATTR_PROPKEY;
    public static final String SELECT_VIEWNAME = "a." + ATTR_VIEWNAME;
    public static final String SELECT_VALUE = "a." + ATTR_VALUE;
    public static final String SELECT_USERID = "a.user.id";

    @Generated
    private long id;
    @Generated
    private String propKey;
    @Generated
    private String viewName = "";
    @Generated
    private String value;
    @Generated
    private long userId;



    @Generated
    public UserPropertyDTO() {
    }

    @Generated
    public UserPropertyDTO(long id) {
        this.id = id;
    }

    @Generated
    public UserPropertyDTO(long id, String propKey, String viewName, String value, long userId) {
        this.id = id;
        this.propKey = propKey;
        this.viewName = viewName;
        this.value = value;
        this.userId = userId;
    }



    @Override
    public String toString() {
        return "UserProperty " + viewName + "#" + propKey + " für userId " + userId + ": " + value;
    }

    @Generated
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final var dto = (UserPropertyDTO) obj;

        return this.id == dto.getId();
    }

    @Generated
    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }



    @Generated
    public long getId() {
        return this.id;
    }

    @Generated
    public void setId(long id) {
        this.id = id;
    }

    @Generated
    public String getPropKey() {
        return this.propKey;
    }

    @Generated
    public void setPropKey(String propKey) {
        this.propKey = propKey;
    }

    @Generated
    public String getViewName() {
        return this.viewName;
    }

    @Generated
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    @Generated
    public String getValue() {
        return this.value;
    }

    @Generated
    public void setValue(String value) {
        this.value = value;
    }

    @Generated
    public long getUserId() {
        return this.userId;
    }

    @Generated
    public void setUserId(long userId) {
        this.userId = userId;
    }

}
