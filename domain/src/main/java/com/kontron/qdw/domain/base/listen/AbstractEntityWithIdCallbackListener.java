package com.kontron.qdw.domain.base.listen;

import jakarta.persistence.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

public class AbstractEntityWithIdCallbackListener {
    /**
     * Method to be executed before persisting a abstract entity with id object
     * @param object the abstract entity with id object
     */
    @PrePersist
    @Generated
    public void doPrePersist(AbstractEntityWithId object) {
        object.setCreationDate(LocalDateTime.now());
    }

    /**
     * Method to be executed before merging a abstract entity with id object
     * @param object the abstract entity with id object
     */
    @PreUpdate
    @Generated
    public void doPreUpdate(AbstractEntityWithId object) {
        object.setLastUpdate(LocalDateTime.now());
    }

}