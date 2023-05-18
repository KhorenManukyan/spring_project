package spring_demo.controller;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import spring_demo.dto.AvailabilityDTO;
import spring_demo.dto.CheckCallCenterUserDTO;
import spring_demo.dto.CreateCallCenterUserDTO;
import spring_demo.model.CallCenterUser;
import spring_demo.model.User;
import spring_demo.model.enums.Category;
import spring_demo.service.CallCenterUserService;
import spring_demo.service.UserService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/call")
@RequiredArgsConstructor
public class CallCenterUserController {
    private final UserService userService;
    private final CallCenterUserService callCenterUserService;

    @GetMapping("/createCall")
    public String createCall(ModelMap modelMap) {
        List<User> users = userService.getAllUsers();
        modelMap.addAttribute("users", users);
        return "create-call";
    }

    @GetMapping("/getAll")
    public String getAll(ModelMap modelMap) {
        List<CallCenterUser> all = callCenterUserService.findAll();
        modelMap.addAttribute("calls", all);
        return "viewAll";
    }

    @PostMapping("/getAll")
    public String submitForm(@RequestParam("date") @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date,
                             @RequestParam("time") @DateTimeFormat(pattern = "HH:mm") LocalTime time,
                             @RequestParam("userId") String userId) {
        CallCenterUser callCenterUser = new CallCenterUser();
        callCenterUser.setDate(date);
        callCenterUser.setTime(time);
        callCenterUser.setUser(userService.getUserById(Integer.parseInt(userId)));
        callCenterUserService.save(callCenterUser);

        return "redirect:/call/getAll";
    }

    @PostMapping("/check")
    public String checkUserAvailability(@ModelAttribute CheckCallCenterUserDTO checkCallCenterUserDTO, ModelMap modelMap) {
        LocalTime startTime = LocalTime.of(17, 0);
        LocalTime endTime = LocalTime.of(2, 0);
        Duration duration = Duration.ofMinutes(30);
        LocalTime currentTime = startTime;
        List<AvailabilityDTO> availabilities = new LinkedList<>();

        while (!currentTime.equals(endTime)) {
            int percent = (int) getPercent(checkCallCenterUserDTO.getDate(), currentTime);
            availabilities.add(new AvailabilityDTO(currentTime, percent));
            currentTime = currentTime.plus(duration);
        }
        modelMap.addAttribute("availabilities", availabilities);
        return "viewAll";
    }

    @PostMapping("/save")
    @Transactional
    public String save(@ModelAttribute CreateCallCenterUserDTO createCallCenterUserDTO) {
        String userName = createCallCenterUserDTO.getName();
        User user = userService.findByName(userName).orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (user.getCategory().name().equals("SALES")) {
            CallCenterUser call = new CallCenterUser();
            call.setName(userName);
            call.setDate(createCallCenterUserDTO.getDate());
            call.setTime(createCallCenterUserDTO.getTime());
            call.setUser(user);
            callCenterUserService.save(call);
        }
        return "redirect:/call/getAll";
    }

    @PostMapping("/updateCall")
    public String updateUser(@ModelAttribute CallCenterUser callCenterUser, int id) {
        CallCenterUser updatedUser = callCenterUserService.edit(callCenterUser, id);
        callCenterUserService.save(updatedUser);
        return "redirect:/call/getAll";
    }

    @GetMapping("/edit/{id}")
    public String edit(ModelMap modelMap, @PathVariable int id) {
        Optional<CallCenterUser> callCenterUser = callCenterUserService.getUserById(id);
        if (callCenterUser.isPresent()) {
            modelMap.addAttribute("callCenterUser", callCenterUser);
            return "edit-call";
        }
        return "redirect:/viewAll";
    }

    @GetMapping("/checkAvailability")
    public String checkUserAvailability() {
        return "redirect:/call/getAll";

    }

    private double getPercent(LocalDate date, LocalTime time) {
        List<User> allByCategory = userService.findAllByCategory(Category.SALES);
        double max = allByCategory.size();
        double busy = 0;
        List<CallCenterUser> allByLocalDate = callCenterUserService.findAllByLocalDate(date);
        for (CallCenterUser callCenterUser : allByLocalDate) {
            if (callCenterUser.getTime().equals(time)) {
                busy++;
            }
        }

        if (busy > 0) {
            return 100 - (busy / max) * 100;
        }
        return 100;
    }
}
