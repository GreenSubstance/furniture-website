package com.storeproject.demostore.services;

import com.storeproject.demostore.dto.mappers.OrderContentMapper;
import com.storeproject.demostore.dto.mappers.OrderMapper;
import com.storeproject.demostore.dto.request.CartItemDto;
import com.storeproject.demostore.dto.request.OrderDto;
import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.repos.OrderContentRepo;
import com.storeproject.demostore.repos.OrderRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    final OrderRepo orderRepo;
    final OrderContentRepo orderContentRepo;

    final Map<CartItemDto, Integer> cart = new HashMap<>(); //item, quantity


    public Map<CartItemDto, Integer> getCart() {
        return cart;
    }

    public void addItem(CartItemDto itemToAdd) {

        cart.merge(itemToAdd, itemToAdd.getQnt(), Integer::sum);

        /*
        if (cart.containsKey(item)) {
            cart.replace(item, cart.get(item) + 1);
        } else {
            cart.put(item, 1);
        }
        */
    }

    public void removeItem(CartItemDto item) {
        cart.remove(item);
    }

    @Transactional
    public void checkout(User user, OrderDto orderInfo) {

        Order order = OrderMapper.toEntity(orderInfo, user);
        orderRepo.save(order);

        cart.forEach((k, v)
                -> orderContentRepo.save(OrderContentMapper.toEntity(k, v, order)));

        cart.clear();
    }
}
