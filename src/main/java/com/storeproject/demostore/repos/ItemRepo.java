package com.storeproject.demostore.repos;

import com.storeproject.demostore.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepo extends JpaRepository<Item, Long> {
    @Query(value="select * from sofa_catalog as s " +
            "where s.item_name ilike %:keyword% or s.item_desc ilike %:keyword%", nativeQuery = true)
    Iterable<Item> findByKeyword(@Param("keyword") String keyword);
}
