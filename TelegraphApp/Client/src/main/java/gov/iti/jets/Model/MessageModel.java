package gov.iti.jets.Model;

import java.time.LocalDateTime;

import DTO.MessageDTO;

public class MessageModel {

    private int messageId;
    private String senderPhone;
    private int conversationId;
    private String messageBody;
    private LocalDateTime timeStamp;

    // Constructor taking MessageDTO as a parameter
    public MessageModel(MessageDTO dto) {
        this.messageId = dto.getMessageId();
        this.senderPhone = dto.getSenderPhone();
        this.conversationId = dto.getConversationId();
        this.messageBody = dto.getMessageBody();
        this.timeStamp = dto.getTimeStamp();
    }

    // Getters and Setters remain unchanged
    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public int getConversationId() {
        return conversationId;
    }

    public void setConversationId(int conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }
}
