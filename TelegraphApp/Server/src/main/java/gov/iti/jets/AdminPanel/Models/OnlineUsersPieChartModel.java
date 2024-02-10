package gov.iti.jets.AdminPanel.Models;

import gov.iti.jets.Domain.enums.UserStatus;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;


public class OnlineUsersPieChartModel {

    private final ObjectProperty<UserStatus> status = new SimpleObjectProperty<>();
    public UserStatus getStatus() {
        return status.get();
    }

    public ObjectProperty<UserStatus> statusProperty() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status.set(status);
    }
}