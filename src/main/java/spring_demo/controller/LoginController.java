package spring_demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import spring_demo.dto.LoginUserDTO;
import spring_demo.model.UserRegister;

@Controller
@RequiredArgsConstructor
public class LoginController {
    @GetMapping("/login")
    public String showLoginPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new LoginUserDTO());
        return "login";
    }

    @PostMapping("/login")
    public String submitLoginForm(@ModelAttribute LoginUserDTO loginUserDTO){
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }
}
