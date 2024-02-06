package gov.iti.jets.Persistence.mysql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public enum DBConnectionPool {
    DATASOURCE;
    private final HikariDataSource dataSource;

    DBConnectionPool() {
        HikariConfig config = new HikariConfig("/PropertyFiles/DBprop.properties");
        dataSource = new HikariDataSource(config);
    }


    public Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

    public void close() {
        dataSource.close();
    }
}
