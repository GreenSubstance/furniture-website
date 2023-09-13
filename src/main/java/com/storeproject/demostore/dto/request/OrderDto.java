package com.storeproject.demostore.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDto {

    @NotBlank
    String address;

    @NotNull
    Integer deliveryFee;

    @NotNull
    Integer upfrontFee;

    @NotNull
    Integer total;
}
