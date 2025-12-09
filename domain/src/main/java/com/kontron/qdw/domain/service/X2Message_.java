package com.kontron.qdw.domain.service;

import com.kontron.qdw.domain.serial.*;
import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;
import com.kontron.qdw.domain.material.*;

@StaticMetamodel(X2Message.class)
public class X2Message_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<X2Message, String> analysisText;
    @Generated
    public static volatile SingularAttribute<X2Message, String> causeText;
    @Generated
    public static volatile SingularAttribute<X2Message, String> customerReport;
    @Generated
    public static volatile SingularAttribute<X2Message, String> defectComponent;
    @Generated
    public static volatile SingularAttribute<X2Message, String> designator;
    @Generated
    public static volatile SingularAttribute<X2Message, String> workCenter;
    @Generated
    public static volatile SingularAttribute<X2Message, Material> defectMaterial;
    @Generated
    public static volatile SingularAttribute<X2Message, FaultAnalysis> faultAnalysis;
    @Generated
    public static volatile SingularAttribute<X2Message, MaterialRevision> materialRevision;
    @Generated
    public static volatile SingularAttribute<X2Message, RepairErrorCode> repairErrorCode;
    @Generated
    public static volatile SingularAttribute<X2Message, RepairState> repairState;
    @Generated
    public static volatile SingularAttribute<X2Message, RepairTask> repairTask;
    @Generated
    public static volatile SingularAttribute<X2Message, SerialObject> serialObject;
    @Generated
    public static volatile SingularAttribute<X2Message, ServiceMessage> serviceMessage;
}