package gov.iti.jets.Controllers.HomePageController.Attachments;

import DTO.AttachmentDTO;
import gov.iti.jets.Client;
import gov.iti.jets.Controllers.Shared.Messages.MessageController;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.FileSystemUtil;
import gov.iti.jets.Model.AttachmentModel;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.ServiceContext.AttachmentService;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.rmi.RemoteException;
import java.util.List;

public class AttachmentPaneViewer {
    @FXML
    private ListView<AttachmentsController> lv_attachments;
    FXMLLoader loader;
    ListView<AttachmentsController> layout;

    public AttachmentPaneViewer(int conversationID) {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/AttachmentPane/AttachmentPaneViewer.fxml"));
            loader.setController(this);
            layout = loader.load();
            Platform.runLater(() -> loadAttachments(conversationID));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAttachments(int conversationID) {
        // first of all check if the attachments are stored in client in AttachmentsMap .
        try {
            if (ClientState.getInstance().attachmentsMap.containsKey(conversationID)) {
                // this mean that attachments are loaded from server before , Just get them .
                lv_attachments.itemsProperty().bind(new SimpleListProperty<>(ClientState.getInstance().attachmentsMap.get(
                        conversationID
                )));
            } else {
                // this mean that attachments are not loaded from server before , get them from server .
                // get all attachments for this conversation from server 👇🏻👇🏻 .
                List<AttachmentDTO> attachmentDTOList = AttachmentService.getInstance().getRemoteService().getAllAttachmentsForConversation(conversationID);
                // check if there are an attachments in this conversation 👇🏻👇🏻  .
                System.out.println("✅✅✅✅✅✅✅✅✅✅✅✅✅✅ Attachments Are Loaded from server .");
                if (attachmentDTOList.size() != 0) {
                    // create new Observable List
                    ObservableList<AttachmentsController> attachmentsControllersList = FXCollections.observableArrayList();
                    // add the new Conversation ID and it's Attachments Observable .
                    ClientState.getInstance().attachmentsMap.put(conversationID, attachmentsControllersList);
                    // loop on fetched Attachments from server and add them into the listView .
                    for (AttachmentDTO attachmentDTO : attachmentDTOList) {
                        System.out.println(attachmentDTO.getAttachmentType());
                        AttachmentModel attachmentModel = new AttachmentModel(attachmentDTO);
                        attachmentsControllersList.add(new AttachmentsController(attachmentModel));
                    }
                    // this mean that attachments are loaded from server before , Just get them .
                    bindListOnListViewAndChangeSetCell(attachmentsControllersList);
                }
            }

        } catch (RemoteException e) {
            CustomDialogs.showErrorDialog("Error while loading attachments from server" + e.getMessage());
        }
    }

    private void bindListOnListViewAndChangeSetCell(ObservableList<AttachmentsController> list) {
        lv_attachments.itemsProperty().bind(new SimpleListProperty<>(list));
        lv_attachments.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(AttachmentsController attachmentsController, boolean empty) {
                super.updateItem(attachmentsController, empty);
                if (empty || attachmentsController == null) {
                    setGraphic(null);
                } else {
                    setGraphic(attachmentsController.getLayout());
                }
            }
        });
        // Override the action of the ListView .
        lv_attachments.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) { // Double-click detected
                AttachmentsController selectedController = lv_attachments.getSelectionModel().getSelectedItem();
                if (selectedController != null) {
                    try {
                        byte[] fileBytes = AttachmentService.getInstance().getRemoteService().getAttachmentData(selectedController.getAttachmentModel().getConversationId(), selectedController.getAttachmentModel().getAttachmentId());
                        CustomDialogs.showInformativeDialogWithActions("Do you want to Download file with name : " + selectedController.getAttachmentModel().getAttachmentName(), () -> {
                            FileSystemUtil.storeByteArrayAsFile(fileBytes, selectedController.getAttachmentModel().getAttachmentName());
                            CustomDialogs.showInformativeDialog("File : \" " + selectedController.getAttachmentModel().getAttachmentName() + " \" Downloaded Successfully .");
                        }, () -> {

                        });
                    } catch (RemoteException e) {
                        CustomDialogs.showErrorDialog("Error while downloading attachment : " + e.getMessage());
                    }
                }
            }
        });
    }

    public ListView<AttachmentsController> getLayout() {
        return layout;
    }


}

