package com.learning.user;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public String saveUser(User user) {
        return userService.saveUser(user);
    }

    @PutMapping
    public String updateUser(User user) {
        return userService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
