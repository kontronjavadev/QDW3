package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(Customer.class)
public class Customer_ extends AbstractFuntionalEntity_ {
    @Generated
    public static volatile SingularAttribute<Customer, String> name;
    @Generated
    public static volatile SingularAttribute<Customer, String> street;
    @Generated
    public static volatile SingularAttribute<Customer, String> zipCode;
    @Generated
    public static volatile SingularAttribute<Customer, String> city;
    @Generated
    public static volatile SingularAttribute<Customer, Boolean> internal;
    @Generated
    public static volatile SingularAttribute<Customer, Country> country;
    @Generated
    public static volatile SingularAttribute<Customer, VerticalSector> verticalSector;
}