package gov.iti.jets.Controllers.services.ChatBot;


import gov.iti.jets.Controllers.Shared.Messages.MessageController;
import gov.iti.jets.Controllers.services.CustomDialogs;
import gov.iti.jets.Model.ClientState;
import io.github.palexdev.materialfx.controls.MFXButton;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class ChatBotChatPaneViewer {

    @FXML
    private MFXButton btn_send;

    @FXML
    private ListView<ChatBotChatMessageController> lv_chatMessages;

    @FXML
    private TextArea textArea;

    FXMLLoader loader;
    VBox layout;
    private String sender;

    @FXML
    void sendMessage(ActionEvent event) {
        if (!textArea.getText().isEmpty()) {
            ChatBotChatMessageController message = new ChatBotChatMessageController(ClientState.getInstance().getLoggedinUserModel().getUserPhone());
            message.setMessageDetails(textArea.getText());
            Platform.runLater(() -> ClientState.getInstance().chatBotChatMessages.add(message));
            getChatBotResponse(textArea.getText());
            textArea.clear();
        }
    }

    public void getChatBotResponse(String userMessage) {
        new Thread(() -> {
            String chatBotReponse = ChatBot.call(userMessage);
            ChatBotChatMessageController chatbotMessage = new ChatBotChatMessageController("00000000000");
            chatbotMessage.setMessageDetails(chatBotReponse);
            Platform.runLater(() -> ClientState.getInstance().chatBotChatMessages.add(chatbotMessage));
        }).start();
    }


    public void changeSender(String sender) {
        this.sender = sender;
    }

    public ChatBotChatPaneViewer() {
        try {
            loader = new FXMLLoader(getClass().getResource("/ChatBot/ChatbotChatPane.fxml"));
            loader.setController(this);
            layout = loader.load();
            bindListViewOnObservableList();
            changeListViewCellFactory();
        } catch (IOException e) {
            System.out.println("❌❌❌❌❌❌❌❌❌❌❌ Error while loading Chatbot Chat Pane Controller : " + e.getMessage());
            CustomDialogs.showErrorDialog("Error while loading Chatbot Chat Pane Controller" + e.getMessage());
        }
    }

    public VBox getLayout() {
        return layout;
    }

    private void bindListViewOnObservableList() {
        lv_chatMessages.itemsProperty().bind(new SimpleListProperty<>(ClientState.getInstance().chatBotChatMessages));
    }

    private void changeListViewCellFactory() {
        lv_chatMessages.setCellFactory(param -> new ListCell<>() {
            @Override
            protected void updateItem(ChatBotChatMessageController messageController, boolean empty) {
                super.updateItem(messageController, empty);
                if (empty || messageController == null) {
                    setGraphic(null);
                } else {
                    setGraphic(messageController.getLayout());
                }
            }
        });
    }
}
