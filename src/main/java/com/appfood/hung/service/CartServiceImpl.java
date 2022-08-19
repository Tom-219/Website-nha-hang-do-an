package com.appfood.hung.service;


import com.appfood.hung.model.Cart;
import com.appfood.hung.model.CartItem;
import com.appfood.hung.model.Product;
import com.appfood.hung.model.User;
import com.appfood.hung.repository.CartItemRepository;
import com.appfood.hung.repository.CartRepository;
import com.appfood.hung.repository.UserRepository;
import com.appfood.hung.repository.facade.AuthenticationFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Service
@SessionScope
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private Map<Product, CartItem> map = new HashMap<>();

    private final AuthenticationFacade facade;

    private final ProductService productService;

    private final CartRepository repository;

    private final CartItemRepository cartItemRepository;

    private final UserRepository userRepository;

    public void add(CartItem item) {
        CartItem existedItem = map.get(item.getProduct());
        if (existedItem != null) {
            existedItem.setQuantity(item.getQuantity() + existedItem.getQuantity());
        } else {
            map.put(item.getProduct(), item);
        }
        map.put(item.getProduct(), item);
    }

    @Override
    @Transactional
    public void addToCard(long productId) {
        long uid = facade.getCurrentUserId();
        Product product = productService.findById(productId);
        // kiem tra cart voi uid nay da co chua, neu chua thi add cart & new cart - item voi qty = 1

        Cart cart = repository.findByUserId(uid);
        if (cart == null) {
            cart = new Cart(userRepository.findById(uid).orElse(null));
            Cart cartUpdate = repository.save(cart);
            User currentUser = facade.getCurrentUser();
            currentUser.setCart(cart);
            CartItem item = CartItem.builder()
                    .cart(cartUpdate)
                    .quantity(1)
                    .product(product)
                    .build();
            cartItemRepository.save(item);
        } else {
            List<CartItem> cartItemList = cartItemRepository.findByCartId(cart.getId());
            AtomicBoolean productExist = new AtomicBoolean(false);
            cartItemList.forEach(cartItem -> {
                if (cartItem.getProduct().getId() == productId) {
                    productExist.set(true);
                    cartItem.setQuantity(cartItem.getQuantity() + 1);
                }
            });

            if (!productExist.get()) {
                cartItemRepository.save(CartItem.builder()
                        .cart(cart)
                        .quantity(1)
                        .product(product)
                        .build());
            }
        }
    }

    public void remove(Product product) {
        map.remove(product);
    }

    public Collection<CartItem> getCartItem() {
        return map.values();
    }

    public void clear() {
        map.clear();
    }

    public void update(Product product, Integer quantity) {
        CartItem item = map.get(product);

        item.setQuantity(quantity + item.getQuantity());

        if (item.getQuantity() <= 0) {
            map.remove(product);
        }
    }

    public Double getAmount() {
        long uid = facade.getCurrentUserId();
        Cart cart = repository.findByUserId(uid);
        if (cart != null) {
            List<CartItem> cartItems = cartItemRepository.findByCartId(cart.getId());
            return cartItems.stream().mapToDouble(item -> item.getQuantity() * item.getProduct().getPrice()).sum();
        }
        return 0d;
    }

    public Integer getCount() {
        if (map.isEmpty())
            return 0;
        return map.values().size();
    }
}
