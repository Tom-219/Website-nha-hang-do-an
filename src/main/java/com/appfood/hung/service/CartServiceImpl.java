package com.appfood.hung.service;


import com.appfood.hung.model.CartItem;
import com.appfood.hung.model.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
@SessionScope
public class CartServiceImpl implements CartService{
    private Map<Product, CartItem> map = new HashMap<>();

    public void add(CartItem item){
        CartItem existedItem = map.get(item.getProduct());
        if(existedItem != null){
            existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
        }else{
            map.put(item.getProduct(),item);
        }
        map.put(item.getProduct(),item);
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
