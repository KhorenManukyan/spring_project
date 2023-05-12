package spring_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegistrationController {

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "registration";
    }

    @GetMapping("/hr-page")
    public String hrPage() {
        return "hr";
    }
    @GetMapping("/worker-page")
    public String workerPage() {
        return "worker";
    }
    @GetMapping("/call-page")
    public String callPage() {
        return "call";
    }

}

