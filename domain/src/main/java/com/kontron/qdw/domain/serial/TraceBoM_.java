package com.kontron.qdw.domain.serial;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(TraceBoM.class)
public class TraceBoM_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<TraceBoM, String> deliveryNoteNumber;
    @Generated
    public static volatile SingularAttribute<TraceBoM, String> lotNumber;
    @Generated
    public static volatile SingularAttribute<TraceBoM, String> orderNumber;
    @Generated
    public static volatile SingularAttribute<TraceBoM, LocalDate> productionDate;
    @Generated
    public static volatile SingularAttribute<TraceBoM, MaterialRevision> materialRevision;
    @Generated
    public static volatile SingularAttribute<TraceBoM, Supplier> supplier;
    @Generated
    public static volatile CollectionAttribute<TraceBoM, TraceBoMItem> traceBoMItems;
    @Generated
    public static volatile CollectionAttribute<TraceBoM, IllegalTraceBoMItem> illegalTraceBoMItems;
}