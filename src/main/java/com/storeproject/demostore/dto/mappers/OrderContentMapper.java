package com.storeproject.demostore.dto.mappers;

import com.storeproject.demostore.dto.request.CartItemDto;
import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.OrderContent;
import com.storeproject.demostore.repos.ColorsRepo;
import com.storeproject.demostore.repos.ItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderContentMapper {

    private static ColorsRepo colorsRepo;
    private static ItemRepo itemRepo;

    OrderContentMapper(@Autowired ColorsRepo colorsRepo, @Autowired ItemRepo itemRepo) {
        OrderContentMapper.colorsRepo = colorsRepo;
        OrderContentMapper.itemRepo = itemRepo;
    }

    public static CartItemDto toDto(OrderContent orderContent) {
        return null;
    }

    public static OrderContent toEntity(CartItemDto cartItemDto, Integer qnt, Order order) {

        return OrderContent.builder()
                .item(itemRepo.getReferenceById(cartItemDto.getId()))
                .order(order)
                .quantity(qnt)
                .fabricCategory(cartItemDto.getFabricCategory())
                .color(colorsRepo.getReferenceById(cartItemDto.getColorId()))
                .sumPrice(cartItemDto.getPrice() * qnt)
                .build();
    }

}
