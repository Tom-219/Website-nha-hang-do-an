package com.appfood.hung.service;


import com.appfood.hung.model.Cart;
import com.appfood.hung.model.CartItem;
import com.appfood.hung.model.Product;
import com.appfood.hung.repository.CartRepository;
import com.appfood.hung.repository.UserRepository;
import com.appfood.hung.repository.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
@RequiredArgsConstructor
public class CartServiceImpl implements CartService{
    private Map<Product, CartItem> map = new HashMap<>();

    private final AuthenticationFacade facade;

    private final CartRepository repository;

    private final UserRepository userRepository;

    public void add(CartItem item){
        CartItem existedItem = map.get(item.getProduct());
        if(existedItem != null){
            existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
        }else{
            map.put(item.getProduct(),item);
        }
        map.put(item.getProduct(),item);
    }

    @Override
    public void addToCard(long productId) {
        long uid = facade.getCurrentUserId();
        // kiem tra cart voi uid nay da co chua, neu chua thi add cart & new cart - item voi qty = 1

        Cart cart = repository.findByUserId(uid);
        if (cart == null)
            cart = new Cart(userRepository.findById(uid).orElse(null));

    }

    public void remove(Product product){
        map.remove(product);
    }

    public Collection<CartItem> getCartItem(){
        return map.values();
    }
    public void clear(){
        map.clear();
    }

    public void update(Product product,Integer quantity){
        CartItem item = map.get(product);

        item.setQuantity(quantity + item.getQuantity());

        if (item.getQuantity() <=0){
            map.remove(product);
        }
    }

    public Double getAmount(){
        return map.values().stream().mapToDouble(item->item.getQuantity() * item.getPrice()).sum();
    }

    public Integer getCount(){
        if(map.isEmpty())
            return 0;
        return map.values().size();
    }
}
