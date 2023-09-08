package com.storeproject.demostore.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "fabric_colors")
public class Color {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long color_id;

    String color_name;
    String color_hex;

    @ManyToMany(mappedBy = "item_colors")
    Set<Item> sofas = new HashSet<>();

    @OneToMany(mappedBy = "color", orphanRemoval = true)
    List<OrderContent> orderContent = new ArrayList<>();
}

