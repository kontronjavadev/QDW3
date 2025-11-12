package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(User.class)
public class User_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<User, String> name;
    @Generated
    public static volatile SingularAttribute<User, String> password;
    @Generated
    public static volatile SingularAttribute<User, String> email;
    @Generated
    public static volatile SingularAttribute<User, Boolean> active;
    @Generated
    public static volatile CollectionAttribute<User, Role> roles;
}