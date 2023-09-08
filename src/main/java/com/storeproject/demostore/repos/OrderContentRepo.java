package com.storeproject.demostore.repos;

import com.storeproject.demostore.models.OrderContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderContentRepo extends JpaRepository<OrderContent, Long> {
}
