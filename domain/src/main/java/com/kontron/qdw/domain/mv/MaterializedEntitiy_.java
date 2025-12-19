package com.kontron.qdw.domain.mv;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(MaterializedEntitiy.class)
public class MaterializedEntitiy_ {
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, Long> id;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, Long> meSerialObjectId;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, Long> parentSerialObjectId;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> serialNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> parentSerialNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> materialNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> parentMaterialNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> materialType;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> parentMaterialType;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> materialShortText;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> parentMaterialShortText;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> sapNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> parentSapNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> materialHierarchy;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> parentMaterialHierarchy;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, Long> revisionId;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, Long> parentRevisionId;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> revisionNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> parentRevisionNumber;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, LocalDate> assemblyDate;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> assemblyPO;
    @Generated
    public static volatile SingularAttribute<MaterializedEntitiy, String> plant;
}