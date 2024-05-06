package com.jobportal.dao;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jobportal.pojo.User;

@Repository
public class RegisterDao {
    
    public void save(User user) {
        System.out.println("Register DAO reached");
        Session session = DAO.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist(user);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
