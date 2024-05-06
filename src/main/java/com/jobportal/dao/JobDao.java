package com.jobportal.dao;

import com.jobportal.pojo.Job;
import com.jobportal.pojo.JobApplication;
import com.jobportal.pojo.User;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class JobDao {

    public void saveJob(Job job) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(job);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Job getJobById(int jobId) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Query<Job> query = session.createQuery("FROM Job WHERE id = :jobId", Job.class);
            query.setParameter("jobId", jobId);

            Job job = query.uniqueResult();
            if (job != null) {
                Hibernate.initialize(job.getJobApplications());
            }
            return job;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public void updateJob(Job job) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.update(job);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Job> getJobsByEmployer(User employer) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Query<Job> query = session.createQuery("FROM Job WHERE employer = :employer", Job.class);
            query.setParameter("employer", employer);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteJob(int jobId) {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Job job = session.get(Job.class, jobId);
            if (job != null) {
                session.beginTransaction();
                session.delete(job);
                session.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Get all jobs
    public List<Job> getAllJobs() {
        try (Session session = DAO.getSessionFactory().openSession()) {
            Query<Job> query = session.createQuery("FROM Job", Job.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
