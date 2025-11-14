package com.kontron.qdw.domain.base.listen;

import jakarta.persistence.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

public class AbstractFunctionalActiveEntityCallbackListener {
    /**
     * Method to be executed before persisting a abstract functional active entity object
     * @param object the abstract functional active entity object
     */
    @PrePersist
    @Generated
    public void doPrePersist(AbstractFunctionalActiveEntity object) {
        object.setCreationDate(LocalDateTime.now());
    }

    /**
     * Method to be executed before merging a abstract functional active entity object
     * @param object the abstract functional active entity object
     */
    @PreUpdate
    @Generated
    public void doPreUpdate(AbstractFunctionalActiveEntity object) {
        object.setLastUpdate(LocalDateTime.now());
    }

}