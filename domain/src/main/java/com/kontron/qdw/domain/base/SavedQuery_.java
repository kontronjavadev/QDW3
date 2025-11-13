package com.kontron.qdw.domain.base;

import jakarta.persistence.metamodel.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;

@StaticMetamodel(SavedQuery.class)
public class SavedQuery_ {
    @Generated
    public static volatile SingularAttribute<SavedQuery, Long> id;
    @Generated
    public static volatile SingularAttribute<SavedQuery, String> title;
    @Generated
    public static volatile SingularAttribute<SavedQuery, String> viewName;
    @Generated
    public static volatile SingularAttribute<SavedQuery, byte[]> dataObj;
    @Generated
    public static volatile SingularAttribute<SavedQuery, User> user;
}