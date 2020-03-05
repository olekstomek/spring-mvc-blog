package blog.controllers;

import blog.services.UserServiceJpaImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UsersController {

    @Autowired
    private UserServiceJpaImpl userServiceJpa;

    @RequestMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userServiceJpa.findAll());

        return "users";
    }
}