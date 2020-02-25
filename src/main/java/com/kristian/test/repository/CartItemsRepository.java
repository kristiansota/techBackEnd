package com.kristian.test.repository;

import com.kristian.test.model.Cart_items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemsRepository extends JpaRepository<Cart_items,Long> {

    @Query(value = "SELECT * FROM cart_items WHERE user_id = :userId", nativeQuery = true)
    public List<Cart_items> findItemsByUserId(@Param("userId") Long userId);



    @Modifying
    @Query(value = "DELETE FROM cart_items WHERE id = :cartItemId", nativeQuery = true)
    public void deleteCartItem(Long cartItemId);

    @Query(value = "SELECT * FROM cart_items WHERE user_id = :userId AND p_id = :p_id", nativeQuery = true)
    Cart_items existingCartItem(Long p_id, long userId);
}
