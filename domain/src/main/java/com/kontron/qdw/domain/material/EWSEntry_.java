package com.kontron.qdw.domain.material;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

@StaticMetamodel(EWSEntry.class)
public class EWSEntry_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<EWSEntry, Double> threshold;
    @Generated
    public static volatile SingularAttribute<EWSEntry, Boolean> boardOrSystem;
    @Generated
    public static volatile SingularAttribute<EWSEntry, String> filterCriterion;
    @Generated
    public static volatile SingularAttribute<EWSEntry, EWSType> type;
    @Generated
    public static volatile SingularAttribute<EWSEntry, Material> material;
    @Generated
    public static volatile CollectionAttribute<EWSEntry, User> receivers;
}