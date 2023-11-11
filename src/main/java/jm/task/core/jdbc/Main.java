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
        test.saveUser("Раз", "Два", (byte) 10);
        test.saveUser("Раз", "Два", (byte) 11);
        test.saveUser("Раз", "Два", (byte) 12);
        test.saveUser("Раз", "Два", (byte) 13);
        test.getAllUsers();
        test.removeUserById(1);
        test.getAllUsers();
        test.cleanUsersTable();
        test.getAllUsers();//Надо же проверить
        test.dropUsersTable();
        //как я понял из постов на стаковерфлоу, SessionFactory закрывать не надо
        //https://stackoverflow.com/questions/30096416/where-to-open-and-where-to-close-sessionfactory-in-my-application
    }
}
