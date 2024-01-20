package gov.iti.jets.services.application;

import gov.iti.jets.persistence.rowsetimpl.RowsetFactory;
import gov.iti.jets.persistence.rowsetimpl.attachmentRowset.AttachmentCacheRowset;
import gov.iti.jets.persistence.rowsetimpl.conversationRowset.ConversationCacheRowset;
import gov.iti.jets.persistence.rowsetimpl.notificationRowset.NotificationCacheRowset;
import gov.iti.jets.persistence.rowsetimpl.userRowset.UserCacheRowset;
import gov.iti.jets.services.database.LocalDatabaseServices;

public class ApplicationServices {

    public static void initApplicationServices() {
        LocalDatabaseServices.initConnection();
        // init global jdbc Object that used to perform all sql Operations .
        RowsetFactory.initJDBCRowset();
        // init all tables RowSets Objects :
        AttachmentCacheRowset.getInstance().initRowsetObj("attachment");
        ConversationCacheRowset.getInstance().initRowsetObj("conversation");
        NotificationCacheRowset.getInstance().initRowsetObj("user_notification");
        UserCacheRowset.getInstance().initRowsetObj("User");
    }
}
