package spring_demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import spring_demo.dto.UserRegDTO;
import spring_demo.model.User;
import spring_demo.model.enums.Category;
import spring_demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/hello")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("message", "Wooowwwww");
        return "create-user";
    }

    @GetMapping("/getUsersList")
    public String getAllUsers(ModelMap modelMap) {
        List<User> users = userService.getAllUsers();
        modelMap.addAttribute("users", users);
        return "userList";
    }

    @PostMapping("/createUser")
    public String save(@ModelAttribute UserRegDTO userRegDTO) {
        User user = new User();
        user.setName(userRegDTO.getName());
        user.setPassword(userRegDTO.getPassword());
        String upperCase = userRegDTO.getCategory().toUpperCase();
        System.err.println(upperCase);
        user.setCategory(Category.valueOf(upperCase));
        userService.save(user);
        return "redirect:/users/getUsersList";
    }

    @GetMapping("/createUser")
    public String showCreateUserPage() {
        return "create-user";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, int id) {
        User updatedUser = userService.update(user, id);
        userService.save(updatedUser);
        return "redirect:/users/getUsersList";
    }

    @GetMapping("/edit/{id}")
    public String edite(ModelMap modelMap, @PathVariable int id) {
        User user = userService.getUserById(id);
        if (user != null) {
            modelMap.addAttribute("user", user);
            return "editUser";
        }

        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, ModelMap modelMap) {
        User user = userService.getUserById(id);
        modelMap.addAttribute("user", user);
        return "user";
    }

    @GetMapping("delete/{id}")
    public String deleteUserById(@PathVariable int id, ModelMap modelMap) {
        userService.deleteUserById(id);
        modelMap.addAttribute("users", userService.getAllUsers());
        modelMap.addAttribute("success", true);

        return "userList";
    }

    @GetMapping("/deleteAll")
    public String deleteAllUsers() {
        userService.deleteAllUsers();
        return "redirect:/users/getUsersList";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute User user, ModelMap modelMap) {
        if (user.getName().equals("1") && user.getPassword().equals("1")) {
            modelMap.addAttribute("user", user);
            return "redirect:/users/choseSection";
        } else {
            modelMap.addAttribute("error", "Invalid username or password");
        }
        return "login";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }

    @GetMapping("/choseSection")
    public String createNewUser() {
        return "choseSection";
    }

    @GetMapping("/callPage")
    public String showCallCenterPage() {
        return "call-center";
    }

    @GetMapping("/logout")
    public String exit() {
        return "login";
    }
}
