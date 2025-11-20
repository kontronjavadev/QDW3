package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(UserProperty.class)
public class UserProperty_ {
    @Generated
    public static volatile SingularAttribute<UserProperty, Long> id;
    @Generated
    public static volatile SingularAttribute<UserProperty, String> propKey;
    @Generated
    public static volatile SingularAttribute<UserProperty, String> viewName;
    @Generated
    public static volatile SingularAttribute<UserProperty, String> value;
    @Generated
    public static volatile SingularAttribute<UserProperty, User> user;
}