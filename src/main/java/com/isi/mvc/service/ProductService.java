package com.isi.mvc.service;


import com.isi.mvc.dtos.ProductDTO;
import com.isi.mvc.exception.BusinessErrorCodes;
import com.isi.mvc.exception.NameConflictException;
import com.isi.mvc.exception.ReferenceConflictException;
import com.isi.mvc.mapper.ProductMapper;
import com.isi.mvc.model.Product;
import com.isi.mvc.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Product addProduct(ProductDTO productDTO) {
        if (repository.findByRef(productDTO.getRef()).isPresent()){
            throw new ReferenceConflictException(BusinessErrorCodes.DUPLICATE_REFERENCE.getDescription() + " : " +  productDTO.getRef());
        }
        if (repository.findByName(productDTO.getName()).isPresent()){
            throw new NameConflictException(BusinessErrorCodes.DUPLICATE_NAME.getDescription() + " : " + productDTO.getName());
        }
        return repository.save(mapper.toProduct(productDTO));
    }


    public List<ProductDTO> listProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::fromProduct)
                .collect(Collectors.toList());
    }
}
