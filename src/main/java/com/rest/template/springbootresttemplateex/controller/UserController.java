package com.rest.template.springbootresttemplateex.controller;


import com.rest.template.springbootresttemplateex.model.User;
import com.rest.template.springbootresttemplateex.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController<T> {

    @Autowired
    private UserService userService;

    @GetMapping("/getAllUsers")
    public List<User> getAllProducts(){
        return userService.getAllUsers();
    }

    @PostMapping("/api/users")
    public User createUser(@RequestBody User user){
      return userService.createUser(user);
    }

    @PutMapping("/api/users/{id}")
    public User updateUser(@PathVariable("id") Long id){
        return userService.updateUserById(id);
    }

    @DeleteMapping("/api/users/{id}")
    public User delete(@PathVariable("id") Long id){
        return userService.deleteUserById(id);
    }

}
