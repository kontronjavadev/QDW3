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
     * Jede Viertelstunde zu Minute 15, 30, 45, 00
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
     * Jede Viertelstunde zu Minute 12, 27, 42, 57, also jeweils drei Minuten vor der Produktionsumgebung.
     *
     * Hintergrund: Nach Verarbeitung der Importdateien löscht die Produktion die Daten auf dem Server.
     * Was in den verbleibenden 3 Minuten auf dem FTP-Server landet, haben wir nicht in der Hand, aber
     * zumindest sollte die Testumgebung mit dieser Maßnahme mit den meisten Daten versorgt werden.
     */
    @Schedule(dayOfWeek = "*", hour = "*", minute = "12/15", second = "0", persistent = false)
    @AccessTimeout(value = 5, unit = TimeUnit.MINUTES)
    public void runScheduledTraceBoMImportTest() {
        if (!Constants.IS_PROD_ENVIRONMENT) {
            traceBoMImportService.runImport();
        }
    }

}
