package gov.iti.jets.Controllers.services;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileSystemUtil {

    public static final String ATTACHMENT_DIRECTORY = System.getProperty("user.home") + "/telegraph/attachment";
    public static String storeByteArrayAsFile(byte[] bytes, String fileName) {
        // Ensure the fileName includes the extension
        if (bytes == null || fileName == null || !fileName.contains(".")) {
            CustomDialogs.showErrorDialog("Error: bytes array or fileName is null, or fileName does not include an extension.");
            System.out.println("bytes length : " + bytes.length + " fileName : " + fileName + " fileName contains . : " + fileName.contains("."));
            return null;
        }
        if (!fileName.contains(".")) {
            System.out.println("Error: fileName does not include an extension.");
            return null;
        }
        Path directoryPath = Paths.get(ATTACHMENT_DIRECTORY);
        // Create the directory if it doesn't exist
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                System.out.println("Error Creating Directory: " + e.getMessage());
            }
        }
        // Create the file path
        Path filePath = directoryPath.resolve(fileName);
        // Check if the file already exists
        new Thread(() -> {
            try {
                Files.write(filePath, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            } catch (IOException e) {
                CustomDialogs.showErrorDialog("Error writing bytes onto file: " + e.getMessage());
            }
        }).start();
        // Return the absolute path of the saved file
        return filePath.toAbsolutePath().toString();
    }


    /**
     * Retrieve the byte array from the specified file path.
     *
     * @param filePath The absolute path of the file.
     * @return The byte array of the file.
     * @throws IOException If an I/O error occurs.
     */
    public static byte[] getBytesFromFile(String filePath) {
        Path path = Path.of(filePath);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.out.println("Error getting bytes from file" + e.getMessage());
            return null;
        }
    }
}
