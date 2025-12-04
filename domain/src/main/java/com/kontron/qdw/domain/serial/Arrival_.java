package com.kontron.qdw.domain.serial;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(Arrival.class)
public class Arrival_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<Arrival, LocalDate> arrivalDate;
    @Generated
    public static volatile SingularAttribute<Arrival, String> orderNumber;
    @Generated
    public static volatile SingularAttribute<Arrival, Boolean> rebuildFlag;
    @Generated
    public static volatile SingularAttribute<Arrival, MaterialRevision> materialRevision;
    @Generated
    public static volatile SingularAttribute<Arrival, MovementType> movementType;
    @Generated
    public static volatile SingularAttribute<Arrival, Plant> plant;
    @Generated
    public static volatile SingularAttribute<Arrival, SerialObject> serialObject;
    @Generated
    public static volatile SingularAttribute<Arrival, Supplier> supplier;
}