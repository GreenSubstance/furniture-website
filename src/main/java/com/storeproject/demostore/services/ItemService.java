package com.storeproject.demostore.services;

import com.storeproject.demostore.models.Item;
import com.storeproject.demostore.repos.ItemRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemService {

    final ItemRepo itemRepo;

    public List<Item> getAllItems() {
        return itemRepo.findAll();
    }

    public List<Item> getByKeyword(String keyword) {
        return (keyword != null && !keyword.isBlank())
                ? itemRepo.findByKeyword(keyword)
                : itemRepo.findAll();
    }

    public Item getById(Long id) {
        return itemRepo
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item ID not found: " + id));
    }

    public void saveItem(Item item) {
        itemRepo.save(item);
    }

    public void deleteItem(Long itemId) {
        itemRepo.deleteById(itemId);
    }
}
