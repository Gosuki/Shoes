package com.example.web_giay.Convert;

import com.example.web_giay.dto.ProductDTO;
import com.example.web_giay.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConvert {
    public ProductDTO toDTO(Product product){
        ProductDTO dto = new ProductDTO();
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
    public Product toEntity(ProductDTO productDTO){
        Product entity = new Product();
        entity.setName(productDTO.getName());
        entity.setPrice(productDTO.getPrice());
        entity.setStatusId(productDTO.getStatusId());
        entity.setUrlImage(productDTO.getUrlImage());
        return entity;
    }
    public Product toEntity(ProductDTO DTO, Product entity){
        entity.setName(DTO.getName());
        entity.setPrice(DTO.getPrice());
        entity.setStatusId(DTO.getStatusId());
        entity.setUrlImage(DTO.getUrlImage());
        return entity;
    }
}
