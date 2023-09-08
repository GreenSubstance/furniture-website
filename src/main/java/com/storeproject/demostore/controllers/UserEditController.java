package com.storeproject.demostore.controllers;

import com.storeproject.demostore.models.Role;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/user")
public class UserEditController {

    @Autowired
    private UserService userService;

    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "userList";
    }

    @GetMapping("/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "userEdit";
    }

    @PostMapping("/{user}")
    public String userSave(Model model,
                           @RequestParam("userId") User user,
                           @RequestParam("username") String username,
                           @RequestParam("roles") String[] roles) {

        if (username != null && !username.isEmpty()) {
            user.setUsername(username);
        }

        user.getRoles().clear();
        for(String role : roles) {
            user.getRoles().add(Role.valueOf(role));
        }

        userService.saveUser(user);

        return "redirect:/user";
    }
}
