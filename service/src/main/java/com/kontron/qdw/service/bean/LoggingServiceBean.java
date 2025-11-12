package com.kontron.qdw.service.bean;

import net.sourceforge.jbizmo.commons.server.logging.*;
import net.sourceforge.jbizmo.commons.annotation.Generated;
import jakarta.ejb.*;

@Stateless
@Local(LoggingService.class)
public class LoggingServiceBean implements LoggingService {
    /* (non-Javadoc)
     * @see net.sourceforge.jbizmo.commons.server.logging.LoggingService#debug(net.sourceforge.jbizmo.commons.server.logging.LoggingDTO)
     */
    @Override
    @Generated
    public void debug(LoggingDTO logEntry) {
        // No implementation required!
    }

    /* (non-Javadoc)
     * @see net.sourceforge.jbizmo.commons.server.logging.LoggingService#info(net.sourceforge.jbizmo.commons.server.logging.LoggingDTO)
     */
    @Override
    @Generated
    public void info(LoggingDTO logEntry) {
        // No implementation required!
    }

    /* (non-Javadoc)
     * @see net.sourceforge.jbizmo.commons.server.logging.LoggingService#warn(net.sourceforge.jbizmo.commons.server.logging.LoggingDTO)
     */
    @Override
    @Generated
    public void warn(LoggingDTO logEntry) {
        // No implementation required!
    }

    /* (non-Javadoc)
     * @see net.sourceforge.jbizmo.commons.server.logging.LoggingService#error(net.sourceforge.jbizmo.commons.server.logging.LoggingDTO)
     */
    @Override
    @Generated
    public void error(LoggingDTO logEntry) {
        // No implementation required!
    }

}