package com.isi.mvc.mapper;


import com.isi.mvc.dtos.ProductDTO;
import com.isi.mvc.model.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product toProduct(ProductDTO productDTO) {
        if (productDTO == null){
            return null;
        }
        return Product.builder()
                .id(productDTO.getId())
                .ref(productDTO.getRef())
                .name(productDTO.getName())
                .build();
    }
}

