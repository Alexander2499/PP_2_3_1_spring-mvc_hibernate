package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.jpa.AbstractEntityManagerFactoryBean;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

@Controller
//@RequestMapping("/users")
public class UserController {

    private UserServiceImpl userService = new UserServiceImpl();

//    private EntityManagerFactory entityManagerFactory;
//    EntityManager entityManager = entityManagerFactory.createEntityManager();

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    public String showAllUsersOnWeb(Model model) {
        List<User> usersList = userService.showUsers();
        model.addAttribute("users", usersList);
        return "users";
    }

//    @GetMapping(value = "/delete")
//    public String deleteUserOnWeb(Model model) {
//        List<User> users = userService.showUsers();
//        model.addAttribute("users", users);
//        return "users";
//    }

//    @PostMapping()
//    public String createUser(@RequestParam("name") String name, @RequestParam("lastName") String lastName,
//                             @RequestParam("salary") int salary, Model model) {
//        User user = new User();
//        user.setName(name);
//        user.setLastName(lastName);
//        user.setSalary(salary);
//
//        // Добавление в бд
//
//        model.addAttribute("user",user);
//        return "Дело сделано";
//    }


//    @GetMapping("/new")
//    public String newUser(@ModelAttribute("user") User user) {
//        return "pages/new_user";
//    }

    @GetMapping("/users/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new_user";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/refactor")
    public String refactorUsers(Model model,@PathVariable("id") int id) {
        model.addAttribute("user", userService.findUserById(id));
        return "refactor";
    }

    @PostMapping("/users/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @PostMapping("/users/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }





//    @GetMapping("/{id}/delete")
//    public String


//    @RequestMapping(value = "/refactor", params = {"count"})
//    public String refactorUsers(@RequestParam(value = "count") int count, Model model) {
//        User user = userService.refactorUser(count);
//        model.addAttribute("users", user);
//        return "new_user";
//    }
}
