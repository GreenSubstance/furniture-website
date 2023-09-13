package com.storeproject.demostore.controllers;

import com.storeproject.demostore.models.Item;
import com.storeproject.demostore.services.ItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AdminController {

    final ItemService itemService;

    @GetMapping()
    public String adminPage(Model model) {
        return "admin";
    }

    @GetMapping("/catalog")
    public String catalog(Model model) {
        model.addAttribute("items", itemService.getAllItems());
        model.addAttribute("item", new Item());
        return "catalogEdit";
    }

    @GetMapping("/catalog/{item}")
    public String product(Model model, @PathVariable Item item) {
        model.addAttribute("item", item);
        return "productEdit";
    }

    @PostMapping(value = "/catalog", params = "action=save")
    public String saveItem(@ModelAttribute("item") Item item) {
        itemService.saveItem(item);
        return "redirect:/admin/catalog";
    }

    @GetMapping("/catalog/delete/{item_id}")
    public String deleteItem(@PathVariable(name = "item_id") Long itemId) {
        itemService.deleteItem(itemId);
        return "redirect:/admin/catalog";
    }

    @PostMapping("/catalog/{item}")
    public String saveEditedItem(@ModelAttribute("item") Item item) {
        itemService.saveItem(item);
        return "redirect:/admin/catalog";
    }
}
