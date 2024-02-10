package gov.iti.jets.Model;

import DTO.NotificationDTO;

import java.time.LocalDateTime;

public class NotificationModel {
    private String notificationId;
    private String type;
    private LocalDateTime timeStamp;
    private String body;

    public NotificationModel(NotificationDTO dto) {
        this.notificationId = dto.getNotificationId();
        this.type = dto.getType();
        this.timeStamp = dto.getTimeStamp();
        this.body = dto.getBody();
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
