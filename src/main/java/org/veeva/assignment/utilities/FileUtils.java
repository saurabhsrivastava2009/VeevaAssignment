package org.veeva.assignment.utilities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Class to handle all file utilities
 *
 * @author Saurabh Srivastava
 * @since 10-04-2024
 */
public class FileUtils {

    /**
     * Method to create a directory/folder
     * @param folderPath -the path at which you want to create the foler
     */
    public static void createFolder(String folderPath) {
        File folder = new File(folderPath);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    /**
     * Method to write to file
     * @param filePath - the file path
     * @param content - the contents of the file
     * @throws IOException - handling IO related exceptions
     */
    public static void writeToFile(String filePath, String content) throws IOException {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(content);
        }
    }
}
