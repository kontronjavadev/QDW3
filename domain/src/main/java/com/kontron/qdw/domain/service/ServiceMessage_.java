package com.kontron.qdw.domain.service;

import com.kontron.qdw.domain.serial.*;
import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(ServiceMessage.class)
public class ServiceMessage_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> analysisText;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, LocalDate> basicFinishDate;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, LocalDate> basicStartDate;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> causeText;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, Boolean> customerFailure;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> customerReport;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> defectComponent;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> deliveryNoteNumber;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> designator;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, Boolean> epidemicFailure;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> errorId;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> externalReport;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, LocalDate> internalArrivalDate;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> internalReport;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, LocalDate> internalShipmentDate;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> origin;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, Integer> rebuildFlag;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> repairDescription;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, String> serviceMessageId;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, Supplier> externalSupplier;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, MaterialRevision> materialRevision;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, Plant> plant;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, SerialObject> serialObject;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, ServiceOrder> serviceOrder;
    @Generated
    public static volatile CollectionAttribute<ServiceMessage, Material> failureMaterials;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, FaultAnalysis> faultAnalysis;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, RMAType> rMAType;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, RepairErrorCode> repairErrorCode;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, RepairLocation> repairLocation;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, RepairService> repairService;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, RepairState> repairState;
    @Generated
    public static volatile SingularAttribute<ServiceMessage, RepairTask> repairTask;
    @Generated
    public static volatile CollectionAttribute<ServiceMessage, X2Message> x2Messages;
}