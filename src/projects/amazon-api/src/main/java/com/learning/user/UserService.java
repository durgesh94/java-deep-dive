package com.learning.user;

public class UserService {
    public String getAllUsers() {
        return "GetAllUsers";
    }

    public String getUserById(Long id) {
        return "getUserById" + id;
    }

    public String saveUser(User user) {
        return "SaveUser";
    }

    public String deleteUser(Long id) {
        return "deleteUser" + id;
    }
}
