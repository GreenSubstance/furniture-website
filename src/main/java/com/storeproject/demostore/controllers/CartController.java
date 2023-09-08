package com.storeproject.demostore.controllers;

import com.storeproject.demostore.dto.request.CartItemDto;
import com.storeproject.demostore.dto.request.OrderDto;
import com.storeproject.demostore.exceptions.BadRequestException;
import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.models.UserCart;
import com.storeproject.demostore.services.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

        Map<Long, CartItemDto> cart = cartService.getCart();
        model.addAttribute("listUserCart", cart);

        return "cart";
    }

    @GetMapping("/cart/{item_id}")
    public String removeItemFromCart(@AuthenticationPrincipal User user,
                                     @PathVariable(name = "item_id") Long id) {
        if (user == null) {
            return "Log in";
        }
        cartService.removeItem(id);

        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String checkout(Model model, @AuthenticationPrincipal User user) {
        Map<Long, CartItemDto> cart = cartService.getCart();
        model.addAttribute("listUserCart", cart);

        return "/checkout";
    }

    @PostMapping("/cart/checkout")
    public String checkoutFormOrder(Model model, @AuthenticationPrincipal User user,
                                    @RequestBody OrderDto orderDto) {

        cartService.checkout(user, orderDto);
        return "redirect:/catalog";
    }
}
