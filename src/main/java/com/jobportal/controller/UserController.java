package com.jobportal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import com.jobportal.dao.RegisterDao;
import com.jobportal.dao.UserDao;
import com.jobportal.dao.JobSeekerDao;
import com.jobportal.pojo.Job;
import com.jobportal.pojo.User;
import com.jobportal.pojo.UserRole;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@SessionAttributes("authenticatedUser")
public class UserController {

    @Autowired
    private RegisterDao registerDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private JobSeekerDao jobSeekerDao;

    // Display the registration form
    @GetMapping("/register.htm")
    public String showRegistrationForm() {
        System.out.println("Navigating to user registration form.");
        return "add-user";
    }

    // Process the registration form
    @PostMapping("/usersave.htm")
    public ModelAndView processRegistration(@ModelAttribute("newUser") User user) {
        System.out.println("Processing user registration.");
        registerDao.save(user);
        return new ModelAndView("register-success", "user", user);
    }

    // Display the login form
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String handleLogin(@ModelAttribute User user, Model model, HttpServletRequest request, HttpSession session) {
        User authenticatedUser = userDao.checkLogin(user.getEmail(), user.getPassword());
        if (authenticatedUser != null) {
            session.invalidate(); // Invalidate the previous session
            session = request.getSession(true); // Create a new session
            session.setAttribute("authenticatedUser", authenticatedUser);
            switch (authenticatedUser.getUserRole()) {
                case JOB_SEEKER:
                    return "redirect:/jobseeker-dashboard";
                case EMPLOYER:
                    return "redirect:/employer-dashboard";
                default:
                    model.addAttribute("error", "Invalid credentials");
                    return "login";
            }
        } else {
            model.addAttribute("error", "Invalid credentials");
            return "login";
        }
    }

    // Job seeker dashboard
    @GetMapping("/jobseeker-dashboard")
    public String jobSeekerDashboard(@SessionAttribute("authenticatedUser") User jobSeeker, Model model) {
        List<Job> allJobs = jobSeekerDao.getAllJobs();
        List<Job> appliedJobs = jobSeeker.getAppliedJobs();

        // Create a new list for jobs that the job seeker hasn't applied for yet
        List<Job> availableJobs = allJobs.stream()
                                          .filter(job -> !appliedJobs.contains(job))
                                          .collect(Collectors.toList());

        model.addAttribute("availableJobs", availableJobs);
        model.addAttribute("appliedJobs", appliedJobs);

        return "jobseeker-dashboard";
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
