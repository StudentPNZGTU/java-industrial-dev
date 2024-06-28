package org.penzgtu.Application.controller;

import org.penzgtu.Application.models.user.User;
import org.penzgtu.Application.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public User saveUser(@Valid @RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/fetch")
    public List<User> fetchAndSaveProducts() {
        return userService.fetchAndSaveUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable Long id) {
       userService.deleteUserById(id);
    }



}