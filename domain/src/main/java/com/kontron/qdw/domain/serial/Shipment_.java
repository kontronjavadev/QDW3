package com.kontron.qdw.domain.serial;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(Shipment.class)
public class Shipment_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<Shipment, String> orderNumber;
    @Generated
    public static volatile SingularAttribute<Shipment, Boolean> rebuildFlag;
    @Generated
    public static volatile SingularAttribute<Shipment, LocalDate> shipmentDate;
    @Generated
    public static volatile SingularAttribute<Shipment, Customer> customer;
    @Generated
    public static volatile SingularAttribute<Shipment, MaterialRevision> materialRevision;
    @Generated
    public static volatile SingularAttribute<Shipment, MovementType> movementType;
    @Generated
    public static volatile SingularAttribute<Shipment, Plant> plant;
    @Generated
    public static volatile SingularAttribute<Shipment, SerialObject> serialObject;
}