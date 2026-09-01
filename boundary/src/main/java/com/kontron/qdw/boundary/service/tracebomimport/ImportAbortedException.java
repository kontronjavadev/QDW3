package com.kontron.qdw.boundary.service.tracebomimport;

import com.kontron.util.log.FileImportAbortedWithErrorsLog;

public class ImportAbortedException extends Exception {

    private static final long serialVersionUID = 138943754127869290L;

    private final FileImportAbortedWithErrorsLog taskLog;

    ImportAbortedException(FileImportAbortedWithErrorsLog taskLog) {
        this.taskLog = taskLog;
    }

    public FileImportAbortedWithErrorsLog getTaskLog() {
        return taskLog;
    }

}
