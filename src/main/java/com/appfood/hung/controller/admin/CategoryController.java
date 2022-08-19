package com.appfood.hung.controller.admin;

import com.appfood.hung.payload.dto.CategoryDto;
import com.appfood.hung.repository.CategoryRepository;
import com.appfood.hung.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/category/")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("create")
    public String create(Model model) throws Exception {
        CategoryDto dto = new CategoryDto();
        model.addAttribute("categoryDto", dto);
        return "admin/category/create";
    }

    @PostMapping("upload")

        public String save(@ModelAttribute("category") CategoryDto categoryDto) {
            categoryService.save(categoryDto);
            return "redirect:/admin/category/create";
        }

}
