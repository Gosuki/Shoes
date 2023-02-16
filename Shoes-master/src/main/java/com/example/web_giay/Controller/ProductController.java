package com.example.web_giay.Controller;

import com.example.web_giay.dto.BaseResponse;
import com.example.web_giay.dto.ProductsDTO;
import com.example.web_giay.repository.ProductRepository;
import com.example.web_giay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductsController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(value = "/new")
    public ResponseEntity<?> createProduct(@RequestBody ProductsDTO productsDTO) {
        if (productRepository.findProductsByName(productsDTO.getName()) == null) {
            return ResponseEntity.ok(productService.saveProduct(productsDTO));
        } else {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null, "Product available"));
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateProductById(@RequestBody ProductsDTO productsDTO, @PathVariable(value = "id") Long id) {
        if (productRepository.findOneById(id) != null) {
            productsDTO.setId(id);
            return ResponseEntity.ok(productService.saveProduct(productsDTO));
        } else {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null, "Id not available"));
        }
    }
    @PutMapping(value = "/update/name")
    public ResponseEntity<?> updateProductByName(@RequestBody ProductsDTO productsDTO, @RequestParam(value = "name") String name) {
        if (productRepository.findProductsByName(name) != null) {
            productsDTO.setId(productRepository.findProductsByName(name).getId());
            return ResponseEntity.ok(productService.saveProduct(productsDTO));
        } else {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null, "Name not available"));
        }
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<?> deleteProductById(@RequestBody Long[] ids) {
        return ResponseEntity.ok(productService.delete(ids));
    }

    @GetMapping(value = "/search/{id}")
    public ResponseEntity<?> searchProductV1(@PathVariable(value = "id") Long id) {
        if(productService.searchProductById(id)!=null){
            return ResponseEntity.ok(productService.searchProductById(id));
        }else {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null, "Id not available"));
        }
    }
    @GetMapping(value = "/search")
    public ResponseEntity<?> searchProductV2(@RequestParam(name = "id") Long id)  {
        if(productService.searchProductById(id)!=null){
            return ResponseEntity.ok(productService.searchProductById(id));
        }else {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null, "Id not available"));
        }
    }
    @GetMapping(value = "/search/name")
    public ResponseEntity<?> searchProductByName(@RequestParam(name = "name") String name)  {
        if(productService.searchProductByName(name)!=null){
            return ResponseEntity.ok(productService.searchProductByName(name));
        }else {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null, "Name not available"));
        }
    }
    @GetMapping(value = "/search/name-like")
    public ResponseEntity<?> searchProductLikeName(@RequestParam(name = "name") String name)  {
        return ResponseEntity.ok(productService.searchListProductByNameLike(name));
    }
    @GetMapping(value = "/search/price")
    public ResponseEntity<?> searchProductPrice(@RequestParam(name = "price") double price)  {
        return ResponseEntity.ok(productService.searchProductPrice(price));
    }
    @GetMapping(value = "/search/pricerage")
    public ResponseEntity<?> searchProductPriceRage(@RequestParam(name = "startprice") double startprice,@RequestParam(name = "endprice") double endprice)  {
        return ResponseEntity.ok(productService.searchProductPriceRage(startprice,endprice));
    }
    @GetMapping(value = "/search/category")
    public ResponseEntity<?> searchProductByCategory(@RequestParam(name = "namecategory") String namnamecategory){
        return ResponseEntity.ok(productService.searchProductByCategoryName(namnamecategory));
    }

}

