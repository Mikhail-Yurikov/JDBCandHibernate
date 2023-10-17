package jm.task.core.jdbc.dao;

import com.mysql.cj.Query;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.ResultSet;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS USER(id int not null auto_increment  primary key , name varchar(20), lastname varchar(20), age tinyint)";
        session.createSQLQuery(sql).addEntity(User.class).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void dropUsersTable() {
        String sql = "DROP TABLE IF  EXISTS USER";
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery(sql).executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        User user = new User(name, lastName, age);
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public void removeUserById(long id) {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        User user = session.get(User.class, id);
        session.delete(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> getAllUsers() {
        Session session = getSessionFactory().openSession();
        List<User> list = session.createCriteria(User.class).list();
        session.close();
        return list;
    }

    @Override
    public void cleanUsersTable() {
        Session session = getSessionFactory().openSession();
        session.beginTransaction();
        session.createQuery("delete User ").executeUpdate();
        session.getTransaction().commit();
        session.close();
    }
}
