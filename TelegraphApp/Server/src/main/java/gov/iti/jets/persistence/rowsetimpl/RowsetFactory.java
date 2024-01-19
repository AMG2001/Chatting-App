package gov.iti.jets.persistence.rowsetimpl;

import gov.iti.jets.persistence.PropertiesFileUtil;

import java.sql.SQLException;
import java.util.Properties;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.WebRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.FilteredRowSet;

public class RowsetFactory {

    public static JdbcRowSet rowsetObj;
    private static Properties prop = PropertiesFileUtil.getPropertiesFromFile();

    private static javax.sql.rowset.RowSetFactory rsFactory;

    public static void initJDBCRowset() {
        try {
            rsFactory = RowSetProvider.newFactory();
            rowsetObj = rsFactory.createJdbcRowSet();
            rowsetObj.setUrl(prop.getProperty(PropertiesFileUtil.getDbUrl()));
            rowsetObj.setUsername(prop.getProperty(PropertiesFileUtil.getDbUsername()));
            rowsetObj.setPassword(prop.getProperty(PropertiesFileUtil.getDbPassword()));
            System.out.println("JDBC Rowset Object Initialized ✅✅");
        } catch (SQLException e) {
            System.out.println("#################### Error while initializing JDBC Rowset Obj , class : RowsetFactory , function : initJDBCRowset");
            e.printStackTrace();
        }
    }

    public static CachedRowSet getCachedRowset() {
        // TODO Need to be updated , we will need connection Object Here .
        CachedRowSet cachedRowset = null;
        try {
            rsFactory = RowSetProvider.newFactory();
            cachedRowset = rsFactory.createCachedRowSet();
            cachedRowset.setUrl(prop.getProperty(PropertiesFileUtil.getDbUrl()));
            cachedRowset.setUsername(prop.getProperty(PropertiesFileUtil.getDbUsername()));
            cachedRowset.setPassword(prop.getProperty(PropertiesFileUtil.getDbPassword()));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cachedRowset;
    }

    public static WebRowSet getWebRowset() {
        WebRowSet webRowSet = null;
        try {
            rsFactory = RowSetProvider.newFactory();
            webRowSet = rsFactory.createWebRowSet();
            webRowSet.setUrl(prop.getProperty(PropertiesFileUtil.getDbUrl()));
            webRowSet.setUsername(prop.getProperty(PropertiesFileUtil.getDbUsername()));
            webRowSet.setPassword(prop.getProperty(PropertiesFileUtil.getDbPassword()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return webRowSet;
    }

    public static FilteredRowSet getFilteredRowset() {
        FilteredRowSet filteredRowset = null;
        try {
            filteredRowset = rsFactory.createFilteredRowSet();


            filteredRowset.setUrl(prop.getProperty(PropertiesFileUtil.getDbUrl()));
            filteredRowset.setUsername(prop.getProperty(PropertiesFileUtil.getDbUsername()));
            filteredRowset.setPassword(prop.getProperty(PropertiesFileUtil.getDbPassword()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return filteredRowset;
    }


    public static JoinRowSet getJoinRowset() {
        JoinRowSet joinRowset = null;
        try {
            rsFactory = RowSetProvider.newFactory();
            joinRowset = rsFactory.createJoinRowSet();
            joinRowset.setUrl(prop.getProperty(PropertiesFileUtil.getDbUrl()));
            joinRowset.setUsername(prop.getProperty(PropertiesFileUtil.getDbUsername()));
            joinRowset.setPassword(prop.getProperty(PropertiesFileUtil.getDbPassword()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return joinRowset;
    }


}
