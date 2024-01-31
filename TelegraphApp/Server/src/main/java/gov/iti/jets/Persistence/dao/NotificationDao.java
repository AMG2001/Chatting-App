package gov.iti.jets.Persistence.dao;

import gov.iti.jets.Domain.Notification;

import java.util.List;

public interface NotificationDao extends GenericDatabaseDao<Notification>, RetrievableByID<Notification,String> {
    List<Notification> getNotificationsByRecipientPhone(String phone);
}
