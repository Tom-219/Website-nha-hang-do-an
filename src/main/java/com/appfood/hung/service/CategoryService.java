package com.appfood.hung.service;
import com.appfood.hung.model.Category;
import com.appfood.hung.payload.dto.CategoryDto;
import com.appfood.hung.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Category save(CategoryDto o) {
        return categoryRepository.save(o.convertToCategory());
         /*new ResponseDto(1, o.getId() == null ? "Tạo mới thành công" : "Cập nhật thành công");*/
    }

    public List<Category> findAll() {

            return categoryRepository.findAll();
    }

}
