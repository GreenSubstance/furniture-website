package com.storeproject.demostore.dto.mappers;

import com.storeproject.demostore.dto.request.OrderDto;
import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.OrderState;
import com.storeproject.demostore.models.User;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;

@Component
public class OrderMapper {

    public static Order toEntity(OrderDto orderDto, User user) {

        Calendar c = Calendar.getInstance();
        Date currDate = new Date();
        c.setTime(currDate);
        c.add(Calendar.DATE, 14);

        return Order.builder()
                .user(user)
                .placementDate(new Date())
                .deliveryDate(c.getTime())
                .deliveryAddress(orderDto.getAddress())
                .upfront(orderDto.getUpfrontFee())
                .deliveryFee(orderDto.getDeliveryFee())
                .total(orderDto.getTotal())
                .orderState(OrderState.ACCEPTED)
                .build();
    }

    public static OrderDto toDto(Order order) {
        return OrderDto.builder()
                .address(order.getDeliveryAddress())
                .deliveryFee(order.getDeliveryFee())
                .upfrontFee(order.getUpfront())
                .total(order.getTotal())
                .build();
    }

}
