package com.storeproject.demostore.models;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "sofa_catalog")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long item_id;

    String item_name;
    String item_desc;

    Integer diml;
    Integer dimw;
    Integer dimh;
    Integer diml_sleep;
    Integer dimw_sleep;
    Integer dimh_sleep;
    Integer dimd_seat;
    Integer dimh_seat;

    String img_path;
    Long sofa_type;
    Integer qnt;

    @Singular
    @ElementCollection
    @CollectionTable(name = "sofa_prices", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "item_price")
    @OrderBy("item_price asc")
    List<Integer> prices = new ArrayList<>();

    @Singular
    @ElementCollection
    @CollectionTable(name = "sofa_prices", joinColumns = @JoinColumn(name = "item_id"))
    @Column(name = "fabric_category")
    List<Integer> fabric_categories = new ArrayList<>();

    @Singular
    @ManyToMany
    @JoinTable(
            name = "sofa_colors",
    joinColumns = @JoinColumn(name = "sofa_id"),
    inverseJoinColumns = @JoinColumn(name = "color_id"))
    @Column(name = "color_name")
    List<Color> item_colors = new ArrayList<>();

    @Singular
    @ManyToMany
    @JoinTable(
            name = "sofa_styles",
            joinColumns = @JoinColumn(name = "sofa_id"),
            inverseJoinColumns = @JoinColumn(name = "style_id"))
    @Column(name = "style_name")
    List<Style> item_styles = new ArrayList<>();

    @OneToMany(mappedBy = "item", orphanRemoval = true)
    List<OrderContent> orderContentList = new ArrayList<>();
}
