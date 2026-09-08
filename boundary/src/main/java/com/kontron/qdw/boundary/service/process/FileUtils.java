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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jakarta.activation.MimetypesFileTypeMap;

public class FileUtils {

    public static final FileFilter XML_FILE_FILTER = f -> f.isFile() && FileUtils.isXmlFile(f);
    public static final FileFilter ZIP_FILE_FILTER = f -> f.isFile() && FileUtils.isZipFile(f);


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
     * Entpackt die übergebene zip-Datei in das angegebene Verzeichnis (lediglich Top-Level-Einträge der zip-Datei, keine hierarchische Verarbeitung)
     * 
     * @param zipFile zip-Datei
     * @param targetDir Zielverzeichnis
     * 
     * @throws FileNotFoundException wenn zip-Datei nicht gefunde wird oder nicht geöffnet werden kann
     *         oder Zieldatei nicht zum Schreiben geöffnet werden kann
     * @throws SecurityException wenn es einen security manager gibt und desen checkRead-Methode den Lesezugriff auf die zip-Datei
     *         oder Schreibzugriff auf die Zieldatei verbietet
     */
    public static List<File> unzipFile(final File zipFile, final String targetDir) throws IOException, FileNotFoundException {
        return unzipFile(zipFile, targetDir, Optional.empty());
    }

    /**
     * Entpackt die übergebene zip-Datei in das angegebene Verzeichnis (lediglich Top-Level-Einträge der zip-Datei, keine hierarchische Verarbeitung)
     * 
     * @param zipFile zip-Datei
     * @param targetDir Zielverzeichnis
     * @param fileFilter optionaler Dateifilter, der beschränkt, welche Dateien daraus entpackt werden sollen
     * 
     * @throws FileNotFoundException wenn zip-Datei nicht gefunde wird oder nicht geöffnet werden kann
     *         oder Zieldatei nicht zum Schreiben geöffnet werden kann
     * @throws SecurityException wenn es einen security manager gibt und desen checkRead-Methode den Lesezugriff auf die zip-Datei
     *         oder Schreibzugriff auf die Zieldatei verbietet
     */
    public static List<File> unzipFile(final File zipFile, final String targetDir, Optional<FileFilter> fileFilter)
            throws IOException, FileNotFoundException, SecurityException {
        final int BUFFER_SIZE = 8192;

        // If file is compressed than unzip the contained files
        if (!isZipFile(zipFile)) {
            return Collections.emptyList();
        }

        List<File> extractedFiles = new ArrayList<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile.getAbsolutePath())))) {
            ZipEntry zipEntry = null;
            int count;
            byte data[] = new byte[BUFFER_SIZE];

            // Iterate through zipInputStream to get all contained files
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                File targetFile = createFullPath(zipEntry.getName(), targetDir);
                if (fileFilter.isPresent() && !fileFilter.get().accept(targetFile)) {
                    // Datei wird nicht akzeptiert, wird also nicht entpackt
                    continue;
                }
                extractedFiles.add(targetFile);
                if (targetFile.exists()) {
                    // wurde bereits entpackt
                    continue;
                }

                try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(targetFile), BUFFER_SIZE)) {
                    // Read until end of stream and write content to file
                    while ((count = zipInputStream.read(data, 0, BUFFER_SIZE)) != -1) {
                        out.write(data, 0, count);
                    }
                    out.flush();
                }
            }
        }
        return extractedFiles;
    }

    /**
     * Create full path from path and filename
     * @param fileNameInZipFile
     * @param destDir
     * @return full path created from path and filename
     */
    private static File createFullPath(final String fileNameInZipFile, final String destDir) {
        return new File(destDir, fileNameInZipFile.toLowerCase());
    }

}
