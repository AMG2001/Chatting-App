package gov.iti.jets.services.application;

import gov.iti.jets.persistence.rowsetimpl.RowsetFactory;
import gov.iti.jets.persistence.rowsetimpl.attachmentRowset.AttachmentCacheCacheRowset;
import gov.iti.jets.persistence.rowsetimpl.conversationRowset.ConversationCacheCacheRowset;
import gov.iti.jets.persistence.rowsetimpl.notificationRowset.NotificationCacheCacheRowset;
import gov.iti.jets.persistence.rowsetimpl.userRowset.UserCacheRowset;
import gov.iti.jets.services.database.LocalDatabaseServices;

public class ApplicationServices {

    public static void initApplicationServices() {
        LocalDatabaseServices.initConnection();
        // init global jdbc Object that used to perform all sql Operations .
        RowsetFactory.initJDBCRowset();
        // init all tables RowSets Objects :
        AttachmentCacheCacheRowset.getInstance().initRowsetObj("attachment");
        ConversationCacheCacheRowset.getInstance().initRowsetObj("conversation");
        NotificationCacheCacheRowset.getInstance().initRowsetObj("user_notification");
        UserCacheRowset.getInstance().initRowsetObj("User");
    }
}
