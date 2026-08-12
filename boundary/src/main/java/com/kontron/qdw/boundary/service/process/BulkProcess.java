package com.kontron.qdw.boundary.service.process;

import java.util.Date;

import org.slf4j.Logger;

import com.kontron.util.datetime.DateUtil;
import com.kontron.util.datetime.TimeConstants;
import com.kontron.util.datetime.TimeUtil;

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
    private final long start;

    private int cnt = 0;
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
        start = System.currentTimeMillis();
    }


    public void nextCnt() {
        cnt++;
    }

    public boolean hasNext() {
        return bulkToIdx - bulkFromIdx > 0;
    }

    public void nextBulk() {
        bulkFromIdx = bulkToIdx;
        bulkToIdx = Math.min(listSize, bulkFromIdx + bulkSize);
    }


    /**
     * Logging, wenn je Eintrag informiert wird.
     * 
     * @see #nextCnt()
     */
    public void logProcess(Logger logger) {
        logProcess(logger, cnt);
    }

    /**
     * Logging, wenn je bulk informiert wird.
     * 
     * @see #nextBulk()
     */
    public void logProcessBulkLevel(Logger logger) {
        logProcess(logger, bulkFromIdx);
    }

    private void logProcess(Logger logger, int calcBase) {
        if (calcBase == 0) {
            return;
        }

        float realFloatProgress = (float) calcBase / listSize * 100;
        if (realFloatProgress > progress) {
            progress = ((int) realFloatProgress / progressStep) * progressStep;

            long duration = System.currentTimeMillis() - start; // verstrichene Zeit (ms)
            long expectedDuration = duration * listSize / calcBase; // erwartete Dauer (ms) für alle Einträge
            Date expectedEnd = new Date(start + expectedDuration);
            double performance = calcBase * (float) TimeConstants.MILLISECONDS_PER_MINUTE / duration; // Einträge pro Minute
            if (logger.isInfoEnabled()) {
                logger.info("{}% done, avg {} per minute; expected duration: {}; expected end: {}",
                        progress, String.format("%.1f", performance),
                        TimeUtil.toBestPracticeStringShort(expectedDuration),
                        DateUtil.dateToString(expectedEnd, DateUtil.FORMAT_PATTERN_GERMAN_DATE_TIME));
            }

            progress += progressStep;
        }
    }



    public int getBulkFromIdx() {
        return bulkFromIdx;
    }

    public int getBulkToIdx() {
        return bulkToIdx;
    }

    public int getCnt() {
        return cnt;
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
