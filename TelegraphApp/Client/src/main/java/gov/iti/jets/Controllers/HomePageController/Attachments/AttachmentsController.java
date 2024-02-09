package gov.iti.jets.Controllers.HomePageController.Attachments;

import gov.iti.jets.Model.AttachmentModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class AttachmentsController {
    @FXML
    private ImageView attachment_icon;

    @FXML
    private Label attachment_name;
    FXMLLoader loader;
    HBox layout;

    public AttachmentsController(AttachmentModel attachmentModel) {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/AttachmentPane/AttachmentCard.fxml"));
            loader.setController(this);
            layout = loader.load();
            attachment_icon.setImage(getAttachmentIcon(attachmentModel.getAttachmentType()));
            attachment_name.setText(attachmentModel.getAttachmentName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image getAttachmentIcon(String type) {
        switch (type) {
            case ".png", ".jpg", ".jpeg":
                return new Image("/Dashboard/AttachmentPane/Icons/Image.png");
            case ".mp4", ".avi", ".mov":
                return new Image("/Dashboard/AttachmentPane/Icons/Video.png.png");
            case ".mp3", ".wav", ".aac":
                return new Image("/Dashboard/AttachmentPane/Icons/audio.png");
            case ".txt", ".docx", ".pdf":
                return new Image("/Dashboard/AttachmentPane/Icons/File.png");
            default:
                // Default case for unknown types
                return new Image("/Dashboard/AttachmentPane/Icons/Unkown.png");
        }
    }


    public String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }

        int lastDotIndex = fileName.lastIndexOf('.');
        if (lastDotIndex >= 0) {
            return fileName.substring(lastDotIndex + 1);
        } else {
            return "";
        }
    }

}
