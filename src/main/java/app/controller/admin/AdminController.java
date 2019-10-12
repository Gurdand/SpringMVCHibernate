package app.controller.admin;

import app.model.Role;
import app.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import app.service.UserService;
import java.util.*;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView usersPage() {
        List<User> users = userService.getAllUsers();
        List<Role> roles = userService.getAllRole();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("adminPage");
        modelAndView.addObject("rolesList", roles);
        modelAndView.addObject("usersList", users);
        return modelAndView;
    }

    @PostMapping(path = "/createUser")
    public ModelAndView createUser(@ModelAttribute("user") User user ) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.addUser(parseRoles(user)); //// Parse Roles
        return modelAndView;
    }

    @GetMapping(path = "/deleteUser/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return modelAndView;
    }

    @GetMapping(path = "/updateUser/{id}")
    public ModelAndView updateUserPage(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("updatePage");
        List<Role> roles = userService.getAllRole();
        User user = userService.getUserById(id);
        modelAndView.addObject("rolesList", roles);
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(path = "/updateUser")
    public ModelAndView updateUser(@ModelAttribute("user") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        userService.updateUser(parseRoles(user)); //// Parse Roles
        return modelAndView;
    }

    private User parseRoles(User user) {
        for (Role role : user.getRoles()) {
            String[] parseData = role.getRoleName().split("_");
            role.setRoleId(Long.parseLong(parseData[0]));
            role.setRoleName(parseData[1]);
        }
        return user;
    }

}
