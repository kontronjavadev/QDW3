package com.kontron.qdw.domain.base.listen;

import jakarta.persistence.*;
import java.time.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import com.kontron.qdw.domain.base.*;

public class AbstractTransactionalEntityCallbackListener {
    /**
     * Method to be executed before persisting a abstract transactional entity object
     * @param object the abstract transactional entity object
     */
    @PrePersist
    @Generated
    public void doPrePersist(AbstractTransactionalEntity object) {
        object.setCreationDate(LocalDateTime.now());
    }

    /**
     * Method to be executed before merging a abstract transactional entity object
     * @param object the abstract transactional entity object
     */
    @PreUpdate
    @Generated
    public void doPreUpdate(AbstractTransactionalEntity object) {
        object.setLastUpdate(LocalDateTime.now());
    }

}