package com.storeproject.demostore.controllers;

import com.storeproject.demostore.dto.request.CartItemDto;
import com.storeproject.demostore.exceptions.BadRequestException;
import com.storeproject.demostore.models.Item;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.services.CartService;
import com.storeproject.demostore.services.ItemService;
import com.storeproject.demostore.services.UserCartService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Controller
public class CatalogController {

    final ItemService itemService;
    //final UserCartService userCartService;
    final CartService cartService;

    @GetMapping(path = {"/catalog","/search"})
    public String catalog(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("title", "Catalog");
        Iterable<Item> items;
        if (keyword != null && !keyword.isEmpty()) {
            items = itemService.getByKeyword(keyword);
        } else {
            items = itemService.getAllItems();
        }
        model.addAttribute("items", items);
        return "catalog";
    }

    @GetMapping("/product/{id}")
    public String product(@PathVariable(name = "id") Long productId, Model model) {
        model.addAttribute("title", "Catalog");
        model.addAttribute("item", itemService.getById(productId));
        model.addAttribute("cartItem", new CartItemDto());
        return "product";
    }

    @PostMapping("/product")
    public void addItemToCart(@AuthenticationPrincipal User user,
                              @ModelAttribute("cartItem") CartItemDto cartItemDto) {
        if (user == null) {
            throw new BadRequestException("user not logged in");
        }
        cartService.addItem(cartItemDto);
    }

    /*
    @PostMapping("/product/{id}")
    public String addItemToCart(@PathVariable(name = "id") Long productId, Model model,
                                @AuthenticationPrincipal User user,
                                @RequestParam(name = "qnt") Integer qnt, @RequestParam(name = "fabric_cat") Integer fabric_cat,
                                @RequestParam(name = "price") Integer price, @RequestParam(name = "color_name", required = false) String color_name) {
        if (user == null) {
            return "redirect:/login";
        }
        Integer addedQnt = userCartService.addItemToCart(user, productId, qnt, fabric_cat, price, color_name);
        model.addAttribute("item", itemService.getById(productId));
        return "product";
    }
    */

}
