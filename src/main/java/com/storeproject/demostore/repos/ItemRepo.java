package com.storeproject.demostore.repos;

import com.storeproject.demostore.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    @Query(value="select * from sofa_catalog as s " +
            "where s.item_name ilike %:keyword% or s.item_desc ilike %:keyword%", nativeQuery = true)
    List<Item> findByKeyword(@Param("keyword") String keyword);
}
