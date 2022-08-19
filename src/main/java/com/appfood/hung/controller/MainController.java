package com.appfood.hung.controller;

import com.appfood.hung.model.Product;
import com.appfood.hung.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String root() {
        return "index1";
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/user")
    public String userIndex() {
        return "user/index";
    }
    @GetMapping("/index1")
    public String home(){return "index1";}



    @GetMapping("/about")
    public String AboutPage() {
        return "about";
    }

    @GetMapping("/contact")
    public String Contact() {
        return "contact";
    }

    @GetMapping("/gallery")
    public String Gallery() {
        return "gallery";
    }

    @GetMapping("/menu")
    public String MenuPage() {
        return "menu";
    }

    @GetMapping("/reservation")
    public String ReservationPage() {
        return "reservation";
    }

    @GetMapping("/listproducts")
    public String ListProducts(Model model){
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "productListUser";
    }


}
