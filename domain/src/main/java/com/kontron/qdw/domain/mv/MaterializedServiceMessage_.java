package com.kontron.qdw.domain.mv;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(MaterializedServiceMessage.class)
public class MaterializedServiceMessage_ extends MaterializedEntitiy_ {
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> serviceOrder;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, ServiceOrderType> serviceOrderType;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> rmaType;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> location;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> serviceName;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> taskName;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> stateName;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, LocalDate> internalArrivalDate;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, LocalDate> internalShipmentDate;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, LocalDate> basicStartDate;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, LocalDate> basicEndDate;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> designator;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> defectComponent;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> analysisText;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> internalReport;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> externalReport;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> customerReport;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, Boolean> epidemicFailure;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> errorId;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> origin;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, Boolean> customerFailure;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> customerCode;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> customerName;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> customerGroup;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> countryCode;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> countryName;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> supplierCode;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> supplierName;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, LocalDate> supplierArrivalDate;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, LocalDate> customerShipmentDate;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> faultAnalysisCode;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> errorCodeName;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> externalSupplierCode;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> externalSupplierName;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> deliveryNoteNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> errorCodeGroup;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> symptomShortText;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> repairTaskShortText;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> repairDescription;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> ownerLocation;
    @Generated
    public static volatile SingularAttribute<MaterializedServiceMessage, String> errorShortText;
}