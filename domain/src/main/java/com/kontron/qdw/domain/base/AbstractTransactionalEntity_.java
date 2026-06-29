package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(AbstractTransactionalEntity.class)
public class AbstractTransactionalEntity_ {
    @Generated
    public static volatile SingularAttribute<AbstractTransactionalEntity, Long> id;
    @Generated
    public static volatile SingularAttribute<AbstractTransactionalEntity, Integer> version;
    @Generated
    public static volatile SingularAttribute<AbstractTransactionalEntity, LocalDateTime> creationDate;
    @Generated
    public static volatile SingularAttribute<AbstractTransactionalEntity, LocalDateTime> lastUpdate;
}