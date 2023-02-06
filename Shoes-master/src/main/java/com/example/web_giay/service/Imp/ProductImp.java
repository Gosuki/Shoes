package com.example.web_giay.service.Imp;

import com.example.web_giay.Convert.ProductConvert;
import com.example.web_giay.dto.ProductsDTO;
import com.example.web_giay.entity.Category;
import com.example.web_giay.entity.Products;
import com.example.web_giay.repository.CategoryRepository;
import com.example.web_giay.repository.DescriptionRepository;
import com.example.web_giay.repository.ProductRepository;
import com.example.web_giay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductConvert productConvert;
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private DescriptionRepository descriptionRepository;

    @Override
    public String delete(Long[] ids) {
       for (Long item:ids){
           if(productRepository.findOneById(item)!=null){
               productRepository.deleteById(item);
               descriptionRepository.delete(descriptionRepository.findDescriptionByProducts(productRepository.findOneById(item)));
           }
           else {
               return "Not found ID";
           }
       }
        return "Delete Successful";
    }

    @Override
    public ProductsDTO saveProduct(ProductsDTO productsDTO) {
        Products product = new Products();
        if (productsDTO.getId()!=null){
          Products oldproduct = productRepository.findOneById(productsDTO.getId());
            product = productConvert.toEntity(productsDTO,oldproduct);
        } else {
            product = productConvert.toEntity(productsDTO);
        }
        Category category= categoryRepository.findOneByName(productsDTO.getCategoryname());
        product.setCategory(category);
        product = productRepository.save(product);
        return productConvert.toDTO(product);
    }

    @Override
    public ProductsDTO searchProductById(Long id) {
        Products product=productRepository.findOneById(id);
        if(product!=null){
            return productConvert.toDTO(product);
        }else{
            return null;
        }
    }

    @Override
    public ProductsDTO searchProductByName(String name) {
        Products product=productRepository.findProductsByName(name);
        if(product!=null){
            return productConvert.toDTO(product);
        }else{
            return null;
        }
    }
    public List<ProductsDTO> searchListProductByNameLike(String name) {
        List<Products> product=productRepository.searchProductsByNameLike(name);
        List<ProductsDTO> productsDTOList = new ArrayList<>();
        if(product!=null){
           for(Products temp:product){
               productsDTOList.add(productConvert.toDTO(temp));
           }
           return productsDTOList;
        }else{
            return null;
        }
    }

    @Override
    public List<ProductsDTO> searchProductPrice(double price) {
        List<Products> productsList= productRepository.findProductsByPrice(price);
        List<ProductsDTO> productsDTOList = new ArrayList<>();
        if(productsList!=null){
            for (Products temp: productsList){
                productsDTOList.add(productConvert.toDTO(temp));
            }
            return productsDTOList;
        } else
        {
            return null;
        }
    }

    @Override
    public List<ProductsDTO> searchProductPriceRage(double startprice,double endprice) {
        List<Products> productsList= productRepository.findProductsByPriceBetween(startprice,endprice);
        List<ProductsDTO> productsDTOList = new ArrayList<>();
        if(productsList!=null){
            for (Products temp: productsList){
                productsDTOList.add(productConvert.toDTO(temp));
            }
            return productsDTOList;
        } else
        {
            return null;
        }
    }

    @Override
    public List<ProductsDTO> searchProductByCategoryName(String name) {
        List<Products> productsList = productRepository.findProductsByCategory_Name(name);
        List<ProductsDTO> productsDTOList = new ArrayList<>();
        if(productsList!=null){
            for (Products temp: productsList){
                productsDTOList.add(productConvert.toDTO(temp));
            }
            return productsDTOList;
        } else
        {
            return null;
        }
    }
}
