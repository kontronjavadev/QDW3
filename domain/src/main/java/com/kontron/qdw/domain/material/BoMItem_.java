package com.kontron.qdw.domain.material;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(BoMItem.class)
public class BoMItem_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<BoMItem, Double> quantity;
    @Generated
    public static volatile SingularAttribute<BoMItem, String> label;
    @Generated
    public static volatile SingularAttribute<BoMItem, String> position;
    @Generated
    public static volatile SingularAttribute<BoMItem, Material> material;
    @Generated
    public static volatile SingularAttribute<BoMItem, MaterialRevision> materialRevision;
}