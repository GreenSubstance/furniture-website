package com.storeproject.demostore.controllers;

import com.storeproject.demostore.dto.request.UserDto;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class RegistrationController {

    final UserService userService;

    @GetMapping()
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping()
    public String addUser(Model model, UserDto user) {

        try {
            User userFromDb = userService.loadUserByUsername(user.getUsername());
            model.addAttribute("message", "User already exists " + userFromDb.getUsername());
            return "registration";
        }
        catch (UsernameNotFoundException e) {
            userService.registerUser(user);
            return "redirect:/login";
        }
    }
}
