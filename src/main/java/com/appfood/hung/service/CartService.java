package com.appfood.hung.service;

import com.appfood.hung.model.CartItem;
import com.appfood.hung.model.Product;

import java.util.Collection;

public interface CartService {
    void add(CartItem item);

    void remove(Product product);

    Collection<CartItem> getCartItem();

    void clear();

    void update(Product product,Integer quantity);

    Double getAmount();

    Integer getCount();

}
