package com.kontron.qdw.domain.material;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(SerialNumberSetItem.class)
public class SerialNumberSetItem_ {
    @Generated
    public static volatile SingularAttribute<SerialNumberSetItem, Long> id;
    @Generated
    public static volatile SingularAttribute<SerialNumberSetItem, String> serialNumber;
    @Generated
    public static volatile SingularAttribute<SerialNumberSetItem, SerialNumberSet> serialNumberSet;
}