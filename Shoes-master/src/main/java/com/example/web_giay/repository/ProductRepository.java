package com.example.web_giay.repository;

import com.example.web_giay.dto.ProductDTO;
import com.example.web_giay.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query(value = "SELECT a FROM Product a where a.id =?1")
    Product findOneById(Long id);
    Product findProductByName(String name);

    List<Product> findAll();
    @Query(value = "SELECT p FROM Product p WHERE p.name LIKE %:name% AND p.price = :price")
    List<Product> searchProductByNameLikeOrPrice(@Param("name") String name,@Param("price") double price);
    List<Product> searchProductByNameLike(String name);

    //    @Query(value = "select p from Products p where p.price=?1")
    List<Product> findProductByPrice(Double price);

    List<Product> findProductByPriceBetween(Double startprice, Double endprice);

    List<Product> findProductByCategoryName(String name);

}
