package com.appfood.hung.payload.dto;
import com.appfood.hung.model.Category;
import com.appfood.hung.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.Base64;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    Long id;

    int price;

    String name;

    String description;

    MultipartFile file;

    Category category;

    public Product converToEntity(){
        Product product = new Product();
        product.setId(this.id);
        product.setPrice(this.price);
        product.setName(this.name);
        product.setDescription(this.description);
        product.setCategory(this.category);
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            product.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return product;
    }

}
