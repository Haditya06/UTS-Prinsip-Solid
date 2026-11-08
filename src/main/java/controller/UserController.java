package controller;

import java.util.List;
import modul.UserItem;
import repository.JdbcUserRepository;
import service.UserService;
import service.UserServiceImpl;
import validation.UserValidator;


public class UserController {
    private final UserService service;

    public UserController() {
        this(new UserServiceImpl(new JdbcUserRepository(), new UserValidator()));
    }

    public UserController(UserService service) {
        if (service == null) throw new IllegalArgumentException("UserService tidak boleh null.");
        this.service = service;
    }

    public List<UserItem> getAllUser() { return service.getAllUser(); }
    public List<UserItem> searchUser(String keyword) { return service.searchUser(keyword); }
    public int getIdByUsername(String username) { return service.getIdByUsername(username); }
    public void addUser(UserItem user) { service.addUser(user); }
    public void updateUser(UserItem user, String newPassword) { service.updateUser(user, newPassword); }
    public void deleteUser(int id) { service.deleteUser(id); }
}
