package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(AbstractFuntionalEntity.class)
public class AbstractFuntionalEntity_ {
    @Generated
    public static volatile SingularAttribute<AbstractFuntionalEntity, String> code;
    @Generated
    public static volatile SingularAttribute<AbstractFuntionalEntity, String> shortText;
    @Generated
    public static volatile SingularAttribute<AbstractFuntionalEntity, String> comment;
    @Generated
    public static volatile SingularAttribute<AbstractFuntionalEntity, LocalDateTime> creationDate;
    @Generated
    public static volatile SingularAttribute<AbstractFuntionalEntity, LocalDateTime> lastUpdate;
    @Generated
    public static volatile SingularAttribute<AbstractFuntionalEntity, Integer> version;
}