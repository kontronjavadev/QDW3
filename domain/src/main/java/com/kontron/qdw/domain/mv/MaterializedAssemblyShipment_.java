package com.kontron.qdw.domain.mv;

import com.kontron.qdw.domain.serial.*;
import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;

@StaticMetamodel(MaterializedAssemblyShipment.class)
public class MaterializedAssemblyShipment_ extends MaterializedEntitiy_ {
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, LocalDate> arrivalDate;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, Long> arrivalId;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> arrivalMovementType;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> countryCode;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> countryName;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> customerCode;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> customerName;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> customerOrderNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> ownerLocation;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> purchaseOrderNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, LocalDate> shipmentDate;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, Long> shipmentId;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> shipmentMovementType;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> supplierCode;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, String> supplierName;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, Material> material;
    @Generated
    public static volatile SingularAttribute<MaterializedAssemblyShipment, SerialObject> serialObject;
}