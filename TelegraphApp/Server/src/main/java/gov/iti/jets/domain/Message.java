package gov.iti.jets.domain;

import java.sql.Time;
import java.util.Date;

public class Message {
    private int messageId;
    private String senderPhone;
    private int attachmentId;
    private int conversationId;
    private String messageBody;
    private Time timeStamp;
    public Message(){

    }

    public Message(int messageId, String senderPhone, int attachmentId, int conversationId, String messageBody, Time timeStamp) {
        this.messageId = messageId;
        this.senderPhone = senderPhone;
        this.attachmentId = attachmentId;
        this.conversationId = conversationId;
        this.messageBody = messageBody;
        this.timeStamp = timeStamp;
    }

    // Getters and Setters
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

    public int getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(int attachmentId) {
        this.attachmentId = attachmentId;
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

    public Time getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Time timeStamp) {
        this.timeStamp = timeStamp;
    }
}

