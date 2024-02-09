package gov.iti.jets.Controllers.HomePageController.Attachments;

import gov.iti.jets.Controllers.Shared.Messages.MessageController;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;

import java.io.IOException;

public class AttachmentPaneViewer {
    @FXML
    private ListView<AttachmentsController> lv_attachments;
    FXMLLoader loader;
    ListView<MessageController> layout;

    public AttachmentPaneViewer(int conversationID) {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/AttachmentPane/AttachmentPaneViewer.fxml"));
            loader.setController(this);
            layout = loader.load();
            // bind lv_attachments on attachmentsList
            // TODO load attachments list depending on conversation id .
//            lv_attachments.itemsProperty().bind(new SimpleListProperty<>(attachmentsList));
//            lv_attachments.set
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ListView<MessageController> getLayout() {
        return layout;
    }
}

