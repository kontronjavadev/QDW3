package com.kontron.qdw.domain.material;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(MaterialRevision.class)
public class MaterialRevision_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<MaterialRevision, String> revisionNumber;
    @Generated
    public static volatile SingularAttribute<MaterialRevision, String> rev2;
    @Generated
    public static volatile SingularAttribute<MaterialRevision, String> rev6;
    @Generated
    public static volatile SingularAttribute<MaterialRevision, String> alternativeNumber;
    @Generated
    public static volatile SingularAttribute<MaterialRevision, String> comment;
    @Generated
    public static volatile SingularAttribute<MaterialRevision, Material> material;
    @Generated
    public static volatile SingularAttribute<MaterialRevision, Plant> plant;
    @Generated
    public static volatile CollectionAttribute<MaterialRevision, BoMItem> boMItems;
}