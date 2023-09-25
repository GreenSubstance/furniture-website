package com.storeproject.demostore.controllers;

import com.storeproject.demostore.dto.request.UserDto;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/profile")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class ProfileInfoController {

    final UserService userService;

    @GetMapping("/info")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("userInfo", new UserDto());
        return "userProfileInfo";
    }

    @PostMapping("/info")
    public String updateProfile(@AuthenticationPrincipal User user,
                                Model model,
                                @ModelAttribute("userInfo") UserDto userInfo) {

        String result = userService.updateProfile(user, userInfo);
        model.addAttribute("message", result);
        return "redirect:/user/profile/info";
    }
}
