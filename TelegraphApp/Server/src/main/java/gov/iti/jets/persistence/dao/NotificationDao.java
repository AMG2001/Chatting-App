package gov.iti.jets.persistence.dao;

import gov.iti.jets.domain.Notification;

import java.util.List;

public interface NotificationDao extends GenericDatabaseDao<Notification>, RetrievableByID<Notification,String> {
    List<Notification> getNotificationsByRecipientPhone(String phone);
}
