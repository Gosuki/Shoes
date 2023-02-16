package com.example.web_giay.repository;

import com.example.web_giay.entity.Description;
import com.example.web_giay.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DescriptionRepository extends JpaRepository<Description,Long> {
    Description findDescriptionByProduct(Product product);
}
