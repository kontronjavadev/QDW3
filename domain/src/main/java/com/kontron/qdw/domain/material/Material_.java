package com.kontron.qdw.domain.material;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(Material.class)
public class Material_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<Material, String> materialNumber;
    @Generated
    public static volatile SingularAttribute<Material, String> sapNumber;
    @Generated
    public static volatile SingularAttribute<Material, String> shortText;
    @Generated
    public static volatile SingularAttribute<Material, String> comment;
    @Generated
    public static volatile SingularAttribute<Material, Double> fitValue;
    @Generated
    public static volatile SingularAttribute<Material, String> materialHierarchy;
    @Generated
    public static volatile SingularAttribute<Material, Boolean> searchSubAssemblies;
    @Generated
    public static volatile SingularAttribute<Material, Location> ownerLocation;
    @Generated
    public static volatile SingularAttribute<Material, MaterialClass> materialClass;
    @Generated
    public static volatile SingularAttribute<Material, MaterialType> materialType;
    @Generated
    public static volatile CollectionAttribute<Material, MaterialRevision> revisions;
}