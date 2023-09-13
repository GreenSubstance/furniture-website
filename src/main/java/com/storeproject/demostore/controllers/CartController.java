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

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class CartController {

    final CartService cartService;

    @GetMapping()
    public String userCart(Model model) {
        Map<Integer, CartItemDto> cart = cartService.getCart();
        model.addAttribute("listUserCart", cart);
        System.out.println(cart);
        return "cart";
    }

    @GetMapping("/{item_id}")
    public String removeItemFromCart(@PathVariable(name = "item_id") Integer itemId) {
        cartService.removeItem(itemId);
        return "redirect:/cart";
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        Map<Integer, CartItemDto> cart = cartService.getCart();
        model.addAttribute("listUserCart", cart);
        model.addAttribute("orderInfo", new OrderDto());
        return "/checkout";
    }

    @PostMapping("/checkout")
    public String checkoutFormOrder(@AuthenticationPrincipal User user,
                                    @Valid @ModelAttribute("orderInfo") OrderDto orderDto) {

        cartService.checkout(user, orderDto);
        return "redirect:/user/profile";
    }
}
