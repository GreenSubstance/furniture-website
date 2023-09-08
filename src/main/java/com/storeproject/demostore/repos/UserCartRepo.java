package com.storeproject.demostore.repos;

import com.storeproject.demostore.models.Item;
import com.storeproject.demostore.models.User;
import com.storeproject.demostore.models.UserCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserCartRepo extends JpaRepository<UserCart, Long> {
    public List<UserCart> findByUser(User user);
    public UserCart findByUserAndItem(User user, Item item);

    @Modifying
    @Transactional
    @Query(value = "delete from user_cart as c where c.user_id = :uid and c.item_id = :iid", nativeQuery = true)
    public void deleteByUserAndItem(@Param("uid") Long uid, @Param("iid") Long iid);
}
