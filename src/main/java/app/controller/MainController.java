package app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/**")
public class MainController {

    @RequestMapping
    public String allRequests() {
        return "redirect:/";
    }
}
