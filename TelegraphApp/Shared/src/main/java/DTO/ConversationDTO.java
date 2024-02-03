package DTO;

import java.io.Serializable;

public class ConversationDTO implements Serializable {
    private int conversationId;
    private String conversationName;
    private String type;
    private byte[] conversationImage;

    public ConversationDTO(int conversationId, String conversationName, String type, byte[] conversationImage) {
        this.conversationId = conversationId;
        this.conversationName = conversationName;
        this.type = type;
        this.conversationImage = conversationImage;
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
