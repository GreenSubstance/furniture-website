package com.storeproject.demostore.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "styles")
public class Style {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long style_id;
    String style_name;

    @ManyToMany(mappedBy = "item_styles")
    Set<Item> sofas = new HashSet<>();
}
