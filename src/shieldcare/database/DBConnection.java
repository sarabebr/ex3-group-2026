package shieldcare.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String DB_PATH =
            System.getProperty("user.dir") + "\\data\\ShieldCare.accdb";

    private static final String URL =
            "jdbc:ucanaccess://" + DB_PATH;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    public static void testConnection() {
        try (Connection conn = getConnection()) {
            System.out.println("Database connected successfully.");
            System.out.println("Database path: " + DB_PATH);
        } catch (SQLException e) {
            System.out.println("Database connection failed.");
            System.out.println(e.getMessage());
        }
    }
}