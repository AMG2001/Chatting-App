package gov.iti.jets.persistence.rowsetimpl.userRowset;

import gov.iti.jets.persistence.PropertiesFileUtil;
import gov.iti.jets.persistence.dao.ICacheRowsetFactory;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.SQLException;
import java.util.Properties;

public class UserCacheRowset implements ICacheRowsetFactory {
    public CachedRowSet getUserCacheRowset() {
        return userCacheRowset;
    }

    private static CachedRowSet userCacheRowset;
    private static UserCacheRowset INSTANCE;
    private static Properties prop = PropertiesFileUtil.getPropertiesFromFile();
    private static javax.sql.rowset.RowSetFactory rsFactory;

    private UserCacheRowset() {

    }
    public static UserCacheRowset getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserCacheRowset();
        }
        return INSTANCE;
    }

    @Override
    public void initRowsetObj(String tableName) {
        try {
            rsFactory = RowSetProvider.newFactory();
            userCacheRowset = rsFactory.createCachedRowSet();
            userCacheRowset.setUrl(prop.getProperty(PropertiesFileUtil.getDbUrl()));
            userCacheRowset.setUsername(prop.getProperty(PropertiesFileUtil.getDbUsername()));
            userCacheRowset.setPassword(prop.getProperty(PropertiesFileUtil.getDbPassword()));
            userCacheRowset.setCommand(String.format("SELECT * FROM %s", tableName));
            userCacheRowset.execute();
            System.out.println(String.format("%s RowSet Initialized ✅✅", tableName));
        } catch (SQLException e) {
            System.out.println("#################### Error while initializing JDBC Rowset Obj , class : RowsetFactory , function : initJDBCRowset");
            e.printStackTrace();
        }
    }

}
