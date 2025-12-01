package com.kontron.qdw.domain.material;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(ProductLine.class)
public class ProductLine_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<ProductLine, String> label;
    @Generated
    public static volatile CollectionAttribute<ProductLine, Material> materials;
}