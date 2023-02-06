package com.example.web_giay.service;

import com.example.web_giay.dto.ProductsDTO;

import java.util.List;

public interface ProductService {
    String delete(Long[] ids);

    ProductsDTO saveProduct(ProductsDTO productsDTO) ;
    ProductsDTO searchProductById(Long id);
    ProductsDTO searchProductByName(String name);

    List<ProductsDTO> searchListProductByNameLike(String name);

    List<ProductsDTO> searchProductPrice(double price);
    List<ProductsDTO> searchProductPriceRage(double startprice,double endprice);
    List<ProductsDTO> searchProductByCategoryName(String name);
}
