package DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class MessageDTO implements Serializable {
    private int messageId;
    private String senderPhone;
    private int conversationId;
    private String messageBody;
    private LocalDateTime timeStamp;

    public MessageDTO(int messageId, String senderPhone, int conversationId, String messageBody, LocalDateTime timeStamp) {
        this.messageId = messageId;
        this.senderPhone = senderPhone;
        this.conversationId = conversationId;
        this.messageBody = messageBody;
        this.timeStamp = timeStamp;
    }

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