package com.storeproject.demostore.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {

    String address;
    Integer deliveryFee;
    Integer upfrontFee;
    Integer total;
}
