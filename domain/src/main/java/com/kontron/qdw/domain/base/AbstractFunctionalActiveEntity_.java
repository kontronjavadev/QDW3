package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(AbstractFunctionalActiveEntity.class)
public class AbstractFunctionalActiveEntity_ {
    @Generated
    public static volatile SingularAttribute<AbstractFunctionalActiveEntity, String> code;
    @Generated
    public static volatile SingularAttribute<AbstractFunctionalActiveEntity, String> shortText;
    @Generated
    public static volatile SingularAttribute<AbstractFunctionalActiveEntity, String> comment;
    @Generated
    public static volatile SingularAttribute<AbstractFunctionalActiveEntity, Boolean> active;
    @Generated
    public static volatile SingularAttribute<AbstractFunctionalActiveEntity, LocalDateTime> creationDate;
    @Generated
    public static volatile SingularAttribute<AbstractFunctionalActiveEntity, LocalDateTime> lastUpdate;
    @Generated
    public static volatile SingularAttribute<AbstractFunctionalActiveEntity, Integer> version;
}