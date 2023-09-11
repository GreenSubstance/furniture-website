package com.storeproject.demostore.controllers;

import com.storeproject.demostore.dto.request.CartItemDto;
import com.storeproject.demostore.dto.request.OrderDto;
import com.storeproject.demostore.exceptions.BadRequestException;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.services.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CartController {

    final CartService cartService;

    @GetMapping("/cart")
    public String userCart(Model model, @AuthenticationPrincipal User user) {

        if (user == null) {
            throw new BadRequestException("user not logged in");
        }

        Map<Integer, CartItemDto> cart = cartService.getCart();
        model.addAttribute("listUserCart", cart);
        System.out.println(cart);

        return "cart";
    }

    @GetMapping("/cart/{item_id}")
    public String removeItemFromCart(@AuthenticationPrincipal User user,
                                     @PathVariable(name = "item_id") Integer itemId) {
        if (user == null) {
            return "Log in";
        }
        cartService.removeItem(itemId);
        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String checkout(Model model, @AuthenticationPrincipal User user) {
        Map<Integer, CartItemDto> cart = cartService.getCart();
        model.addAttribute("listUserCart", cart);
        model.addAttribute("orderInfo", new OrderDto());
        return "/checkout";
    }

    @PostMapping("/cart/checkout")
    public String checkoutFormOrder(@AuthenticationPrincipal User user,
                                    @ModelAttribute("orderInfo") OrderDto orderDto) {

        cartService.checkout(user, orderDto);
        return "redirect:/user/profile";
    }
}
