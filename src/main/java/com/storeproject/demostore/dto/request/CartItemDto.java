package com.storeproject.demostore.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemDto {

    Long id;

    @JsonProperty("fabric_category")
    Integer fabricCategory;

    Integer price;

    @JsonProperty("color_id")
    Long colorId;

    @JsonProperty("color_name")
    String colorName;

    @JsonProperty("quantity")
    Integer qnt;
}
