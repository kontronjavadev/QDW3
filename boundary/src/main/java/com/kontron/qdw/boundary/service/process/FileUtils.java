package com.kontron.qdw.boundary.service.process;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jakarta.activation.MimetypesFileTypeMap;

public class FileUtils {

    public static final FileFilter XML_FILE_FILTER = f -> f.isFile() && FileUtils.isXmlFile(f);


    /**
     * Determine whether the file is a compressed file by using the mimetype methods from package javax.activation
     * @param fileName
     * @return true if the file is a ZIP file
     */
    public static boolean isZipFile(final File file) {
        String tmpMimeType = null;

        MimetypesFileTypeMap mimeTypes = new MimetypesFileTypeMap();
        Set<String> validZipMimeTypes = new TreeSet<>();

        validZipMimeTypes.add("application/x-zip-compressed");
        validZipMimeTypes.add("application/zip");
        validZipMimeTypes.add("multipart/x-zip");

        tmpMimeType = mimeTypes.getContentType(file);

        return validZipMimeTypes.contains(tmpMimeType.toString()) || file.getName().toLowerCase().endsWith(".zip");
    }

    /**
     * Determine whether the file is a XML file by using the mimetype methods from package javax.activation
     * @param fileName
     * @return true if the file is an XML file
     */
    public static boolean isXmlFile(final File file) {
        String tmpMimeType = null;

        MimetypesFileTypeMap mimeTypes = new MimetypesFileTypeMap();
        Set<String> validXmlMimeTypes = new TreeSet<>();

        validXmlMimeTypes.add("application/xml");
        validXmlMimeTypes.add("text/xml");

        tmpMimeType = mimeTypes.getContentType(file);

        return validXmlMimeTypes.contains(tmpMimeType.toString()) || file.getName().toLowerCase().endsWith(".xml");
    }

    /**
     * @param file file to check
     * @return true if file is not locked by another system
     */
    public static boolean isBusy(File file) {
        Path filePath = file.toPath();
        if (isFileReady(filePath)) {
            return false; // Datei ist frei (==nicht busy)
        }

        try {
            TimeUnit.SECONDS.sleep(15);
        }
        catch (InterruptedException e) {
            // Wichtig im EE/WildFly-Kontext: Interrupt-Flag wiederherstellen
            Thread.currentThread().interrupt();
            return true;
        }

        // Nach 15 Sekunden erneuter Check. Wenn !isFileReady, ist sie immer noch busy.
        return !isFileReady(filePath);
    }

    private static boolean isFileReady(Path file) {
        try (FileChannel channel = FileChannel.open(file, StandardOpenOption.WRITE, StandardOpenOption.APPEND);
                FileLock lock = channel.tryLock()) {

            if (lock == null) {
                return false;
            }

            // feststellen, ob die Datei gerade noch übertragen wird und sich die Größe ändert
            long sizeBefore = Files.size(file);
            TimeUnit.MILLISECONDS.sleep(150);
            long sizeAfter = Files.size(file);

            return sizeBefore == sizeAfter;
        }
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        }
        catch (Exception e) {
            // Greift bei fehlenden Rechten, gelöschter Datei oder OverlappingFileLockException
            return false;
        }
    }

    /**
     * Create full path from path and filename
     * @param fileName
     * @param destDir
     * @return full path created from path and filename
     */
    public static String createFullPath(final String fileName, final String destDir) {
        String fullPath = null;

        if (destDir.charAt(destDir.length() - 1) != File.separatorChar) {
            fullPath = destDir + File.separatorChar + fileName.toLowerCase();
        }
        else {
            fullPath = destDir + fileName.toLowerCase();
        }

        return fullPath;
    }

    /**
     * Extract given ZIP file into given directory (only files in top level hierarchy of the zip file)
     * @param fileName
     * @param destDir
     * @throws Exception
     */
    public static void unzipFile(final File file, final String destDir) throws IOException, FileNotFoundException {
        // ZipInputStream zipInputStream = null;
        // BufferedOutputStream out = null;
        final int BUFFER_SIZE = 8192;

        // If file is compressed than unzip the contained files
        if (!isZipFile(file)) {
            return;
        }

        try (ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file.getAbsolutePath())))) {
            ZipEntry zipEntry = null;
            int count;
            byte data[] = new byte[BUFFER_SIZE];

            // Iterate through zipInputStream to get all contained files
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String tmpPath = createFullPath(zipEntry.getName(), destDir);

                try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(tmpPath), BUFFER_SIZE)) {
                    // Read until end of stream and write content to file
                    while ((count = zipInputStream.read(data, 0, BUFFER_SIZE)) != -1) {
                        out.write(data, 0, count);
                    }

                    out.flush();
                }
            }
        }
    }
}
