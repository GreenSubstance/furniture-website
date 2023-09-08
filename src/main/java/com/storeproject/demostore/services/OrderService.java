package com.storeproject.demostore.services;

import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.OrderState;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.repos.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    public Order addOrder(User user, String delivery_address, Integer total, Integer delivery_fee, Integer upfront_fee) {
        Order order = new Order();

        Calendar c = Calendar.getInstance();
        Date currDate = new Date();
        c.setTime(currDate);
        c.add(Calendar.DATE, 14);
        Date deliveryDate = c.getTime();

        order.setUser(user);
        order.setPlacementDate(currDate);
        order.setDeliveryDate(deliveryDate);
        order.setDeliveryAddress(delivery_address);
        order.setTotal(total);
        order.setUpfront(upfront_fee);
        order.setDeliveryFee(delivery_fee);
        order.setOrderState(OrderState.ACCEPTED);

        orderRepo.save(order);

        return order;
    }
}
