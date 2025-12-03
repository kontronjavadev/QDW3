package com.kontron.qdw.domain.mv;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(AggregatedShipmentArrival.class)
public class AggregatedShipmentArrival_ extends AbstractAggregatedBase_ {
    @Generated
    public static volatile SingularAttribute<AggregatedShipmentArrival, Integer> shipments;
    @Generated
    public static volatile SingularAttribute<AggregatedShipmentArrival, MaterialRevision> materialRevision;
    @Generated
    public static volatile SingularAttribute<AggregatedShipmentArrival, Plant> plant;
    @Generated
    public static volatile SingularAttribute<AggregatedShipmentArrival, Customer> customer;
    @Generated
    public static volatile SingularAttribute<AggregatedShipmentArrival, MovementType> shipmentMovementType;
    @Generated
    public static volatile SingularAttribute<AggregatedShipmentArrival, MovementType> arrivalMovementType;
    @Generated
    public static volatile SingularAttribute<AggregatedShipmentArrival, Supplier> supplier;
}