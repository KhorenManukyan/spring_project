package spring_demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import spring_demo.dto.SaveUserRequestDTO;
import spring_demo.model.Permission;
import spring_demo.model.enums.PermissionType;
import spring_demo.model.UserRegister;
import spring_demo.service.PermissionService;
import spring_demo.service.UserRegisterService;

import java.util.List;

@Controller
@RequestMapping("/users-register")
@RequiredArgsConstructor
public class UserRegistryController {
    private final UserRegisterService userRegisterService;

    private final PermissionService permissionService;

    @GetMapping
    public String homePageRegisterPage() {
        return "registration";
    }

    @GetMapping("/first-page")
    public String firstPage(ModelMap modelMap) {
        modelMap.addAttribute("message", "first page started");
        return "user";
    }

    @GetMapping("/get-users")
    public String getAllUsers(ModelMap modelMap) {
        List<UserRegister> users = userRegisterService.getAllUsers();
        modelMap.addAttribute("users", users);
        return "allRegisteredUserList";
    }

    @GetMapping("/save")
    public String addingUser() {
        return "registration";
    }

    @PostMapping
    public String save(@ModelAttribute SaveUserRequestDTO saveUserRequestDTO) {
        UserRegister userRegister = new UserRegister();
        userRegister.setPassword(saveUserRequestDTO.getPassword());
        userRegister.setName(saveUserRequestDTO.getName());
        userRegisterService.save(userRegister);
        if (saveUserRequestDTO.getPermissions() != null) {
            for (String permission : saveUserRequestDTO.getPermissions()) {
                if (permission != null) {
                    String perm = permission.toUpperCase();
                    Permission savePermission = new Permission();
                    savePermission.setPermissionType(PermissionType.valueOf(perm));
                    savePermission.setUserRegister(userRegister);
                    permissionService.save(savePermission);
                }

            }
        }
        return "redirect:/get-users";
    }

//    @GetMapping("/submit-form")
//    public String submitForm(@RequestParam(name = "addUser", defaultValue = "false") boolean addUser,
//                             @RequestParam(name = "deleteUser", defaultValue = "false") boolean deleteUser,
//                             @RequestParam(name = "editUser", defaultValue = "false") boolean editUser,
//                             @RequestParam(name = "archiveUser", defaultValue = "false") boolean archiveUser) {
//
//        if (addUser) {
//            System.out.println("addUser is checked");
//        }
//        if (deleteUser) {
//            System.out.println("deleteUser is checked");
//        }
//        if (editUser) {
//            System.out.println("editUser is checked");
//        }
//        if (archiveUser) {
//            System.out.println("archiveUser is checked");
//        }
//        return "allRegisteredUserList";
//    }
}
