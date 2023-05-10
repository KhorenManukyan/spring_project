package spring_demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import spring_demo.model.User;
import spring_demo.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public void save(@RequestBody User user) {
        userService.save(user);
    }

    @PutMapping("/{id}")
    public void update(@RequestBody User user, @PathVariable int id) {
        userService.update(user, id);
    }


    @GetMapping("/byid/{id}")
    public User getById(@PathVariable int id) {
        return userService.getById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public User deleteById(@PathVariable int id) {
        return userService.deleteById(id);
    }

    @DeleteMapping("/delete")
    public void deleteAll() {
        userService.deleteAll();
    }
}
