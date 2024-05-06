package com.jobportal.dao;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

import com.jobportal.pojo.Job;
import com.jobportal.pojo.JobApplication;
import com.jobportal.pojo.User;

public class DAO {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                registry = new StandardServiceRegistryBuilder()
                        .applySetting(Environment.DRIVER, "com.mysql.cj.jdbc.Driver")
                        .applySetting(Environment.URL, "jdbc:mysql://localhost:3306/jobportal")
                        .applySetting(Environment.USER, "root")
                        .applySetting(Environment.PASS, "SejalSatav2001@123")
                        .applySetting(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect")
                        .applySetting(Environment.SHOW_SQL, "true")
                        .applySetting(Environment.FORMAT_SQL, "true")
                        .applySetting(Environment.USE_SQL_COMMENTS, "true")
                        .applySetting(Environment.HBM2DDL_AUTO, "update")
                        .build();

                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(Job.class);
                sources.addAnnotatedClass(User.class);
                sources.addAnnotatedClass(JobApplication.class);

                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }
        return sessionFactory;
    }
}
