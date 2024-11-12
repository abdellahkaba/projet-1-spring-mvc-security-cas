package com.isi.mvc.controller;


import com.isi.mvc.dtos.ProductDTO;
import com.isi.mvc.model.Product;
import com.isi.mvc.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service ;

    @PostMapping("/product-create")
    public String addProduct(
            @Valid @ModelAttribute("product") ProductDTO productDTO,
            BindingResult bindingResult,
            Model model
    )
    {
        if (bindingResult.hasErrors()){
            model.addAttribute("product",productDTO);
            return "product-create.html";
        }
        service.addProduct(productDTO);
        return "redirect:/products";
    }

    @GetMapping("/product-create")
    public String formProduct (Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "product-create.html" ;
    }
}
