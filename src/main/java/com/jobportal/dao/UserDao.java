package com.jobportal.dao;



import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.jobportal.pojo.User;

@Repository
public class UserDao {

	public User checkLogin(String email, String password) {
	    try (Session session = DAO.getSessionFactory().openSession()) {
	        Query<User> query = session.createQuery("FROM User WHERE email = :email AND password = :password", User.class);
	        query.setParameter("email", email);
	        query.setParameter("password", password);

	        User user = query.uniqueResult();
	        if (user != null) {
	            Hibernate.initialize(user.getAppliedJobs());
	        }
	        return user;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	}

    public User findById(int id) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            User user = session.get(User.class, id);
            if (user != null) {
                Hibernate.initialize(user.getJobs());
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User getAuthenticatedUser(String email, String password) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Query<User> query = session.createQuery("FROM User u JOIN FETCH u.appliedJobs WHERE u.email = :email AND u.password = :password", User.class);
            query.setParameter("email", email);
            query.setParameter("password", password);

            return query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
