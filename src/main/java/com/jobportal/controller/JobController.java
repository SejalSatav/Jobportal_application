package com.jobportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.jobportal.dao.JobDao;
import com.jobportal.dao.UserDao;
import com.jobportal.dao.JobSeekerDao;
import com.jobportal.pojo.Job;
import com.jobportal.pojo.JobApplication;
import com.jobportal.pojo.User;

@Controller
@SessionAttributes("authenticatedUser")
public class JobController {

    @Autowired
    private JobDao jobDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JobSeekerDao jobSeekerDao;

    // Display the job list for the employer
    @GetMapping("/employer-dashboard")
    public ModelAndView showJobList(@SessionAttribute("authenticatedUser") User authenticatedUser) {
        List<Job> jobs = jobDao.getJobsByEmployer(authenticatedUser);
        ModelAndView modelAndView = new ModelAndView("employer-dashboard");
        modelAndView.addObject("jobs", jobs);
        return modelAndView;
    }

    // Display the job creation form
    @GetMapping("/add-job.htm")
    public String showJobForm() {
        return "add-job";
    }

    // Process the job creation form
    @PostMapping("/jobsave.htm")
    public String processJobCreation(@ModelAttribute("newJob") Job job, @SessionAttribute("authenticatedUser") User employer) {
        job.setEmployer(employer);
        jobDao.saveJob(job);
        return "redirect:/employer-dashboard";
    }

    @GetMapping("/edit-job/{jobId}")
    public ModelAndView showJobEditForm(@PathVariable("jobId") int jobId) {
        ModelAndView modelAndView = new ModelAndView("edit-job");
        Job job = jobDao.getJobById(jobId);
        modelAndView.addObject("job", job);
        return modelAndView;
    }

    @PostMapping("/update-job")
    public String updateJob(@ModelAttribute("job") Job job, @SessionAttribute("authenticatedUser") User authenticatedUser, Model model) {
        job.setEmployer(authenticatedUser); // Set the employer object in the job
        jobDao.updateJob(job);
        model.addAttribute("jobs", jobDao.getJobsByEmployer(authenticatedUser)); // Pass the authenticatedUser object instead of its ID
        return "redirect:/employer-dashboard";
    }

    @GetMapping("/delete-job/{jobId}")
    public String deleteJob(@PathVariable("jobId") int jobId) {
        jobDao.deleteJob(jobId);
        return "redirect:/employer-dashboard";
    }

    // Job seeker applies for a job
    @GetMapping("/apply-job/{jobId}")
    public ModelAndView showApplyJobPage(@PathVariable int jobId, Model model) {
        Job job = jobDao.getJobById(jobId);
        if (job == null) {
            return new ModelAndView("error", "errorMessage", "Job not found.");
        }
        return new ModelAndView("apply-job", "job", job);
    }

    @PostMapping("/apply-job/{jobId}")
    public String applyForJob(@PathVariable int jobId, @SessionAttribute("authenticatedUser") User jobSeeker) {
        Job job = jobDao.getJobById(jobId);
        if (job == null) {
            return "redirect:/jobseeker-dashboard?error=true";
        }

        JobApplication jobApplication = new JobApplication();
        jobApplication.setJob(job);
        jobApplication.setJobSeeker(jobSeeker);

        job.getJobApplications().add(jobApplication);
        jobSeeker.getAppliedJobs().add(job);

        jobDao.updateJob(job);
        return "redirect:/jobseeker-dashboard?success=true";
    }
}
