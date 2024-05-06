package com.jobportal.dao;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import com.jobportal.pojo.Job;
import com.jobportal.pojo.User;

@Repository
public class JobSeekerDao {

    public List<Job> getAllJobs() {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Query<Job> query = session.createQuery("FROM Job", Job.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void applyToJob(User jobSeeker, Job job) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            session.beginTransaction();
            jobSeeker.getAppliedJobs().add(job);
            session.update(jobSeeker);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

