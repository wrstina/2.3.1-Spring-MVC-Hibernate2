package web.controller;

import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import javax.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private static final String REDIRECT_TO_USERS = "redirect:/users";
    private static final String USERS_VIEW = "users";
    private static final String USERS_ATTRIBUTE = "users";
    private static final String USER_ATTRIBUTE = "user";
    private static final String IS_EDIT_ATTRIBUTE = "isEdit";

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute(USERS_ATTRIBUTE, userService.getAllUsers());
        if (!model.containsAttribute(USER_ATTRIBUTE)) {
            model.addAttribute(USER_ATTRIBUTE, new User());
        }
        if (!model.containsAttribute(IS_EDIT_ATTRIBUTE)) {
            model.addAttribute(IS_EDIT_ATTRIBUTE, false);
        }
        return USERS_VIEW;
    }

    @GetMapping("/add-user")
    public String showAddUserForm(Model model) {
        model.addAttribute(USER_ATTRIBUTE, new User());
        model.addAttribute(IS_EDIT_ATTRIBUTE, false);
        model.addAttribute(USERS_ATTRIBUTE, userService.getAllUsers());
        logger.info("New user created");
        return USERS_VIEW;
    }

    @PostMapping("/save-user")
    public String saveUser(@Valid User user,
                           BindingResult bindingResult,
                           Model model) {
        userService.saveUser(user);
        logger.info("User saved: {}", user.getName());
        if (bindingResult.hasErrors()) {
            logger.warn("Validation errors found while saving user: {}", bindingResult.getAllErrors());
            model.addAttribute(USERS_ATTRIBUTE, userService.getAllUsers());
            model.addAttribute(IS_EDIT_ATTRIBUTE, false);
            return USERS_VIEW;
        }
        return REDIRECT_TO_USERS;
    }

    @GetMapping("/edit-user")
    public String showEditUserForm(@RequestParam long id, Model model) {
        User user = userService.findUserById(id);
        model.addAttribute(USER_ATTRIBUTE, user);
        model.addAttribute(USERS_ATTRIBUTE, userService.getAllUsers());
        model.addAttribute(IS_EDIT_ATTRIBUTE, true);
        logger.info("Editing user: {}", user.getName());
        return USERS_VIEW;
    }

    @PostMapping("/update-user")
    public String updateUser(@Valid User user,
                             BindingResult bindingResult,
                             Model model) {
        logger.info("Attempting to update user: {} (ID: {})", user.getName(), user.getId());
        if (bindingResult.hasErrors()) {
            model.addAttribute(USERS_ATTRIBUTE, userService.getAllUsers());
            model.addAttribute(IS_EDIT_ATTRIBUTE, true);
            return USERS_VIEW;
        }
        try {
            userService.updateUser(user);
            logger.info("User updated successfully: {} (ID: {})", user.getName(), user.getId());
        } catch (Exception e) {
            logger.error("Error updating user: {}", e.getMessage());
            bindingResult.reject("error.user", "User wasn't updated: " + e.getMessage());
            model.addAttribute(USERS_ATTRIBUTE, userService.getAllUsers());
            model.addAttribute(IS_EDIT_ATTRIBUTE, true);
            return USERS_VIEW;
        }
        return REDIRECT_TO_USERS;
    }

    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam long id) {
        userService.deleteUserById(id);
        try {
            logger.info("User deleted with id: {}", id);
        } catch (Exception e) {
            logger.error("Error deleting user with id {}: {}", id, e.getMessage());

        }
        return REDIRECT_TO_USERS;
    }
}

    /*@GetMapping("/")
    public String home() {
        return "redirect:/users";
    }

    @GetMapping("/user")
    public String getAllUsers(Model model) {
        createTestUser();

        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return USERS_VIEW;
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
    }*/

