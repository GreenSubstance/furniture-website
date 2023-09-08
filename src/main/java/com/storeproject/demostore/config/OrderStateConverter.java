package com.storeproject.demostore.config;

import com.storeproject.demostore.models.OrderState;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class OrderStateConverter implements Converter<String, OrderState> {
    @Override
    public OrderState convert(String source) {
        if (source.isBlank()) {
            return null;
        }
        try {
            return OrderState.valueOf(source.toUpperCase());
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
}
