package gov.iti.jets.Controllers.HomePageController;

import DTO.AttachmentDTO;
import DTO.MessageDTO;
import gov.iti.jets.Controllers.HomePageController.Attachments.AttachmentPaneViewer;
import gov.iti.jets.Controllers.HomePageController.Attachments.AttachmentsController;
import gov.iti.jets.Controllers.Shared.Messages.MessageController;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Controllers.services.StagesLauncher;
import gov.iti.jets.Model.ClientState;
import gov.iti.jets.ServiceContext.AttachmentService;
import gov.iti.jets.ServiceContext.MessageService;
import gov.iti.jets.ServiceContext.UserService;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChatPaneController {

    @FXML
    private Button btn_sendAttachment;

    @FXML
    private VBox chatArea;

    @FXML
    private ImageView chatImage;

    @FXML
    private Text chatName;

    @FXML
    private ListView<MessageController> lv_chatMessages;

    @FXML
    private HBox message_send_area;
    @FXML
    private VBox pane_chatPane;
    @FXML
    private Text receiverStatus;
    @FXML
    private Circle receiverStatusCircle;
    @FXML
    private TextArea messageArea;
    @FXML
    private Button btn_sendMessage;
    VBox layout;
    FXMLLoader loader;
    ConversationCard contactCardData;

    @FXML
    public void initialize() {
        changeListViewCellFactory();
        setSendButtonAction();
    }

    private void setSendButtonAction() {
        btn_sendMessage.setOnAction(event -> {
            sendMessage();
        });
    }

    private void bindListViewOnObservableList(ObservableList<MessageController> observableList) {
        lv_chatMessages.itemsProperty().bind(new SimpleListProperty<>(observableList));
    }

    private void changeListViewCellFactory() {
        lv_chatMessages.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(MessageController messageController, boolean empty) {
                super.updateItem(messageController, empty);
                if (empty || messageController == null) {
                    setGraphic(null);
                } else {
                    setGraphic(messageController.getLayout());
                }
            }
        });
    }

    public ChatPaneController(ConversationCard conversationCard) {
        try {
            loader = new FXMLLoader(getClass().getResource("/Dashboard/ChatArea/chatPane.fxml"));
            loader.setController(this);
            layout = loader.load();
            this.contactCardData = conversationCard;
            chatImage.setImage(conversationCard.img_contact.getImage());
            chatName.setText(conversationCard.text_contactName.getText());
            receiverStatus.setText(conversationCard.status_text.getText());
            receiverStatusCircle.setFill(conversationCard.status_circle.getFill());
            btn_sendAttachment.setOnAction(event -> pickAttachmentAndSentIt(conversationCard.getConversationID()));
            if (ClientState.getInstance().conversationsMessagesList.containsKey(conversationCard.getConversationID())) {
                // This mean that the Messages is loaded before from server .
                loadConversationMessagesFromLocal(conversationCard);
            } else {
                // this mean it's first time the chat is opened , then load messages from Server .
                loadChatMessagesFromServer(conversationCard);
            }
        } catch (Exception e) {
            CustomDialogs.showErrorDialog("Error while loading HomePageView : " + e.getMessage());
            System.err.println("Error while loading HomePageView : " + e.getMessage());
        }
    }


    public VBox getLayout() {
        return layout;
    }

    private void loadConversationMessagesFromLocal(ConversationCard conversationCard) {
        lv_chatMessages.setItems(ClientState.getInstance().conversationsMessagesList.get(conversationCard.getConversationID()));
    }

    private void loadChatMessagesFromServer(ConversationCard conversationCard) {
        try {
            // Just for Debugging .
            System.out.println("Conversation ID : " + conversationCard.getConversationID());
            if (MessageService.getInstance().getRemoteService().getAllMessagesForConversation(conversationCard.getConversationID()) == null) {
                System.out.println("Messages of Conversation : " + conversationCard.getConversationID() + " Are Null !!");
            } else {
                // Check if the conversation messages are loaded in Client Side .
                if (ClientState.getInstance().conversationsMessagesList.containsKey(conversationCard.getConversationID())) {
                    // get all conversation messages from client storage .
                    lv_chatMessages.getItems().clear();
                    bindListViewOnObservableList(ClientState.getInstance().conversationsMessagesList.get(conversationCard.getConversationID()));
                } else {
                    System.out.println("Loading Messages From Server .");
                    // create new conversations messages observable.
                    ObservableList<MessageController> newMessagesList = FXCollections.observableArrayList();
                    bindListViewOnObservableList(newMessagesList);
                    ClientState.getInstance().conversationsMessagesList.put(conversationCard.getConversationID(), newMessagesList);
                    System.out.println("Messages list created and add to ConversationsMap");
                    // Load all messages from server then show them on UI .
                    MessageService.getInstance().getRemoteService().getAllMessagesForConversation(conversationCard.getConversationID()).stream().forEach(messageDTO -> {
                        MessageController messageController = new MessageController(messageDTO);
                        System.out.println("Message Body : " + messageDTO.getMessageBody() + " From : " + messageDTO.getSenderPhone());
                        // To Store all messages related to it's Conversation in ConversationsMessages Map .
                        newMessagesList.add(messageController);
                    });
                }
            }
        } catch (RemoteException e) {
            CustomDialogs.showErrorDialog("Error while loading chat messages from server : " + e.getMessage());
        }
    }

    private void sendMessage() {
        if (messageArea.getText().isEmpty()) {
            CustomDialogs.showErrorDialog("You can't leave message area empty !!");
        } else {
            MessageController messageController = new MessageController(ClientState.getInstance().loggedinUser.getValue().getUserPhone(), messageArea.getText(), LocalDateTime.now(), ClientState.getInstance().loggedinUser.getValue().getProfilePic());
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessageBody(messageArea.getText());
            messageDTO.setSenderPhone(ClientState.getInstance().loggedinUser.getValue().getUserPhone());
            messageDTO.setConversationId(contactCardData.getConversationID());
            messageDTO.setTimeStamp(LocalDateTime.now());
            messageArea.clear();
            try {
                MessageService.getInstance().getRemoteService().sendMessage(messageDTO);
                System.out.println("Message Sent Successfully ");
            } catch (RemoteException e) {
                CustomDialogs.showErrorDialog("Error while sending message : " + e.getMessage());
            }
        }

    }

    @FXML
    void showAttachmentsPane(ActionEvent event) {
        StagesLauncher.LaunchNewStage(new AttachmentPaneViewer(contactCardData.getConversationID()).getLayout(), "Attachment Pane", 400, 500);
    }

    private void pickAttachmentAndSentIt(int conversationID) {
        try {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose an attachment");
            File file = fileChooser.showOpenDialog(null);
            if (file != null) {
                try {
                    AttachmentDTO attachmentDTO = new AttachmentDTO();
                    attachmentDTO.setConversationId(conversationID);
                    attachmentDTO.setAttachmentName(getFileNameWithoutExtension(file));
                    attachmentDTO.setAttachmentType(getFileExtension(file));
                    attachmentDTO.setAttachment(Files.readAllBytes(file.toPath()));
                    AttachmentService.getInstance().getRemoteService().sendAttachment(attachmentDTO);
                } catch (RuntimeException re) {
                    CustomDialogs.showErrorDialog("Error while Choosing an Attachment : " + re.getMessage());
                }
            } else {
                CustomDialogs.showErrorDialog("You Must choose an attachment !!");
            }
        } catch (Exception e) {
            System.out.println("Image Not Taken !!");
        }
    }

    private String getFileNameWithoutExtension(File file) {
        String filenameWithoutExtension = Paths.get(file.getName()).getFileName().toString().replaceFirst("[.][^.]+$", "");
        return filenameWithoutExtension;
    }


    private String getFileExtension(File file) {
        String extension = "";
        String fileName = file.getName();
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i);
        }
        return extension;
    }
}
