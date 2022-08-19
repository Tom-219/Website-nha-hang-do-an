package com.appfood.hung.controller.rest;

import com.appfood.hung.model.CartItem;
import com.appfood.hung.model.Product;
import com.appfood.hung.repository.facade.AuthenticationFacade;
import com.appfood.hung.service.CartService;
import com.appfood.hung.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    private final ProductService productService;

    private final AuthenticationFacade facade;

    @GetMapping("/add-to-cart/{id}")
    public String add(@PathVariable("id") long id) {
        long uid = facade.getCurrentUserId();
        Product product = productService.findById(id);
        if (product != null) {
            CartItem item = new CartItem();
            /*BeanUtils.copyProperties(product, item);*/
            item.setPrice((double) product.getPrice());
            item.setName(product.getName());
            item.setQuantity(1);
            cartService.add(item);
        }
        /*return"admin/cartItem/list";*/
        return "admin/cartItem/list";
    }
}
