package com.storeproject.demostore.controllers;

import com.storeproject.demostore.models.Item;
import com.storeproject.demostore.services.ItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@FieldDefaults(level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
@Controller
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class CatalogEditController {

    final ItemService itemService;

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
    public String saveItem(Model model, @ModelAttribute("item") Item item) {
        itemService.saveItem(item);
        return "redirect:/admin/catalog";
    }

    @GetMapping(value = "/catalog/delete/{item_id}")
    public String deleteItem(Model model, @PathVariable Long item_id) {
        itemService.deleteItem(item_id);
        return "redirect:/admin/catalog";
    }

    @PostMapping("/catalog/{item}")
    public String saveEditedItem(Model model, @ModelAttribute("item") Item item) {
        itemService.saveItem(item);
        return "redirect:/admin/catalog";
    }
}
