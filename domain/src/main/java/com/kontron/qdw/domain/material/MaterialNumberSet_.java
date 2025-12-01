package com.kontron.qdw.domain.material;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(MaterialNumberSet.class)
public class MaterialNumberSet_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<MaterialNumberSet, String> label;
    @Generated
    public static volatile CollectionAttribute<MaterialNumberSet, Material> materials;
}