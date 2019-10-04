package app.service;

import app.model.Role;
import app.model.User;

import java.util.List;
import java.util.Set;

public interface UserService {

    List<User> getAllUsers();

    void addUser(User user);

    void updateUser(User user);

    void deleteUser(User user);

    User getUserById(Long id);

    User getUserByLogin(String login);

    List<Role> getAllRole();
}
