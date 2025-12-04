package com.kontron.qdw.domain.serial;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(AssemblyRecord.class)
public class AssemblyRecord_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<AssemblyRecord, LocalDate> assemblyDate;
    @Generated
    public static volatile SingularAttribute<AssemblyRecord, String> productionOrderNumber;
    @Generated
    public static volatile SingularAttribute<AssemblyRecord, MaterialRevision> materialRevision;
    @Generated
    public static volatile SingularAttribute<AssemblyRecord, SerialObject> parentSerialObject;
    @Generated
    public static volatile SingularAttribute<AssemblyRecord, SerialObject> serialObject;
}