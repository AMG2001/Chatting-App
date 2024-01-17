package gov.iti.jets.domain;

public class UserNotification {
    private String recipientPhone;
    private String notificationId;

    // Getters and Setters
    public String getRecipientPhone() {
        return recipientPhone;
    }

    public void setRecipientPhone(String recipientPhone) {
        this.recipientPhone = recipientPhone;
    }

    public String getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(String notificationId) {
        this.notificationId = notificationId;
    }
}

