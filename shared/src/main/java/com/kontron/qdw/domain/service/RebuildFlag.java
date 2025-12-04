package com.kontron.qdw.domain.service;

import java.util.Arrays;

public enum RebuildFlag {

    PROCESSED(0), //
    REBUILD_FOR_NEW(1), //
    REBUILD_FOR_UPDATED(2);

    private final int dbValue;

    private RebuildFlag(int dbValue) {
        this.dbValue = dbValue;
    }

    public int getDbValue() {
        return dbValue;
    }

    public static RebuildFlag byDbValue(int dbValue) {
        return Arrays.stream(values())
                .filter(rf -> rf.dbValue == dbValue)
                .findFirst()
                .orElse(null);
    }

}
