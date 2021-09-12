package com.rest.template.springbootresttemplateex.service;

import com.rest.template.springbootresttemplateex.model.User;

import java.util.List;

public interface UserService {

    List<User> getAllUsers();

    public User createUser(User user);

    public User updateUserById(Long id);

    public User deleteUserById(Long id);
}
