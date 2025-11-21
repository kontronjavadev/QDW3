package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import java.util.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(PasswordReset.class)
public class PasswordReset_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<PasswordReset, UUID> uuid;
    @Generated
    public static volatile SingularAttribute<PasswordReset, User> user;
}