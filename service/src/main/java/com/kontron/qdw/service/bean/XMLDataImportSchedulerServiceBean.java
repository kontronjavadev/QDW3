package com.kontron.qdw.service.bean;

import java.util.concurrent.TimeUnit;

import com.kontron.qdw.boundary.service.SapDataImportServiceBean;

import jakarta.ejb.AccessTimeout;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 * Scheduler, der zeitgesteuert {@link SapDataImportServiceBean} aufruft.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
// Abhängigkeit zu Boundary-Suprojekt in pom erforderlich
@Singleton
@Startup
public class XMLDataImportSchedulerServiceBean {

    @EJB
    private SapDataImportServiceBean importService;

    /**
     * Scheduler für automatischen Import.
     * Täglich um 1:30 Uhr
     */
    @Schedule(dayOfWeek = "*", hour = "1", minute = "30", persistent = false)
    @AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
    public void runScheduledImport() {
        importService.runImport();
    }

}
