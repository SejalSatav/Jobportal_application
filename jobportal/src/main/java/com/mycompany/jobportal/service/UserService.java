package com.mycompany.jobportal.service;

import com.mycompany.jobportal.dao.UserDao;
import com.mycompany.jobportal.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt the password
        userDao.save(user);
    }

    public User findUserByUsername(String username) {
        return userDao.findByUsername(username);
    }
}