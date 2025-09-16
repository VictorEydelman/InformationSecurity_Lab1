package se.ifmo.InfoSec.service;

import jakarta.persistence.NoResultException;
import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.ifmo.InfoSec.entities.User;

import java.util.List;

@Service
public class UserService {
    private final SessionFactory sessionFactory;

    public UserService(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(User user) {
        try(Session session = sessionFactory.openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(user);
            tx.commit();
        }
    }
    public User getByUsernameAndPassword(String username, String password) {
        Session session = sessionFactory.openSession();
        Query<User> queue = session.createQuery("FROM User WHERE username = :username and password = :password",User.class);
        queue.setParameter("username", username);
        queue.setParameter("password", password);
        try {
            return queue.getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }
    public User getByUsername(String username) {
        Session session = sessionFactory.openSession();
        Query<User> queue = session.createQuery("FROM User WHERE username = :username",User.class);
        queue.setParameter("username", username);
        return queue.uniqueResult();
    }
    public List<String> getListUser() {
        try (Session session = sessionFactory.openSession()) {
            Query<String> query = session.createQuery("select u.username from User u", String.class);
            return query.list();
        }
    }
}
