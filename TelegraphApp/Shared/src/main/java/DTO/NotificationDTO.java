package DTO;

import java.io.Serializable;
import java.time.LocalDateTime;

public class NotificationDTO implements Serializable {
    private String notificationId;
    private String type;
    private LocalDateTime timeStamp;
    private String body;

    public NotificationDTO(String notificationId, String type, LocalDateTime timeStamp, String body) {
        this.notificationId = notificationId;
        this.type = type;
        this.timeStamp = timeStamp;
        this.body = body;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
