package spring_demo.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import spring_demo.dto.UserDTO;
import spring_demo.model.User;
import spring_demo.model.enums.ChoseSection;
import spring_demo.service.UserService;

import java.util.List;

import static spring_demo.model.enums.ChoseSection.CREATEUSER;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/hello")
    public String hello(ModelMap modelMap) {
        modelMap.addAttribute("message", "Wooowwwww");
        return "users";
    }

    @GetMapping
    public String getAllUsers(ModelMap modelMap) {
        List<User> users = userService.getAllUsers();
        modelMap.addAttribute("users", users);
        return "userList";
    }

    @PostMapping("/choseSection")
    public String addUser(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users/users";
    }

//    @GetMapping("/choseSection")
//    public String createNewUser() {
//        return "createUser";
//    }

    @PostMapping("/createUser")
    public String save(@ModelAttribute User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute User user, int id) {
        User updatedUser = userService.update(user, id);
        userService.save(updatedUser);
        return "redirect:/users";
    }

//    @PostMapping("/chosedPlace")
//    public String chosedFromSections(ModelMap modelMap){
//
//                ChoseSection choseSection =
//        switch (ChoseSection){
//            case CREATEUSER -> modelMap.addAttribute("createUser", createUser)
//            case    GETUSERSLIST,
//            case    HRPAGE,
//            case    CALLPAGE
//        }
//
//    }

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
        return "redirect:/users";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute User user, ModelMap modelMap) {
//        if (user.getUsername().equals("1") && user.getPassword().equals("1")){
//            modelMap.addAttribute("user", user);
            return "redirect:/users/choseSection";
//        } else {
//            modelMap.addAttribute("error", "Invalid username or password");
//        }
//        return "login";
    }

    @GetMapping("/home")
    public String showHomePage(){
        return "home";
    }
//    @GetMapping("/hr/home")
//    public String showHomePage(){
//        return "hr/home";
//    }
}
