package com.appfood.hung.service;


import com.appfood.hung.model.Product;
import com.appfood.hung.payload.dto.ProductDto;
import com.appfood.hung.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class
ProductService {
    @Autowired
    private ProductRepository productRepo;

/*
    public Product findById(){return productRepo.findById();}
*/
    public void  saveProductToDB(ProductDto p){
        productRepo.save(p.converToEntity());
    }
    public List<Product> getAllProduct(Integer pageNo, Integer pageSize, String sortBy){
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Product> pageResult = productRepo.findAll(pageable);
        if (pageResult.hasContent()) {
            return pageResult.getContent();
        } else {
            return new ArrayList<Product>();
        }
    }
    public void deleteProductById(Long id)
    {
        productRepo.deleteById(id);
    }
    public void chageProductName(Long id ,String name)
    {
        Product p = new Product();
        p = productRepo.findById(id).get();
        p.setName(name);
        productRepo.save(p);
    }
    public void changeProductDescription(Long id , String description)
    {
        Product p = new Product();
        p = productRepo.findById(id).get();
        p.setDescription(description);
        productRepo.save(p);
    }
    public void changeProductPrice(Long id,int price)
    {
        Product p = new Product();
        p = productRepo.findById(id).get();
        p.setPrice(price);
        productRepo.save(p);
    }


    public Product findById(Long id) {
        Product p = new Product();
        p =productRepo.findById(id).get();
        return p;
    }

    public List<Product> getAllProduct() {
        return productRepo.findAll();
    }
}
