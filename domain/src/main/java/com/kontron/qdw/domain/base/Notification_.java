package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(Notification.class)
public class Notification_ extends AbstractEntityWithId_ {
    @Generated
    public static volatile SingularAttribute<Notification, String> header;
    @Generated
    public static volatile SingularAttribute<Notification, String> notification;
    @Generated
    public static volatile SingularAttribute<Notification, LocalDateTime> notificationStart;
    @Generated
    public static volatile SingularAttribute<Notification, LocalDateTime> notificationEnd;
    @Generated
    public static volatile SingularAttribute<Notification, User> initiator;
    @Generated
    public static volatile CollectionAttribute<Notification, User> gelesen;
}