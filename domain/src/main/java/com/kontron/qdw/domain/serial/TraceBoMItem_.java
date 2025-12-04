package com.kontron.qdw.domain.serial;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(TraceBoMItem.class)
public class TraceBoMItem_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, String> dateCode;
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, String> infoField1;
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, String> infoField2;
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, String> infoField3;
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, String> infoField4;
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, String> manufacturerName;
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, String> manufacturerRevision;
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, String> orderCode;
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, Integer> quantity;
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, Material> material;
    @Generated
    public static volatile SingularAttribute<TraceBoMItem, TraceBoM> traceBom;
}