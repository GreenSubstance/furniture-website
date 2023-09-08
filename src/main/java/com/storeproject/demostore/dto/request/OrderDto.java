package com.storeproject.demostore.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {

    @JsonProperty("delivery_address")
    String address;

    @JsonProperty("delivery_fee")
    Integer deliveryFee;

    @JsonProperty("upfront_fee")
    Integer upfrontFee;

    Integer total;
}
