package com.example.web_giay.Convert;

import com.example.web_giay.dto.ProductsDTO;
import com.example.web_giay.entity.Products;
import org.springframework.stereotype.Component;

@Component
public class ProductConvert {
    public ProductsDTO toDTO(Products product){
        ProductsDTO dto = new ProductsDTO();
        if(product.getId()!=null){
            dto.setId(product.getId());
        }
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setStatusId(product.getStatusId());
        dto.setModifiedDate(product.getModifiedDate());
        dto.setUrlImage(product.getUrlImage());
        dto.setCategoryname(product.getCategory().getName());
        dto.setCategoryid(product.getCategory().getId());
        return dto;
    }
    public Products toEntity(ProductsDTO productsDTO){
        Products entity = new Products();
        entity.setName(productsDTO.getName());
        entity.setPrice(productsDTO.getPrice());
        entity.setStatusId(productsDTO.getStatusId());
        entity.setUrlImage(productsDTO.getUrlImage());
        return entity;
    }
    public Products toEntity(ProductsDTO DTO,Products entity){
        entity.setName(DTO.getName());
        entity.setPrice(DTO.getPrice());
        entity.setStatusId(DTO.getStatusId());
        entity.setUrlImage(DTO.getUrlImage());
        return entity;
    }
}
