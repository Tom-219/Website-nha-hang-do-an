package com.appfood.hung.controller.admin;

import com.appfood.hung.model.Category;
import com.appfood.hung.model.Product;
import com.appfood.hung.payload.dto.ProductDto;
import com.appfood.hung.service.CategoryService;
import com.appfood.hung.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("admin/product/")
@Controller
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping("listproducts")
    public String showExampleView(Model model)
    {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "/admin/product/listproduct";
    }

    @GetMapping("new")
    public String createProductForm(Model model) {
        // create student object to hold student form data
        ProductDto p = new ProductDto();
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("productDto", p);
        return "admin/product/addProduct";

    }
    @PostMapping("addP")
    public String saveProduct(@ModelAttribute("productDto") ProductDto p, Model model)
    {
        List<Category> list = categoryService.findAll();
        model.addAttribute("categories", list);
        model.addAttribute("productDto",p);
        productService.saveProductToDB(p);
        return "redirect:/admin";
    }

    @GetMapping("deleteProd/{id}")
    public String deleteProduct(@PathVariable("id") Long id)
    {

        productService.deleteProductById(id);
        return "redirect:/admin";
    }

    @PostMapping("changeName")
    public String changePname(@RequestParam("id") Long id,
                              @RequestParam("newPname") String name)
    {
        productService.chageProductName(id, name);
        return "redirect:/admin";
    }
    @PostMapping("changeDescription")
    public String changeDescription(@RequestParam("id") Long id ,
                                    @RequestParam("newDescription") String description)
    {
        productService.changeProductDescription(id, description);
        return "redirect:/admin";
        /*return "redirect:/admin/product/listproducts";*/
    }

    @PostMapping("changePrice")
    public String changePrice(@RequestParam("id") Long id ,
                              @RequestParam("newPrice") int price)
    {
        productService.changeProductPrice(id, price);
        return "redirect:/admin";
    }
}


