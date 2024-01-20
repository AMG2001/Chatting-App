package gov.iti.jets.persistence.rowsetimpl.messageRowset;

import gov.iti.jets.persistence.PropertiesFileUtil;
import gov.iti.jets.persistence.dao.ICacheRowsetFactory;


import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;
import java.util.Properties;

public class MessageCacheRowset implements ICacheRowsetFactory {

    public CachedRowSet getCacheRowsetObj() {
        return messageCacheRowset;
    }

    private static CachedRowSet messageCacheRowset;
    private static MessageCacheRowset INSTANCE;
    private static Properties prop = PropertiesFileUtil.getPropertiesFromFile();
    private static javax.sql.rowset.RowSetFactory rsFactory;

    private MessageCacheRowset() {
    }

    public static MessageCacheRowset getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new MessageCacheRowset();
        }
        return INSTANCE;
    }

    @Override
    public void initRowsetObj(String tableName) {
        try {
            rsFactory = RowSetProvider.newFactory();
            messageCacheRowset = rsFactory.createCachedRowSet();
            messageCacheRowset.setUrl(prop.getProperty(PropertiesFileUtil.getDbUrl()));
            messageCacheRowset.setUsername(prop.getProperty(PropertiesFileUtil.getDbUsername()));
            messageCacheRowset.setPassword(prop.getProperty(PropertiesFileUtil.getDbPassword()));
            messageCacheRowset.setCommand(String.format("SELECT * FROM %s", tableName));
            messageCacheRowset.execute();
            System.out.println(String.format("%s RowSet Initialized ✅✅", tableName));
        } catch (SQLException e) {
            System.out.println("#################### Error while initializing JDBC Rowset Obj , class : RowsetFactory , function : initJDBCRowset");
            e.printStackTrace();
        }
    }
}
