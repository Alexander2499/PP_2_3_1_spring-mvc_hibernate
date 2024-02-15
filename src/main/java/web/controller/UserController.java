package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.model.User;
import web.service.UserServiceImpl;

import java.util.List;

@Controller
public class UserController {
    UserServiceImpl userService = new UserServiceImpl();

    @GetMapping(value = "/allusers")
    public String showAllUsersOnWeb(Model model) {
        List<User> users = userService.showUsers();
        model.addAttribute("users",users);
        return "users";
    }
}
