package DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class ConversationDTO implements Serializable {
    private static final long serialVersionUID = 0L;
    private int conversationId;
    private String type;
    private ArrayList<MessageDTO> messages;
    private ArrayList<AttachmentDTO> attachments;

    public ConversationDTO(){

    }

    public ConversationDTO(int conversationId, String type, ArrayList<MessageDTO> messages, ArrayList<AttachmentDTO> attachments) {
        this.conversationId = conversationId;
        this.type = type;
        this.messages = messages;
        this.attachments = attachments;
    }

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

    public ArrayList<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageDTO> messages) {
        this.messages = messages;
    }

    public ArrayList<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }
}
