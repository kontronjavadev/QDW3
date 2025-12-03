package com.kontron.qdw.domain.mv;

import com.kontron.qdw.domain.serial.*;
import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.material.*;

@StaticMetamodel(MaterializedArrival.class)
public class MaterializedArrival_ extends MaterializedEntitiy_ {
    @Generated
    public static volatile SingularAttribute<MaterializedArrival, LocalDate> arrivalDate;
    @Generated
    public static volatile SingularAttribute<MaterializedArrival, String> supplierCode;
    @Generated
    public static volatile SingularAttribute<MaterializedArrival, String> supplierName;
    @Generated
    public static volatile SingularAttribute<MaterializedArrival, String> movementType;
    @Generated
    public static volatile SingularAttribute<MaterializedArrival, String> orderNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedArrival, String> countryCode;
    @Generated
    public static volatile SingularAttribute<MaterializedArrival, String> countryName;
    @Generated
    public static volatile SingularAttribute<MaterializedArrival, Material> material;
    @Generated
    public static volatile SingularAttribute<MaterializedArrival, SerialObject> serialObject;
}