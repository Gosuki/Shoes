package com.example.web_giay.repository;

import com.example.web_giay.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Products,Long> {
    @Query(value = "SELECT a FROM Products a where a.id =?1")
    Products findOneById(Long id);
    Products findProductsByName(String name);

    @Query(value = "SELECT p FROM Products p WHERE p.name LIKE %:name% ")
    List<Products> searchProductsByNameLike(@Param("name") String name);

    //    @Query(value = "select p from Products p where p.price=?1")
    List<Products> findProductsByPrice(Double price);

    List<Products> findProductsByPriceBetween(Double startprice,Double endprice);

    List<Products> findProductsByCategory_Name(String name);

}
