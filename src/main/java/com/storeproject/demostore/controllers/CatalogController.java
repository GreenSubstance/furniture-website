package com.storeproject.demostore.controllers;

import com.storeproject.demostore.dto.request.CartItemDto;
import com.storeproject.demostore.exceptions.BadRequestException;
import com.storeproject.demostore.models.Item;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.services.CartService;
import com.storeproject.demostore.services.ItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Controller
public class CatalogController {

    final ItemService itemService;
    final CartService cartService;

    @GetMapping(path = {"/catalog","/search"})
    public String catalog(@RequestParam(name = "keyword", required = false) String keyword, Model model) {
        model.addAttribute("title", "Catalog");
        List<Item> items = itemService.getByKeyword(keyword);
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
                              @Valid @ModelAttribute("cartItem") CartItemDto cartItemDto) {
        if (user == null) {
            throw new BadRequestException("user not logged in");
        }
        cartService.addItem(cartItemDto);
    }

}
