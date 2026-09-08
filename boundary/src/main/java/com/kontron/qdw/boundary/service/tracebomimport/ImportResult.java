package com.kontron.qdw.boundary.service.tracebomimport;

public record ImportResult(boolean success, int numberEntries, String errorMessage) {

    public static ImportResult ok(int numberEntries) {
        return new ImportResult(true, numberEntries, null);
    }

    public static ImportResult fail(String message) {
        return new ImportResult(false, 0, message);
    }

    @Override
    public String toString() {
        if (success) {
            return "ImportResult: successfull with " + numberEntries + " entries";
        }
        return "ImportResult: failed because: " + errorMessage;
    }

}
