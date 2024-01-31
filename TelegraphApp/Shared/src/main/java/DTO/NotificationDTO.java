package DTO;

import java.io.Serializable;
import java.sql.Time;

public class NotificationDTO implements Serializable {
    private String notificationId;
    private String type;
    private Time timeStamp;
    private String body;

    public NotificationDTO(String notificationId, String type, Time timeStamp, String body) {
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

    public Time getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Time timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
