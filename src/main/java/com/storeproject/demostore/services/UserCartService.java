package com.storeproject.demostore.services;

import com.storeproject.demostore.models.Item;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.models.UserCart;
import com.storeproject.demostore.repos.ItemRepo;
import com.storeproject.demostore.repos.UserCartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserCartService {

    @Autowired
    private UserCartRepo userCartRepo;
    @Autowired
    private ItemRepo itemRepo;

    public List<UserCart> getCart(User user) {
        return userCartRepo.findByUser(user);
    }

    public Integer addItemToCart(User user, Long itemId, Integer qnt,
                                 Integer fabric_cat, Integer price, String color_name) {
        Integer finalQnt = qnt;

        Item item = itemRepo.findById(itemId).get();
        UserCart userCart = userCartRepo.findByUserAndItem(user, item);
        if (userCart != null) {
            finalQnt += userCart.getQnt();
            userCart.setQnt(finalQnt);
        } else {
            userCart = new UserCart();
            userCart.setQnt(qnt);
            userCart.setUser(user);
            userCart.setItem(item);
            userCart.setColor_name(color_name);
            userCart.setPrice(price);
            userCart.setFabric_cat(fabric_cat);
        }

        userCartRepo.save(userCart);

        return finalQnt;
    }

    public void deleteFromCart (User user, Long item_id) {
        userCartRepo.deleteByUserAndItem(user.getId(), item_id);
    }
}
