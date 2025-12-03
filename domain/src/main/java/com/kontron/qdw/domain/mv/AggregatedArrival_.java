package com.kontron.qdw.domain.mv;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(AggregatedArrival.class)
public class AggregatedArrival_ extends AbstractAggregatedBase_ {
    @Generated
    public static volatile SingularAttribute<AggregatedArrival, Integer> arrivals;
    @Generated
    public static volatile SingularAttribute<AggregatedArrival, MaterialRevision> materialRevision;
    @Generated
    public static volatile SingularAttribute<AggregatedArrival, Plant> plant;
    @Generated
    public static volatile SingularAttribute<AggregatedArrival, MovementType> movementType;
    @Generated
    public static volatile SingularAttribute<AggregatedArrival, Supplier> supplier;
}