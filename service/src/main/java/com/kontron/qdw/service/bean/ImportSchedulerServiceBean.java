package com.kontron.qdw.service.bean;

import java.util.concurrent.TimeUnit;

import com.kontron.qdw.boundary.service.repairimport.RepairImportServiceBean;
import com.kontron.qdw.boundary.service.sapimport.SapDataImportServiceBean;
import com.kontron.qdw.boundary.service.tracebomimport.TraceBoMImportServiceBean;
import com.kontron.qdw.boundary.util.Constants;

import jakarta.ejb.AccessTimeout;
import jakarta.ejb.EJB;
import jakarta.ejb.Schedule;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;

/**
 * Scheduler, der zeitgesteuert {@link SapDataImportServiceBean}, {@link RepairImportServiceBean} und {@link TraceBoMImportServiceBean} aufruft.
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
    @EJB
    private TraceBoMImportServiceBean traceBoMImportService;

    /**
     * Scheduler für automatischen Import der Laufzeitdaten.
     * Täglich um 1:30 Uhr
     */
    @Schedule(dayOfWeek = "*", hour = "1", minute = "30", persistent = false)
    @AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
    public void runScheduledSapImport() {
        sapImportService.runImport();
    }

    /**
     * Scheduler für automatischen Import der Repairdaten.
     * Täglich um 10:30 Uhr
     */
    @Schedule(dayOfWeek = "*", hour = "10", minute = "30", persistent = false)
    @AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
    public void runScheduledRepairImport() {
        repairImportService.runImport();
    }



    /**
     * Scheduler für automatischen Import der Trace-BoM-Daten für die Prod-Umgebung.
     * Jede Viertelstunde, beginnend mit Minute 0
     */
    @Schedule(dayOfWeek = "*", hour = "*", minute = "*/15", second = "0", persistent = false)
    @AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
    public void runScheduledTraceBoMImportProd() {
        if (Constants.IS_PROD_ENVIRONMENT) {
            traceBoMImportService.runImport();
        }
    }

    /**
     * Scheduler für automatischen Import der Trace-BoM-Daten für die Test-Umgebung.
     * Einmal stündlich bei Minute 0
     */
    @Schedule(dayOfWeek = "*", hour = "*", minute = "0", second = "0", persistent = false)
    @AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
    public void runScheduledTraceBoMImportTest() {
        if (!Constants.IS_PROD_ENVIRONMENT) {
            traceBoMImportService.runImport();
        }
    }

}
