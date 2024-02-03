package DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class ConversationDTO implements Serializable {
    private int conversationId;
    private String conversationName;
    private String type;
    private ArrayList<MessageDTO> messages;
    private ArrayList<AttachmentDTO> attachments;
    private byte[] conversationImage;

    public ConversationDTO(int conversationId, String conversationName, String type, ArrayList<MessageDTO> messages, ArrayList<AttachmentDTO> attachments, byte[] conversationImage) {
        this.conversationId = conversationId;
        this.conversationName = conversationName;
        this.type = type;
        this.messages = messages;
        this.attachments = attachments;
        this.conversationImage = conversationImage;
    }

    public ArrayList<AttachmentDTO> getAttachments() {
        return attachments;
    }

    public void setAttachments(ArrayList<AttachmentDTO> attachments) {
        this.attachments = attachments;
    }


    public ArrayList<MessageDTO> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<MessageDTO> messages) {
        this.messages = messages;
    }


    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getConversationName() {
        return conversationName;
    }

    public void setConversationName(String conversationName) {
        this.conversationName = conversationName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getConversationImage() {
        return conversationImage;
    }

    public void setConversationImage(byte[] conversationImage) {
        this.conversationImage = conversationImage;
    }

}
