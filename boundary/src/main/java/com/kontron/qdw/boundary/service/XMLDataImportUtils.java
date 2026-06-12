package com.kontron.qdw.boundary.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.sourceforge.jbizmo.commons.property.PropertyService;

/**
 * Utils für den Import der XML-Dateien, die der Downloader in verschiedenen Verzeichnissen bereitstellt.
 * 
 * 2025 — © Kontron AG
 * @author Raymund Achner, achner.com
 */
public final class XMLDataImportUtils {

    private static final String PROP_XML_EXCHANGE_FOLDER = "sap_exchange_folder";
    private static final String PROP_XML_ARCHIVE_FOLDER = "sap_archive_folder";

    private static String exchangePath = new PropertyService().getStringProperty(PROP_XML_EXCHANGE_FOLDER);
    private static String archivePath = new PropertyService().getStringProperty(PROP_XML_ARCHIVE_FOLDER);

    private XMLDataImportUtils() {
        // privater ctor für Util-Klassen
    }


    public static void moveFileToArchive(String subPath, String sourceFileName) {
        if (sourceFileName == null) {
            return;
        }

        File sourceFile = new File(exchangePath + File.separator + subPath, sourceFileName);
        File targetFile = new File(archivePath + File.separator + subPath, sourceFileName);

        try (InputStream inStream = new FileInputStream(sourceFile); OutputStream outStream = new FileOutputStream(targetFile)) {
            byte[] buffer = new byte[4048];

            int length;
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

            inStream.close();
            outStream.close();

            // Delete the original file
            sourceFile.delete();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
