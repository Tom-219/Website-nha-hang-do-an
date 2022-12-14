package com.appfood.hung.repository;

import com.appfood.hung.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    List<CartItem> findByCartId(long cartId);

    long countByCartId(long cartId);
}
