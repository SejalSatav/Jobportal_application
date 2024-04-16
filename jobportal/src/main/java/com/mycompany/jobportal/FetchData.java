/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jobportal;

/**
 *
 * @author hp
 */
import com.mycompany.jobportal.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class FetchData {
    public static void main(String[] args) {
        // Setting up Hibernate SessionFactory
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        try ( // Obtaining session and opening transaction
                Session session = factory.openSession()) {
            // Fetching the user with username 'admin'
            User user = session.createQuery("FROM User WHERE username = 'employer'", User.class).uniqueResult();
            System.out.println("Username: " + user.getUsername() + ", Password: " + user.getPassword());

        } finally {
            factory.close();
        }
    }
}
