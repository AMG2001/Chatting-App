package gov.iti.jets.services.database;

import gov.iti.jets.persistence.PropertiesFileUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalDatabaseServices {
    private static Connection conn;

    public static Connection getConnectionObj() {
        return conn;
    }

    public static void initConnection() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/telegraphdb", "root", "1234");
            conn.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("❌❌❌ Error While connecting to local Database - class : LocalDatabaseServices , function : initConnection");
            e.printStackTrace();
        }
    }
}
