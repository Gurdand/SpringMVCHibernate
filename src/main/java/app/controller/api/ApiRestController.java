package app.controller.api;

import app.model.User;
import app.model.repository.UserRepository;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api")
public class ApiRestController {

    private UserService userService;

    @Autowired
    public ApiRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUserApi() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "/{id}")
    public User getUser(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @PostMapping
    public void addUser(@ModelAttribute("user") User user) {
        userService.addUser(user);
    }

    @PutMapping
    public void updateUser(@ModelAttribute("user") User user) {
        userService.updateUser(user);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        userService.deleteUser(user);
    }


}
