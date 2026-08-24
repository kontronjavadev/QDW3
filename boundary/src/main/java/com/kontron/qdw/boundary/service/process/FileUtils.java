package com.kontron.qdw.boundary.service.process;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import jakarta.activation.MimetypesFileTypeMap;

public class FileUtils {

    /**
     * Determine whether the file is a compressed file by using the mimetype methods from package javax.activation
     * @param fileName
     * @return true if the file is a ZIP file
     * @throws IOException
     */
    public static boolean isZipFile(final File file) throws IOException {
        String tmpMimeType = null;

        MimetypesFileTypeMap mimeTypes = new MimetypesFileTypeMap();
        Vector<String> validZipMimeTypes = new Vector<>();

        validZipMimeTypes.addElement("application/x-zip-compressed");
        validZipMimeTypes.addElement("application/zip");
        validZipMimeTypes.addElement("multipart/x-zip");

        tmpMimeType = mimeTypes.getContentType(file);

        if (validZipMimeTypes.contains(tmpMimeType.toString()) || file.getName().toLowerCase().endsWith(".zip")) {
            return true;
        }

        return false;
    }

    /**
     * Determine whether the file is a XML file by using the mimetype methods from package javax.activation
     * @param fileName
     * @return true if the file is an XML file
     * @throws IOException
     */
    public static boolean isXmlFile(final File file) throws IOException {
        String tmpMimeType = null;

        MimetypesFileTypeMap mimeTypes = new MimetypesFileTypeMap();
        Vector<String> validXmlMimeTypes = new Vector<>();

        validXmlMimeTypes.addElement("application/xml");
        validXmlMimeTypes.addElement("text/xml");

        tmpMimeType = mimeTypes.getContentType(file);

        if (validXmlMimeTypes.contains(tmpMimeType.toString()) || file.getName().toLowerCase().endsWith(".xml")) {
            return true;
        }

        return false;
    }

    /**
     * @param file
     * @return true if file is not locked by another system
     */
    public static boolean isBusy(final File file) {

        FileLock lock = null;

        try (RandomAccessFile raf = new RandomAccessFile(file, "rw"); FileChannel channel = raf.getChannel()) {

            // Get an exclusive lock on the file
            try {
                lock = channel.tryLock();
            }
            catch (OverlappingFileLockException e) {
                return true;
            }
            finally {
                if (lock != null) {
                    lock.release();
                    lock.close();
                }
            }
        }
        catch (Throwable e) {
            return true;
        }

        return false;
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
    public static void unzipFile(final File file, final String destDir) throws Exception {
        ZipInputStream zipInputStream = null;
        BufferedOutputStream out = null;
        final int BUFFER_SIZE = 8192;

        // If file is compressed than unzip the contained files
        if (!isZipFile(file)) {
            return;
        }

        try {
            zipInputStream = new ZipInputStream(new BufferedInputStream(new FileInputStream(file.getAbsolutePath())));
            ZipEntry zipEntry = null;
            int count;
            byte data[] = new byte[BUFFER_SIZE];

            // Iterate through zipInputStream to get all contained files
            while ((zipEntry = zipInputStream.getNextEntry()) != null) {
                String tmpPath = createFullPath(zipEntry.getName(), destDir);

                try {
                    out = new BufferedOutputStream(new FileOutputStream(tmpPath), BUFFER_SIZE);

                    // Read until end of stream and write content to file
                    while ((count = zipInputStream.read(data, 0, BUFFER_SIZE)) != -1) {
                        out.write(data, 0, count);
                    }

                    out.flush();
                }
                finally {
                    if (out != null) {
                        out.close();
                    }
                }
            }
        }
        finally {
            if (zipInputStream != null) {
                zipInputStream.close();
            }
        }
    }
}
