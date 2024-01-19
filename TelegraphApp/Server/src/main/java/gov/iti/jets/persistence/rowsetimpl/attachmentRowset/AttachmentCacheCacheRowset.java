package gov.iti.jets.persistence.rowsetimpl.attachmentRowset;

import gov.iti.jets.persistence.PropertiesFileUtil;
import gov.iti.jets.persistence.dao.ICacheRowsetFactory;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;
import java.util.Properties;

public class AttachmentCacheCacheRowset implements ICacheRowsetFactory {
    public CachedRowSet getCacheRowsetObj() {
        return conversationCacheRowset;
    }

    private static CachedRowSet conversationCacheRowset;
    private static AttachmentCacheCacheRowset INSTANCE;
    private static Properties prop = PropertiesFileUtil.getPropertiesFromFile();
    private static javax.sql.rowset.RowSetFactory rsFactory;

    private AttachmentCacheCacheRowset() {

    }

    public static AttachmentCacheCacheRowset getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new AttachmentCacheCacheRowset();
        }
        return INSTANCE;
    }

    @Override
    public void initRowsetObj(String tableName) {
        try {
            rsFactory = RowSetProvider.newFactory();
            conversationCacheRowset = rsFactory.createCachedRowSet();
            conversationCacheRowset.setUrl(prop.getProperty(PropertiesFileUtil.getDbUrl()));
            conversationCacheRowset.setUsername(prop.getProperty(PropertiesFileUtil.getDbUsername()));
            conversationCacheRowset.setPassword(prop.getProperty(PropertiesFileUtil.getDbPassword()));
            conversationCacheRowset.setCommand(String.format("SELECT * FROM %s", tableName));
            conversationCacheRowset.execute();
            System.out.println(String.format("%s RowSet Initialized ✅✅", tableName));
        } catch (SQLException e) {
            System.out.println("#################### Error while initializing JDBC Rowset Obj , class : RowsetFactory , function : initJDBCRowset");
            e.printStackTrace();
        }
    }
}
