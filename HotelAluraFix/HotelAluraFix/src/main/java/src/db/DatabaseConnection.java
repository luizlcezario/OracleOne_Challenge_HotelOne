package src.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseConnection {

    static private final String user = "admin123";
    static private final String pass = "example123";
    static private final String url = "jdbc:postgresql://localhost:5432/HotelAlura";

    static private Connection coon;
    static final DatabaseConnection instance = new DatabaseConnection();
    public Connection getConnection() {
        return coon;
    }

    public static DatabaseConnection getInstance() {
        return instance;
    }
    private DatabaseConnection() {
        try {
            coon = DriverManager.getConnection(url, user, pass);
            System.out.println("Connected to the PostgreSQL server successfully.");
        }  catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
