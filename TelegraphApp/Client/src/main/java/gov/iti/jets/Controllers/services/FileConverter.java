package gov.iti.jets.Controllers.services;

import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

public class FileConverter {
    public static byte[] convert_imageToBytes(javafx.scene.image.Image image) {
        try {
            BufferedImage bufferedImage = SwingFXUtils.fromFXImage(image, null);
            ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", byteOutput);
            return byteOutput.toByteArray();
        } catch (Exception e) {
            System.out.println("❌❌❌❌❌❌❌❌ Error converting image to bytes: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public static javafx.scene.image.Image convert_bytesToImage(byte[] bytes) {
        try {
            BufferedImage bufferedImage = ImageIO.read(new java.io.ByteArrayInputStream(bytes));
            return SwingFXUtils.toFXImage(bufferedImage, null);
        } catch (Exception e) {
            System.out.println("❌❌❌❌❌❌❌❌ Error converting bytes to image: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
}
