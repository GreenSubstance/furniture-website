package com.storeproject.demostore.controllers;

import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.models.UserCart;
import com.storeproject.demostore.services.OrderContentService;
import com.storeproject.demostore.services.OrderService;
import com.storeproject.demostore.services.UserCartService;
import com.storeproject.demostore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
/*
@Controller
public class UserCartController {

    @Autowired
    private UserCartService userCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderContentService orderContentService;

    @GetMapping("/cart")
    public String userCart(Model model, @AuthenticationPrincipal User user) {


        List<UserCart> listUserCart = userCartService.getCart(user);
        model.addAttribute("listUserCart", listUserCart);

        return "cart";
    }

    @GetMapping("/cart/{item_id}")
    public String removeItemFromCart(Model model,
                                @AuthenticationPrincipal User user, @PathVariable Long item_id) {
        if (user == null) {
            return "Log in";
        }
        userCartService.deleteFromCart(user, item_id);

        return "redirect:/cart";
    }

    @GetMapping("/cart/checkout")
    public String checkout(Model model, @AuthenticationPrincipal User user) {
        List<UserCart> listUserCart = userCartService.getCart(user);
        model.addAttribute("listUserCart", listUserCart);

        return "/checkout";
    }

    @PostMapping("/cart/checkout")
    public String checkoutFormOrder(Model model, @AuthenticationPrincipal User user,
                                    @RequestParam String delivery_address, @RequestParam Integer total,
                                    @RequestParam Integer delivery_fee, @RequestParam Integer upfront_fee) {

        List<UserCart> listUserCart = userCartService.getCart(user);
        Order order = orderService.addOrder(user, delivery_address, total, delivery_fee, upfront_fee);
        for (UserCart cartItem : listUserCart) {
            orderContentService.addOrderContent(order, cartItem.getItem().getItem_id(), cartItem.getQnt(), cartItem.getFabric_cat(), cartItem.getSubtotal());
            userCartService.deleteFromCart(user, cartItem.getItem().getItem_id());
        }

        return "redirect:/catalog";
    }
}
*/