package com.example.web_giay.Controller;

import com.example.web_giay.dto.BaseResponse;
import com.example.web_giay.dto.ProductDTO;
import com.example.web_giay.repository.ProductRepository;
import com.example.web_giay.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping(value = "/new")
    public ResponseEntity<?> createProduct(@RequestBody ProductDTO productDTO) {
        if (productRepository.findProductByName(productDTO.getName()) == null) {
            return ResponseEntity.ok(productService.saveProduct(productDTO));
        } else {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null, "Product available"));
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> updateProductById(@RequestBody ProductDTO productDTO, @PathVariable(value = "id") Long id) {
        if (productRepository.findOneById(id) != null) {
            productDTO.setId(id);
            return ResponseEntity.ok(productService.saveProduct(productDTO));
        } else {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(), null, "Id not available"));
        }
    }
    @PutMapping(value = "/update/name")
    public ResponseEntity<?> updateProductByName(@RequestBody ProductDTO productDTO, @RequestParam(value = "name") String name) {
        if (productRepository.findProductByName(name) != null) {
            productDTO.setId(productRepository.findProductByName(name).getId());
            return ResponseEntity.ok(productService.saveProduct(productDTO));
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
    @GetMapping(value = "/search/name")
    public ResponseEntity<?> searchProductByName(@RequestParam(required = false) String name,
                                                 @RequestParam(required = false) double price)
    {
        if(productService.searchProductByNameLikeOrPrice(name,price)!=null) {
            return ResponseEntity.ok(productService.searchProductByNameLikeOrPrice(name, price));
        } else {
            return ResponseEntity.ok(new BaseResponse(HttpStatus.BAD_REQUEST.value(),null, "Name or price not available"));
        }
    }
    @GetMapping(value = "/search/priceRage")
    public ResponseEntity<?> searchProductPriceRage(@RequestParam(name = "startPrice") double startprice,@RequestParam(name = "endPrice") double endprice)  {
        return ResponseEntity.ok(productService.searchProductPriceRage(startprice,endprice));
    }
    @GetMapping(value = "/search/category")
    public ResponseEntity<?> searchProductByCategory(@RequestParam(name = "nameCategory") String nameCategory){
        return ResponseEntity.ok(productService.searchProductByCategoryName(nameCategory));
    }

}

