package gov.iti.jets.Model;

import java.util.ArrayList;

import DTO.ConversationDTO;
import DTO.MessageDTO;
import DTO.AttachmentDTO;

public class ConversationModel {

    private int conversationId;
    private String type;
    private ArrayList<MessageModel> messages;
    private ArrayList<AttachmentModel> attachments;

    // Constructor taking ConversationDTO as a parameter
    public ConversationModel(ConversationDTO dto) {
        this.conversationId = dto.getConversationId();
        this.type = dto.getType();
        this.messages = convertMessagesDtoToMessagesModel(dto.getMessages());
        this.attachments = convertAttachmentsDtoToAttachmentsModel(dto.getAttachments());
    }

    private ArrayList<MessageModel> convertMessagesDtoToMessagesModel(ArrayList<MessageDTO> messagesDTO) {
        ArrayList<MessageModel> messagesModel = new ArrayList<>();
        for (MessageDTO messageDTO : messagesDTO) {
            MessageModel messageModel = new MessageModel(messageDTO);
            messagesModel.add(messageModel);
        }
        return messagesModel;
    }

    private ArrayList<AttachmentModel> convertAttachmentsDtoToAttachmentsModel(ArrayList<AttachmentDTO> attachmentsDTO) {
        ArrayList<AttachmentModel> attachmentsModel = new ArrayList<>();
        for (AttachmentDTO attachmentDTO : attachmentsDTO) {
            AttachmentModel attachmentModel = new AttachmentModel(attachmentDTO);
            attachmentsModel.add(attachmentModel);
        }
        return attachmentsModel;
    }

    // Getters and Setters remain unchanged
    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<MessageModel> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageModel> messages) {
        this.messages = messages;
    }

    public ArrayList<AttachmentModel> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<AttachmentModel> attachments) {
        this.attachments = attachments;
    }
}
