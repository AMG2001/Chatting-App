package gov.iti.jets.Domain;

import java.time.LocalDateTime;

public class Message {
    private int message_id;
    private String sender_phone;
    private int attachment_id;
    private int conversation_id;
    private String message_body;
    private LocalDateTime timestamp;


    public Message(int message_id, String sender_phone, int attachment_id, int conversation_id, String message_body, LocalDateTime timestamp) {
        this.message_id = message_id;
        this.sender_phone = sender_phone;
        this.attachment_id = attachment_id;
        this.conversation_id = conversation_id;
        this.message_body = message_body;
        this.timestamp = timestamp;

    }

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getSender_phone() {
        return sender_phone;
    }

    public void setSender_phone(String sender_phone) {
        this.sender_phone = sender_phone;
    }

    public int getAttachment_id() {
        return attachment_id;
    }

    public void setAttachment_id(int attachment_id) {
        this.attachment_id = attachment_id;
    }

    public int getConversation_id() {
        return conversation_id;
    }

    public void setConversation_id(int conversation_id) {
        this.conversation_id = conversation_id;
    }

    public String getMessage_body() {
        return message_body;
    }

    public void setMessage_body(String message_body) {
        this.message_body = message_body;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "Message{" +
                "message_id=" + message_id +
                ", sender_phone='" + sender_phone + '\'' +
                ", attachment_id=" + attachment_id +
                ", conversation_id=" + conversation_id +
                ", message_body='" + message_body + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}

