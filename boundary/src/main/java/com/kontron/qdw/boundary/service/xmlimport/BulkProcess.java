package com.kontron.qdw.boundary.service.xmlimport;

import org.slf4j.Logger;

/**
 * Managt eine Bulk-Verarbeitung
 * 
 * 2026 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
public class BulkProcess {

    public static final int DEFAULT_BULK_SIZE = 2000;

    private final int listSize;
    private final int bulkSize;

    private float cnt = 0;
    private int progressStep = 5;
    private int progress = progressStep;

    private int bulkFromIdx = 0;
    private int bulkToIdx;


    public BulkProcess(int listSize) {
        this(listSize, DEFAULT_BULK_SIZE);
    }

    public BulkProcess(int listSize, int bulkSize) {
        this.listSize = listSize;
        this.bulkSize = bulkSize;
        bulkToIdx = Math.min(listSize, bulkSize);
    }


    void nextCnt() {
        cnt++;
    }

    void nextBulk() {
        bulkFromIdx = bulkToIdx;
        bulkToIdx = Math.min(listSize, bulkFromIdx + bulkSize);
    }

    void logProcess(Logger logger) {
        if (cnt / listSize * 100 > progress) {
            progress = ((int) (cnt / listSize * 100) / progressStep) * progressStep;
            logger.info(progress + "% done");
            progress += progressStep;
        }
    }

    void logProcessBulkLevel(Logger logger) {
        if ((float) bulkFromIdx / listSize * 100 > progress) {
            progress = ((int) ((float) bulkFromIdx / listSize * 100) / progressStep) * progressStep;
            logger.info(progress + "% done");
            progress += progressStep;
        }
    }


    public int getBulkFromIdx() {
        return bulkFromIdx;
    }

    public int getBulkToIdx() {
        return bulkToIdx;
    }

    public int getListSize() {
        return listSize;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public int getProgressStep() {
        return progressStep;
    }

}
