package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        UserService test = new UserServiceImpl();
        test.createUsersTable();
        test.saveUser("Раз", "Разраз", (byte) 10);
        test.saveUser("Два", "Двадва", (byte) 11);
        test.saveUser("Три", "Тритри", (byte) 12);
        test.saveUser("Четыре", "кхм", (byte) 13);
        test.getAllUsers();
        test.cleanUsersTable();
        test.getAllUsers();//Надо же проверить
        test.dropUsersTable();
        Util.closeConnection();
    }
}
