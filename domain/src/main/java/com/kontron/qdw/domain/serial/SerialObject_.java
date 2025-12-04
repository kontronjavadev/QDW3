package com.kontron.qdw.domain.serial;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(SerialObject.class)
public class SerialObject_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<SerialObject, String> serialNumber;
    @Generated
    public static volatile SingularAttribute<SerialObject, LocalDate> assemblyDate;
    @Generated
    public static volatile SingularAttribute<SerialObject, String> customerSerialNumber;
    @Generated
    public static volatile SingularAttribute<SerialObject, String> productionOrderNumber;
    @Generated
    public static volatile SingularAttribute<SerialObject, Material> material;
    @Generated
    public static volatile SingularAttribute<SerialObject, SerialObject> parentObject;
    @Generated
    public static volatile CollectionAttribute<SerialObject, SerialObject> serialObjects;
    @Generated
    public static volatile CollectionAttribute<SerialObject, AssemblyRecord> assemblyRecords;
    @Generated
    public static volatile CollectionAttribute<SerialObject, Arrival> arrivals;
    @Generated
    public static volatile CollectionAttribute<SerialObject, Shipment> shipments;
    @Generated
    public static volatile SingularAttribute<SerialObject, TraceBoM> traceBom;
}