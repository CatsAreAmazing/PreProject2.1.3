package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private static SessionFactory sessionFactory;

    public UserDaoHibernateImpl() {
        Configuration configuration = Util.configue();
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    //Методы создания и удаления таблицы пользователей в классе UserHibernateDaoImpl должны быть реализованы с помощью SQL.
    @Override
    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String sql = "CREATE TABLE IF NOT EXISTS Users (" +
                    "Id INT auto_increment primary key," +
                    "name nvarchar(30) not null," +
                    "lastName nvarchar(30) not null," +
                    "age TINYINT UNSIGNED)";

            Query query = session.createNativeQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }

    }

    @Override
    public void dropUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String sql = "DROP TABLE IF EXISTS users";
            Query query = session.createNativeQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            session.save(user);
            transaction.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            List<User> users = session.createCriteria(User.class).list();
            transaction.commit();
            if (users.isEmpty()) {
                System.out.println("Таблица пуста. Очень пуста. Совсем пуста. Тут абсолютно ничего нет");
            } else {
                System.out.println(users);
            }
            return users;
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
            return null;
        }
        finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try{
            String sql = "TRUNCATE Users";
            Query query = session.createNativeQuery(sql).addEntity(User.class);
            query.executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            session.close();
        }
    }
}
