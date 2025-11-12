package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(AbstractEntityWithId.class)
public class AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<AbstractEntityWithId, Long> id;
    @Generated
    public static volatile SingularAttribute<AbstractEntityWithId, Integer> version;
    @Generated
    public static volatile SingularAttribute<AbstractEntityWithId, LocalDateTime> creationDate;
    @Generated
    public static volatile SingularAttribute<AbstractEntityWithId, LocalDateTime> lastUpdate;
}