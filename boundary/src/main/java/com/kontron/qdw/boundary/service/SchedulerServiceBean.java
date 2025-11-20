package com.kontron.qdw.boundary.service;

import java.lang.invoke.MethodHandles;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.qdw.boundary.util.Constants;
import com.kontron.util.bool.BooleanUtil;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;

@Singleton
public class SchedulerServiceBean {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String EXECUTE_IMPORT_SYSPROP = "com.kontron.qdw.executeImport";

    private static final boolean IS_EXECUTE_IMPORT = BooleanUtil.getBooleanSysProp(EXECUTE_IMPORT_SYSPROP, Boolean.FALSE);

    /**
     * Einstellungen beim Start ausgeben
     */
    @PostConstruct
    private void init() {
        logger.info("=> running on {}", Constants.ENVIRONMENT);

        logger.info("=> I {} execute import of SAP files", (IS_EXECUTE_IMPORT ? "will" : "won't"));
    }


    public boolean isExecuteImport() {
        return IS_EXECUTE_IMPORT;
    }

}
