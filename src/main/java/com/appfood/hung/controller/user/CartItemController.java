package com.appfood.hung.controller.user;


import com.appfood.hung.model.CartItem;
import com.appfood.hung.model.Product;
import com.appfood.hung.service.CartService;
import com.appfood.hung.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/user/")
public class CartItemController {
    @Autowired
    ProductService productService;

    @Autowired
    CartService cartService;
    @GetMapping("list")
        public String list(Model model){
        Collection<CartItem> cartItems = cartService.getCartItem();

        model.addAttribute("cartItems", cartItems);
        model.addAttribute("total", cartService.getAmount());
        model.addAttribute("NoOfItems", cartService.getCount());
        return "admin/cartItem/list";}

    @GetMapping ("addToCart/{id}")
    public String add(@PathVariable("id")Long id){
            Product product = productService.findById(id);
         if(product !=null){
             CartItem item = new CartItem();
             /*BeanUtils.copyProperties(product, item);*/
             item.setPrice((double) product.getPrice());
             item.setName(product.getName());
             item.setQuantity(1);
             cartService.add(item);
         }
        /*return"admin/cartItem/list";*/
        return"admin/cartItem/list";
    }


    @GetMapping("remove")
    public String remove(){
        return "";
    }

    @PostMapping("update")
    public String update(){
        return "";
    }

    @GetMapping("clear")
    public String clear(){
        return "";
    }


}
