/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jobportal.dao;

import com.mycompany.jobportal.model.User;

/**
 *
 * @author hp
 */
public interface UserDao {
    void save(User user);
    User findByUsername(String username);
}