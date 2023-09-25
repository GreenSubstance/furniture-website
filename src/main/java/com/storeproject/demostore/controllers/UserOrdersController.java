package com.storeproject.demostore.controllers;

import com.storeproject.demostore.dto.request.UserDto;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.services.OrderService;
import com.storeproject.demostore.services.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/profile")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserOrdersController {

    final UserService userService;
    final OrderService orderService;

    @GetMapping("/orders")
    public String getProfile(@AuthenticationPrincipal User user, Model model) {
        model.addAttribute("orders", orderService.getOrdersByUser(user));
        return "userOrderInfo";
    }
}
