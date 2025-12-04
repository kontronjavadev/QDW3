package com.kontron.qdw.domain.serial;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(IllegalTraceBoMItem.class)
public class IllegalTraceBoMItem_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<IllegalTraceBoMItem, String> dateCode;
    @Generated
    public static volatile SingularAttribute<IllegalTraceBoMItem, String> manufacturer;
    @Generated
    public static volatile SingularAttribute<IllegalTraceBoMItem, String> manufacturerRevision;
    @Generated
    public static volatile SingularAttribute<IllegalTraceBoMItem, String> materialNumber;
    @Generated
    public static volatile SingularAttribute<IllegalTraceBoMItem, String> orderCode;
    @Generated
    public static volatile SingularAttribute<IllegalTraceBoMItem, TraceBoM> traceBom;
}