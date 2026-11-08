package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Satu tempat untuk konfigurasi dan pembuatan koneksi database.
 */
public final class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/database hazzelnut";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    private DatabaseConnection() {
        
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
