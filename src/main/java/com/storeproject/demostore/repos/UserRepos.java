package com.storeproject.demostore.repos;

import com.storeproject.demostore.models.Order;
import com.storeproject.demostore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepos extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
