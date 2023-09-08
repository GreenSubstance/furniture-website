package com.storeproject.demostore.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long order_id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    User user;

    @Singular
    @OneToMany(mappedBy = "order", orphanRemoval = true) //eager
    List<OrderContent> contents = new ArrayList<>();

    Date placementDate;
    Date deliveryDate;
    String deliveryAddress;
    Integer upfront;
    Integer deliveryFee;
    Integer total;
    OrderState orderState;

}
