package com.kontron.qdw.domain.base.listen;

import jakarta.persistence.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

public class AbstractFuntionalEntityCallbackListener {
    /**
     * Method to be executed before persisting a abstract funtional entity object
     * @param object the abstract funtional entity object
     */
    @PrePersist
    @Generated
    public void doPrePersist(AbstractFuntionalEntity object) {
        object.setCreationDate(LocalDateTime.now());
    }

    /**
     * Method to be executed before merging a abstract funtional entity object
     * @param object the abstract funtional entity object
     */
    @PreUpdate
    @Generated
    public void doPreUpdate(AbstractFuntionalEntity object) {
        object.setLastUpdate(LocalDateTime.now());
    }

}