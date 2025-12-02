package com.kontron.qdw.domain.mv;

import com.kontron.qdw.domain.serial.*;
import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;

@StaticMetamodel(MaterializedArrivalShipment.class)
public class MaterializedArrivalShipment_ extends MaterializedEntitiy_ {
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, LocalDate> arrivalDate;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, Long> arrivalId;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> arrivalMovementType;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> countryCode;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> countryName;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> customerCode;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> customerName;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> customerOrderNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> ownerLocation;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> purchaseOrderNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, LocalDate> shipmentDate;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, Long> shipmentId;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> shipmentMovementType;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> supplierCode;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, String> supplierName;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, Material> material;
    @Generated
    public static volatile SingularAttribute<MaterializedArrivalShipment, SerialObject> serialObject;
}