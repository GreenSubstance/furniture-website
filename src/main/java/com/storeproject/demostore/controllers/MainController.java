package com.storeproject.demostore.controllers;

import com.storeproject.demostore.models.User;
import com.storeproject.demostore.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class MainController {

    @GetMapping("/")
    public String home(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("title", "Home page");
        model.addAttribute("currUser", user);
        return "home";
    }

}
