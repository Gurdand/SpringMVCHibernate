package app.controller.admin;

import app.model.User;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/deleteUser")
public class DeleteUserController {

    private UserService userService;

    @Autowired
    public DeleteUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/admin");
        User user = userService.getUserById(id);
        userService.deleteUser(user);
        return modelAndView;
    }
}
