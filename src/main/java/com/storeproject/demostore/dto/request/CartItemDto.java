package com.storeproject.demostore.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.beans.Transient;
import java.util.Objects;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartItemDto {

    Long id;
    String itemName;
    String imgPath;
    Integer fabricCategory;
    Integer price;
    Long colorId;
    String colorName;
    Integer qnt;

    @Transient
    public Integer getSubtotal() {
        return price * qnt;
    }

    /* qnt isn't involved */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemDto that = (CartItemDto) o;
        return id.equals(that.id) && Objects.equals(fabricCategory, that.fabricCategory) && Objects.equals(price, that.price) && Objects.equals(colorId, that.colorId) && Objects.equals(colorName, that.colorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fabricCategory, price, colorId, colorName);
    }

    @Override
    public String toString() {
        return "CartItemDto{" +
                "id=" + id +
                ", fabricCategory=" + fabricCategory +
                ", price=" + price +
                ", colorId=" + colorId +
                ", colorName='" + colorName + '\'' +
                ", qnt=" + qnt +
                '}';
    }
}
