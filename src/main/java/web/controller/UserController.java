package web.controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import service.UserService;

import java.util.List;


@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String home() {
        return "redirect:/users";
    }

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        createTestUser();

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "user";
    }

    private void createTestUser() {
        if (userService.getAllUsers().isEmpty()) {
            User testUser = new User();
            testUser.setName("Punya");
            testUser.setEmail("dota@mail.com");
            testUser.setAge(5);

            userService.saveUser(testUser);
            System.out.println("User created successfully");
        }
    }
}
