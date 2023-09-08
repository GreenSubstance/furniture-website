package com.storeproject.demostore.services;

import com.storeproject.demostore.models.Item;
import com.storeproject.demostore.repos.ItemRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class ItemService {

    final ItemRepo itemRepo;

    public Iterable<Item> getAllItems() {
        return itemRepo.findAll();
    }
    public Iterable<Item> getByKeyword(String keyword) {
        return itemRepo.findByKeyword(keyword);
    }
    public Item getById(Long id) {
        return itemRepo
                .findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Item ID not found: " + id));
    }

    public void saveItem(Item item) {
        itemRepo.save(item);
    }

    public void deleteItem(Long item_id) {
        itemRepo.deleteById(item_id);
    }
}
