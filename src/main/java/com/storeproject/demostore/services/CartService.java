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
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    final OrderRepo orderRepo;
    final OrderContentRepo orderContentRepo;

    final Map<Integer, CartItemDto> cart = new HashMap<>(); //id, item
    AtomicInteger idGen = new AtomicInteger(0);


    public Map<Integer, CartItemDto> getCart() {
        return cart;
    }

    public void addItem(CartItemDto itemToAdd) {

        Optional<Integer> itemId = cart
                .entrySet()
                .stream()
                .filter(entry -> itemToAdd.equals(entry.getValue()))
                .map(Map.Entry::getKey)
                .findAny();

        if (itemId.isPresent()) {
            CartItemDto item = cart.get(itemId.get());
            item.setQnt(item.getQnt() + itemToAdd.getQnt());
        }
        else cart.put(idGen.getAndIncrement(), itemToAdd);
    }

    public void removeItem(Integer itemId) {
        cart.remove(itemId);
    }

    @Transactional
    public void checkout(User user, OrderDto orderInfo) {

        Order order = OrderMapper.toEntity(orderInfo, user);
        orderRepo.save(order);
        cart
                .values()
                .forEach(v ->
                        orderContentRepo.save(OrderContentMapper.toEntity(v, order)));
        cart.clear();
    }
}
