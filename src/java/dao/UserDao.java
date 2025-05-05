/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import util.HibernateUtil;

/**
 *
 * @author hamza
 */
public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }

    @Override
    public boolean create(User user) {
        user.setPassword(hashPassword(user.getPassword()));
        return super.create(user);
    }

    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    public User findByEmail(String email) {
        Session session = null;
        Transaction tx = null;
        User user = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            tx = session.beginTransaction();
            user = (User) session.createQuery("FROM User u WHERE u.email = :email")
                    .setParameter("email", email)
                    .uniqueResult();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return user;
    }

}