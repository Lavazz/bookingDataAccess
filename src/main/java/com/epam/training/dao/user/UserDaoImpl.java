package com.epam.training.dao.user;

import com.epam.training.config.HibernateConfig;
import com.epam.training.dao.DAOException;
import com.epam.training.model.user.User;
import com.epam.training.storage.user.UserStorage;
import com.epam.training.storage.user.UserStorageImpl;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
public class UserDaoImpl implements UserDao {

    private final UserStorage userStorage;

    @Autowired
    public UserDaoImpl(UserStorageImpl userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public User getUserById(long userId) {
        return userStorage.getUserById(userId);
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        session.beginTransaction();
//       User result = (User) session.get(typeParameterClass, id);
//        session.getTransaction().commit();
//        if (session.isOpen()) {
//            session.close();
//        }
//        return result;
    }

    @Override
    public User createUser(User user) {
        return userStorage.createUser(user);
    }

    @Override
    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    @Override
    public boolean deleteUser(long userId) {
        return userStorage.deleteUser(userId);
    }

    @Override
    public List<User> findAll() {
       // return userStorage.findAll();
        Transaction transaction = null;
        List<User> users;

        try (Session session = HibernateConfig.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Query<User> query = session.createQuery("from User", User.class);

            users = query.getResultList();

            transaction.commit();

        } catch (Exception e) {
            if (Objects.nonNull(transaction)) {
                transaction.rollback();
            }
            throw new DAOException(e.getMessage());
        }

        return users;
    }

}
