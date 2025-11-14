package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(Supplier.class)
public class Supplier_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<Supplier, String> name;
    @Generated
    public static volatile SingularAttribute<Supplier, String> street;
    @Generated
    public static volatile SingularAttribute<Supplier, String> zipCode;
    @Generated
    public static volatile SingularAttribute<Supplier, String> city;
    @Generated
    public static volatile SingularAttribute<Supplier, Country> country;
}