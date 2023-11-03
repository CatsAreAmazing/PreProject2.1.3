package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Util {
    private static final String USER_NAME = "Dima";
    private static final String PASSWORD = "96779628";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static Statement statement;

    private static Connection connection;

    public static Connection getConnection(){
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
            return connection;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
    public static void closeConnection() throws SQLException {
        connection.close();
    }
}
