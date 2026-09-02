package com.kontron.qdw.boundary.service.tracebomimport;

public record ImportResult(boolean success, String errorMessage) {

    public static ImportResult ok() {
        return new ImportResult(true, null);
    }

    public static ImportResult fail(String message) {
        return new ImportResult(false, message);
    }

}
