package com.example.web_giay.service;

import com.example.web_giay.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    String delete(Long[] ids);

    ProductDTO saveProduct(ProductDTO productDTO) ;
    ProductDTO searchProductById(Long id);
    List<ProductDTO> searchProductByNameLikeOrPrice(String name,double price);

    List<ProductDTO> searchProductPriceRage(double startprice, double endprice);
    List<ProductDTO> searchProductByCategoryName(String name);
}
