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
    private AttachmentModel attachmentModel;

    public AttachmentsController(AttachmentModel attachmentModel) {
        this.attachmentModel = attachmentModel;
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/AttachmentPane/AttachmentCard.fxml"));
            loader.setController(this);
            layout = loader.load();
            attachment_icon.setImage(getAttachmentIcon(this.attachmentModel.getAttachmentType()));
            attachment_icon.setFitWidth(60);
            attachment_icon.setFitHeight(60);
            attachment_name.setText(this.attachmentModel.getAttachmentName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Image getAttachmentIcon(String type) {
        if (type.equals(".png") || type.equals(".jpg") || type.equals(".jpeg")) {
            return new Image("/Dashboard/AttachmentPane/Icons/Image.png");
        } else if (type.equals(".mp4") || type.equals(".avi") || type.equals(".mov")) {
            return new Image("/Dashboard/AttachmentPane/Icons/Video.png.png");
        } else if (type.equals(".mp3") || type.equals(".wav") || type.equals(".aac")) {
            return new Image("/Dashboard/AttachmentPane/Icons/audio.png");
        } else if (type.equals(".txt") || type.equals(".docx") || type.equals(".pdf")) {
            return new Image("/Dashboard/AttachmentPane/Icons/File.png");
        } else {
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

    public HBox getLayout() {
        return layout;
    }
}
