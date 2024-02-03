package DTO;

import java.io.Serializable;
import java.util.ArrayList;

public class ConversationDTO implements Serializable {
    private int conversationId;
    private String conversationName;
    private String type;
    ArrayList<MessageDTO> messages;
    private byte[] conversationImage;


    public ConversationDTO(int conversationId, String conversationName, String type, byte[] conversationImage, ArrayList<MessageDTO> messages) {
        this.conversationId = conversationId;
        this.conversationName = conversationName;
        this.type = type;
        this.conversationImage = conversationImage;
        this.messages = messages;
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
