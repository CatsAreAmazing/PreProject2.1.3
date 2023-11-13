package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Util {
    private static final String USER_NAME = "Dima";
    private static final String PASSWORD = "96779628";
    private static final String URL = "jdbc:mysql://localhost:3306/mysql";
    private static Statement statement;

    private static Connection connection;


    public static Configuration configue() {
        try {
            Configuration configuration = new Configuration();

            Properties settings = new Properties();
            settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
            settings.put(Environment.URL, URL);
            settings.put(Environment.USER, USER_NAME);
            settings.put(Environment.PASS, PASSWORD);
            settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

            settings.put(Environment.SHOW_SQL, "true");

            settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            settings.put(Environment.HBM2DDL_AUTO, "create-drop");

            configuration.setProperties(settings);

            configuration.addAnnotatedClass(User.class);

            return configuration;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public static Connection getConnection() {
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
