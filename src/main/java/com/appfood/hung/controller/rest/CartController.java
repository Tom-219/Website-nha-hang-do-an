package com.appfood.hung.controller.rest;

import com.appfood.hung.model.CartItem;
import com.appfood.hung.model.Product;
import com.appfood.hung.repository.facade.AuthenticationFacade;
import com.appfood.hung.service.CartService;
import com.appfood.hung.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/add-to-cart/{id}")
    public ResponseEntity<Void> addToCart(@PathVariable("id") long id) {
        cartService.addToCard(id);
        return ResponseEntity.ok().build();
    }
}
