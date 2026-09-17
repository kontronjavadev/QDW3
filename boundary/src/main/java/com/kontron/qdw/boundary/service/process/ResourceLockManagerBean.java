package com.kontron.qdw.boundary.service.process;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kontron.util.log.TaskLeafLog;
import com.kontron.util.log.TaskNodeLog;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;

@Singleton
@Lock(LockType.READ) // Zwingend erforderlich, überschreibt das implizite WRITE-Lock des @Singleton!
public class ResourceLockManagerBean {

    private static final Logger logger = LoggerFactory.getLogger(ResourceLockManagerBean.class);

    // Verwaltet für jede Enum-Ressource genau ein AtomicBoolean
    private final EnumMap<ImportResource, AtomicBoolean> locks = new EnumMap<>(ImportResource.class);

    public ResourceLockManagerBean() {
        for (ImportResource resource : ImportResource.values()) {
            locks.put(resource, new AtomicBoolean(false));
        }
    }

    /**
     * Führt einen Task exklusiv für die angegebenen Ressourcen aus.
     */
    public void executeLocked(Set<ImportResource> requiredResources, TaskNodeLog mainTask, Runnable taskExecutor) {
        // 1. Sortieren zwingend erforderlich, um Deadlocks bei Multi-Locks zu vermeiden!
        List<ImportResource> sortedLocks = new ArrayList<>(requiredResources);
        sortedLocks.sort(Enum::compareTo);

        String jobName = mainTask.getTaskName()
                + (mainTask.getTaskDescription() == null
                        ? ""
                        : ": " + mainTask.getTaskDescription());
        List<ImportResource> acquiredLocks = new ArrayList<>();

        try {
            // 2. versuchen, alle angeforderten Locks nacheinander zu holen
            for (ImportResource resource : sortedLocks) {
                if (locks.get(resource).compareAndSet(false, true)) {
                    acquiredLocks.add(resource);
                }
                else {
                    String msg = "Job '" + jobName + "' rejected: Ressource '" + resource + "' is blocked.";
                    TaskLeafLog tskUnmarshall = mainTask.createNewSubTaskLeaf("trying to get execution lock");
                    tskUnmarshall.finishTaskWithError(msg);
                    logger.warn(msg);
                    return; // Early Exit. Das finally räumt die bereits geholten Locks sauber auf.
                }
            }

            // 3. An dieser Stelle haben wir alle angeforderten Locks erfolgreich erhalten
            logger.atInfo().setMessage("Job '{}' startet mit exklusivem Zugriff auf: {}")
                    .addArgument(jobName)
                    .addArgument(() -> acquiredLocks.stream().map(ImportResource::toString).collect(Collectors.joining(", ")))
                    .log();

            taskExecutor.run();
        }
        finally {
            // 4. Locks zwingend wieder freigeben.
            // Best Practice: Freigabe in umgekehrter Reihenfolge zur Akquisition.
            for (int i = acquiredLocks.size() - 1; i >= 0; i--) {
                locks.get(acquiredLocks.get(i)).set(false);
            }
        }
    }

}
