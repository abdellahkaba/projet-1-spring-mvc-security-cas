package com.isi.mvc.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer id;
    @NotEmpty(message = "la reference du produit est requise")
    private String ref;
    @NotEmpty(message = "Le nom de produit est requis")
    private String name;
}