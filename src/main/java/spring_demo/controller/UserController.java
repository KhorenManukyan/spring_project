//package spring_demo.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.*;
//import spring_demo.model.User;
//import spring_demo.service.UserService;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/users")
//@RequiredArgsConstructor
//public class UserController {
//
//    private final UserService userService;
//
//    @GetMapping("/hello")
//    public String hello(ModelMap modelMap) {
//        modelMap.addAttribute("message", "Wooowwwww");
//        return "users";
//    }
//
//    @GetMapping
//    public String getAllUsers(ModelMap modelMap) {
//        List<User> users = userService.getAllUsers();
//        modelMap.addAttribute("users", users);
//        return "userList";
//    }
//
//    @GetMapping("/save")
//    public String addUser() {
//        return "users";
//    }
//
//    @PostMapping
//    public String save(@ModelAttribute User user) {
//        userService.save(user);
//        return "redirect:/users";
//    }
//
//    @PostMapping("/updateUser")
//    public String updateUser(@ModelAttribute User user, int id) {
//        User updatedUser = userService.update(user, id);
//        userService.save(updatedUser);
//        return "redirect:/users";
//    }
//
//    @GetMapping("/edit/{id}")
//    public String edite(ModelMap modelMap, @PathVariable int id) {
//        User user = userService.getUserById(id);
//        if (user != null) {
//            modelMap.addAttribute("user", user);
//            return "editUser";
//        }
//
//        return "redirect:/users";
//    }
//
//    @GetMapping("/{id}")
//    public String getUserById(@PathVariable int id, ModelMap modelMap) {
//        User user = userService.getUserById(id);
//        modelMap.addAttribute("user", user);
//        return "user";
//    }
//
//    @GetMapping("delete/{id}")
//    public String deleteUserById(@PathVariable int id, ModelMap modelMap) {
//        userService.deleteUserById(id);
//        modelMap.addAttribute("users", userService.getAllUsers());
//        modelMap.addAttribute("success", true);
//
//        return "userList";
//    }
//
//    @GetMapping("/deleteAll")
//    public String deleteAllUsers() {
//        userService.deleteAllUsers();
//        return "redirect:/users";
//    }
//}
