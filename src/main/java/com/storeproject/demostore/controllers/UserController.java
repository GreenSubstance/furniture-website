package com.storeproject.demostore.controllers;

import com.storeproject.demostore.models.User;
import com.storeproject.demostore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {
        //model.addAttribute("orders", user.getOrders());
        model.addAttribute("orders", userService.getOrders(user));
        return "profile";
    }

    @PostMapping("/profile")
    public String updateProfile(@AuthenticationPrincipal User user, Model model,
                                @RequestParam(required = false) String newUsername,
                                @RequestParam(required = false) String newPassword,
                                @RequestParam(required = false) String newEmail) {

        userService.updateProfile(user, newUsername, newPassword, newEmail);
        return "redirect:/user/profile";
    }
}
