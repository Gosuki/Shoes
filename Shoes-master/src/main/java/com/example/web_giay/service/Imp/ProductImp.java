package com.example.web_giay.service.Imp;

import com.example.web_giay.Convert.ProductConvert;
import com.example.web_giay.dto.ProductDTO;
import com.example.web_giay.entity.Category;
import com.example.web_giay.entity.Product;
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
               descriptionRepository.delete(descriptionRepository.findDescriptionByProduct(productRepository.findOneById(item)));
           }
           else {
               return "Not found ID";
           }
       }
        return "Delete Successful";
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = new Product();
        if (productDTO.getId()!=null){
          Product oldproduct = productRepository.findOneById(productDTO.getId());
            product = productConvert.toEntity(productDTO,oldproduct);
        } else {
            product = productConvert.toEntity(productDTO);
        }
        Category category= categoryRepository.findOneByName(productDTO.getCategoryname());
        product.setCategory(category);
        product = productRepository.save(product);
        return productConvert.toDTO(product);
    }

    @Override
    public ProductDTO searchProductById(Long id) {
        Product product=productRepository.findOneById(id);
        if(product!=null){
            return productConvert.toDTO(product);
        }else{
            return null;
        }
    }
    @Override
    public List<ProductDTO> searchProductByNameLikeOrPrice(String name, double price) {
        if (name != null) {
            if (price > 0) {
                List<Product> productList= productRepository.searchProductByNameLikeOrPrice(name, price);
                return getProductDTOS(productList);
            } else {
                List<Product> productList= productRepository.searchProductByNameLike(name);
                return getProductDTOS(productList);
            }
        } else if (price>0) {
            List<Product> productList= productRepository.findProductByPrice(price);
        } else {
            return getProductDTOS(productRepository.findAll());
        }
        return null;
    }

    @Override
    public List<ProductDTO> searchProductPriceRage(double startprice, double endprice) {
        List<Product> productList = productRepository.findProductByPriceBetween(startprice,endprice);
        return getProductDTOS(productList);
    }

    @Override
    public List<ProductDTO> searchProductByCategoryName(String name) {
        List<Product> productList = productRepository.findProductByCategoryName(name);
        return getProductDTOS(productList);
    }

    private List<ProductDTO> getProductDTOS(List<Product> productList) {
        List<ProductDTO> productDTOList = new ArrayList<>();
        if(productList !=null){
            for (Product temp: productList){
                productDTOList.add(productConvert.toDTO(temp));
            }
            return productDTOList;
        } else
        {
            return null;
        }
    }
}
