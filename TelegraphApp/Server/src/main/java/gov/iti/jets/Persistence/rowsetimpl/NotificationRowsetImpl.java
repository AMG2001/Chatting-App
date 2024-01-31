package gov.iti.jets.Persistence.rowsetimpl;

import gov.iti.jets.Domain.Notification;
import gov.iti.jets.Persistence.dao.NotificationDao;
import gov.iti.jets.Persistence.rowsetimpl.notificationRowset.NotificationCacheRowset;
import gov.iti.jets.Persistence.rowsetimpl.userRowset.UserCacheRowset;
import gov.iti.jets.Persistence.LocalDatabaseServices;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.List;

public class NotificationRowsetImpl implements NotificationDao {
    CachedRowSet rowset;

    @Override
    public void add(Notification entity) {
        try {
            rowset = NotificationCacheRowset.getInstance().getCacheRowsetObj();
            RowsetFactory.userCachedRowsetObj.moveToInsertRow();
            rowset.updateString(1, entity.getNotificationId());
            rowset.updateString(2, entity.getBody());
            rowset.updateTime(3, entity.getTimeStamp());
            rowset.updateString(4, entity.getType());
            rowset.insertRow();
            rowset.moveToCurrentRow();
            rowset.acceptChanges(LocalDatabaseServices.getConnectionObj());
            rowset.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public List<Notification> getAll() {
        return null;
    }

    @Override
    public void update(Notification entity) {
        try {
            rowset = NotificationCacheRowset.getInstance().getCacheRowsetObj();
            rowset.absolute(rowset.findColumn("notificationId"));
            while (rowset.next()) {
                if (entity.getNotificationId().equals(rowset.getString("notificationId"))) {
                    rowset.updateString(1, entity.getNotificationId());
                    rowset.updateString(2, entity.getBody());
                    rowset.updateTime(3, entity.getTimeStamp());
                    rowset.updateString(4, entity.getType());
                    rowset.updateRow();
                    break;
                }
            }
            rowset.acceptChanges(LocalDatabaseServices.getConnectionObj());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void delete(Notification entity) {
        try {
            rowset = NotificationCacheRowset.getInstance().getCacheRowsetObj();
            rowset.absolute(rowset.findColumn("notificationId"));
            while (rowset.next()) {
                if (entity.getNotificationId().equals(rowset.getString("notificationId"))) {
                    rowset.deleteRow();
                    break;
                }
            }
            rowset.acceptChanges(LocalDatabaseServices.getConnectionObj());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }


    @Override
    public List<Notification> getNotificationsByRecipientPhone(String phone) {
        return null;
    }

    @Override
    public Notification getById(String s) {
        return null;
    }
}

