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
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    User user;

    @Singular
    @OneToMany(mappedBy = "order", orphanRemoval = true) //eager
    List<OrderContent> contents = new ArrayList<>();

    @Column(name = "placement_date")
    Date placementDate;

    @Column(name = "delivery_date")
    Date deliveryDate;

    @Column(name = "delivery_address")
    String deliveryAddress;

    @Column(name = "delivery_fee")
    Integer deliveryFee;

    @Column(name = "order_state")
    OrderState orderState;

    Integer upfront;
    Integer total;

}
