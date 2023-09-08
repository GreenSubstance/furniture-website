package com.storeproject.demostore.repos;

import com.storeproject.demostore.models.Color;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColorsRepo extends JpaRepository<Color, Long> {
}
