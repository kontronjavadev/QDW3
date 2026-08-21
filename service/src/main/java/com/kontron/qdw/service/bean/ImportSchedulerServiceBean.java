package com.kontron.qdw.service.bean;

import java.util.concurrent.TimeUnit;

import com.kontron.qdw.boundary.service.repairimport.RepairImportServiceBean;
import com.kontron.qdw.boundary.service.sapimport.SapDataImportServiceBean;

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
public class ImportSchedulerServiceBean {

    @EJB
    private SapDataImportServiceBean sapImportService;
    @EJB
    private RepairImportServiceBean repairImportService;

    /**
     * Scheduler für automatischen Import.
     * Täglich um 1:30 Uhr
     */
    @Schedule(dayOfWeek = "*", hour = "1", minute = "30", persistent = false)
    @AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
    public void runScheduledSapImport() {
        sapImportService.runImport();
    }

    /**
     * Scheduler für automatischen Import.
     * Täglich um 10:30 Uhr
     */
    @Schedule(dayOfWeek = "*", hour = "10", minute = "30", persistent = false)
    @AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
    public void runScheduledRepairImport() {
        repairImportService.runImport();
    }

}
