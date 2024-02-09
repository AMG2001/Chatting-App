package gov.iti.jets.Controllers.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileSystemUtil {

    private static final String ATTACHMENT_DIRECTORY = System.getProperty("user.home") + "/telegraph/attachment";

    /**
     * Store a byte array as a file in the specified directory.
     *
     * @param bytes    The byte array to be stored.
     * @param fileName The name of the file.
     * @return The absolute path of the saved file.
     * @throws IOException If an I/O error occurs.
     */
    public static String storeByteArrayAsFile(byte[] bytes, String fileName) {

        //TODO handle exceptions
        Path directoryPath = Paths.get(ATTACHMENT_DIRECTORY);
        // Create the directory if it doesn't exist
        if (!Files.exists(directoryPath)) {
            try {
                Files.createDirectories(directoryPath);
            } catch (IOException e) {
                System.out.println("Error Creating Directory: "+e.getMessage());
            }
        }
        // Create the file path
        Path filePath = directoryPath.resolve(fileName);
        // Write the byte array to the file
        try {
            Files.write(filePath, bytes, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            System.out.println("Error writing bytes onto file: "+e.getMessage());
        }
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
        //TODO handle exceptions
        Path path = Path.of(filePath);
        try {
            return Files.readAllBytes(path);
        } catch (IOException e) {
            System.out.println("Error getting bytes from file"+e.getMessage());
            return null;
        }
    }
}
