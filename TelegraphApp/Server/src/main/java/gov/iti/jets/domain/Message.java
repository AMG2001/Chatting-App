package gov.iti.jets.domain;

import java.util.Date;

public class Message {
    private String messageId;
    private String senderPhone;
    private String attachmentId;
    private String conversationId;
    private String messageBody;
    private Date timeStamp;

    public Message(String messageId, String senderPhone, String attachmentId, String conversationId, String messageBody, Date timeStamp) {
        this.messageId = messageId;
        this.senderPhone = senderPhone;
        this.attachmentId = attachmentId;
        this.conversationId = conversationId;
        this.messageBody = messageBody;
        this.timeStamp = timeStamp;
    }

    // Getters and Setters
    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getSenderPhone() {
        return senderPhone;
    }

    public void setSenderPhone(String senderPhone) {
        this.senderPhone = senderPhone;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

