package com.storeproject.demostore.services;

import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.OrderContent;
import com.storeproject.demostore.repos.OrderContentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderContentService {

    @Autowired
    OrderContentRepo orderContentRepo;

    public void addOrderContent(Order order, Long item_id, Integer quantity, Integer fabric_category, Integer sum_price) {
        OrderContent orderContent = new OrderContent();

        orderContent.setOrder(order);
        orderContent.setId(item_id);
        orderContent.setQuantity(quantity);
        orderContent.setSumPrice(sum_price);
        orderContent.setFabricCategory(fabric_category);

        orderContentRepo.save(orderContent);
    }
}
