package com.kontron.qdw.domain.mv;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(AggregatedShipment.class)
public class AggregatedShipment_ extends AbstractAggregatedBase_ {
    @Generated
    public static volatile SingularAttribute<AggregatedShipment, Integer> shipments;
    @Generated
    public static volatile SingularAttribute<AggregatedShipment, MaterialRevision> materialRevision;
    @Generated
    public static volatile SingularAttribute<AggregatedShipment, Plant> plant;
    @Generated
    public static volatile SingularAttribute<AggregatedShipment, Customer> customer;
    @Generated
    public static volatile SingularAttribute<AggregatedShipment, MovementType> movementType;
}