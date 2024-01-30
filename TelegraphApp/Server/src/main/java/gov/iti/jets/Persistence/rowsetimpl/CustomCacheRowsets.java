package gov.iti.jets.Persistence.rowsetimpl;

import gov.iti.jets.Persistence.PropertiesFileUtil;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;
import java.util.Properties;

public class CustomCacheRowsets{
    private static CachedRowSet conversationCacheRowset;
    private static CachedRowSet attachmentCacheRowset;
    private static CachedRowSet messageCacheRowset;
    private static CachedRowSet notificationCacheRowset;
    private static CachedRowSet userCacheRowset;
    private static Properties prop = PropertiesFileUtil.getPropertiesFromFile();
    private static javax.sql.rowset.RowSetFactory rsFactory;
    public static CachedRowSet getAttachmentCacheRowset() {
        return attachmentCacheRowset;
    }
    public static CachedRowSet getMessageCacheRowset() {
        return messageCacheRowset;
    }
    public static CachedRowSet getNotificationCacheRowset() {
        return notificationCacheRowset;
    }
    public static CachedRowSet getUserCacheRowset() {
        return userCacheRowset;
    }
    public CachedRowSet getConversationCacheRowset() {
        return conversationCacheRowset;
    }
    private static CachedRowSet initRowsetObj(String tableName) {
        CachedRowSet cachedRowSetObj = null;
        try {
            rsFactory = RowSetProvider.newFactory();
            cachedRowSetObj = rsFactory.createCachedRowSet();
            cachedRowSetObj.setUrl(prop.getProperty(PropertiesFileUtil.getDbUrl()));
            cachedRowSetObj.setUsername(prop.getProperty(PropertiesFileUtil.getDbUsername()));
            cachedRowSetObj.setPassword(prop.getProperty(PropertiesFileUtil.getDbPassword()));
            cachedRowSetObj.setCommand(String.format("SELECT * FROM %s", tableName));
            cachedRowSetObj.execute();
            System.out.println(String.format("%s RowSet Initialized ✅✅", tableName));
        } catch (SQLException e) {
            System.out.println(String.format("#################### Error while initializing JDBC Rowset Obj : , class : CustomCacheRowsets , function : initRowsetObj", tableName));
            e.printStackTrace();
        }
        return cachedRowSetObj;
    }
    public static void initializeRowSets() {
        conversationCacheRowset = initRowsetObj("conversation");
        attachmentCacheRowset = initRowsetObj("attachment");
        messageCacheRowset = initRowsetObj("message");
        notificationCacheRowset = initRowsetObj("notification");
        userCacheRowset = initRowsetObj("user");
    }
}