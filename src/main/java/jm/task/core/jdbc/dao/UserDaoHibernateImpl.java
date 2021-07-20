package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        Session session = Util.getInstanceSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS user (" +
                "id INTEGER not NULL AUTO_INCREMENT, " +
                "name VARCHAR(50), " +
                "lastName VARCHAR (50),  " +
                "age INTEGER, PRIMARY KEY (id))";
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        Session session = Util.getInstanceSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS user";
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getInstanceSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        User user =  new User(name, lastName, age);
        session.save(user);
        transaction.commit();
        System.out.println(" User с именем – " + user.getName() + " добавлен в базу данных");
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getInstanceSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        if (session.get(User.class, id) != null) {
            session.delete(session.get(User.class, id));
        }
        transaction.commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = Util.getInstanceSessionFactory().openSession();
        Query query = session.createQuery("FROM User");
        List<User> users = query.list();
        session.close();

        return users;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getInstanceSessionFactory().openSession();
        Transaction t1 = session.beginTransaction();
        session.createQuery("delete from User").executeUpdate();
        t1.commit();
        session.close();
    }
}
