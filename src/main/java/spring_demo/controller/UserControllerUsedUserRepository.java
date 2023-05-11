package spring_demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import spring_demo.model.User;
import spring_demo.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserControllerUsedUserRepository {

    private final UserRepository userRepository;

    @GetMapping("/hello")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("message", "Wooowwwww");
        return "users";
    }

    @GetMapping
    public String getAllUsers(ModelMap modelMap) {
        List<User> users = userRepository.findAll();
        modelMap.addAttribute("users", users);
//        modelMap.addAttribute("user", new User());
        return "userList";
    }

    @GetMapping("/save")
    public String addUser() {
        return "users";
    }

    @PostMapping
    public String save(@ModelAttribute User user) {
        userRepository.save(user);
        return "redirect:/users";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, int id) {
        User existingUser = userRepository.findById(user.getId()).orElse(null);
        if (existingUser != null) {
            // update user properties here
            existingUser.setFirstName(user.getFirstName());
            existingUser.setLastName(user.getLastName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            userRepository.save(existingUser);
        }

        return "redirect:/users";
    }

    @GetMapping("/edit/{id}")
    public String edite(ModelMap modelMap, @PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            modelMap.addAttribute("user", user);
            return "editUser";
        }

        return "redirect:/users";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable int id, ModelMap modelMap) {
        Optional<User> byId = userRepository.findById(id);
        byId.ifPresent(user -> modelMap.addAttribute("user", user));

        return "user";
    }

    @GetMapping("delete/{id}")
    public String deleteUserById(@PathVariable int id, ModelMap modelMap) {
        userRepository.deleteById(id);
        modelMap.addAttribute("users", userRepository.findAll());
        modelMap.addAttribute("success", true);

        return "userList";
    }

    @GetMapping("/deleteAll")
    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "redirect:/users";
    }
}
