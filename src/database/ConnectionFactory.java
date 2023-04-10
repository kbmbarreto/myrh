package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory implements AutoCloseable {

    private static final String URL = "jdbc:mysql://localhost:3306/myrh";
    private static final String USER = "root";
    private static final String PASSWORD = "Dev2020@";

    private static Connection connection;

    public ConnectionFactory() throws SQLException {
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
    }

    @Override
    public void close() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}