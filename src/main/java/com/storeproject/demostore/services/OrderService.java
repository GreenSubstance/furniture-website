package com.storeproject.demostore.services;

import com.storeproject.demostore.dto.mappers.OrderContentMapper;
import com.storeproject.demostore.dto.mappers.OrderMapper;
import com.storeproject.demostore.dto.request.CartItemDto;
import com.storeproject.demostore.dto.request.OrderDto;
import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.repos.OrderContentRepo;
import com.storeproject.demostore.repos.OrderRepo;
import com.storeproject.demostore.repos.UserRepo;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderService {

    final OrderRepo orderRepo;
    final OrderContentRepo orderContentRepo;
    final UserRepo userRepo;

    @Transactional
    public List<Order> getOrdersByUser(User user) {
        return userRepo
                .findByUsername(user.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(user.getUsername()))
                .getOrders();
    }

    @Transactional
    public void formOrder(User user, OrderDto orderInfo, Map<Integer, CartItemDto> cart) {

        Order order = OrderMapper.toEntity(orderInfo, user);
        orderRepo.save(order);
        cart
                .values()
                .forEach(v ->
                        orderContentRepo.save(OrderContentMapper.toEntity(v, order)));
    }
}
