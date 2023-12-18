
package com.hampusborg.demo.database;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DatabaseConnector {

    private static final String URL = "jdbc:mariadb://localhost:3306/Dungeon_Run";
    private static final String USER = "admin";
    private static final String PASSWORD = "Hampira2023!";

    private static Connection connection;
    public DatabaseConnector() {
        openConnection();
    }


    public static void openConnection() {
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connected to the database");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to connect to the database");
        }
    }

    public static Connection getConnection() {
        try {

            if (connection != null && !connection.isClosed()) {
                return connection;
            } else {
                openConnection();
                return connection;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to check the connection");
        }
    }
}
