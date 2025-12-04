package com.kontron.qdw.domain.service;

import com.kontron.qdw.domain.mv.*;
import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(ServiceOrder.class)
public class ServiceOrder_ extends AbstractFunctionalActiveEntity_ {
    @Generated
    public static volatile SingularAttribute<ServiceOrder, LocalDate> documentDate;
    @Generated
    public static volatile SingularAttribute<ServiceOrder, ServiceOrderType> serviceOrderType;
    @Generated
    public static volatile SingularAttribute<ServiceOrder, Customer> customer;
    @Generated
    public static volatile SingularAttribute<ServiceOrder, Supplier> supplier;
    @Generated
    public static volatile CollectionAttribute<ServiceOrder, ServiceMessage> serviceMessages;
}