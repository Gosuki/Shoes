package com.example.web_giay.repository;

import com.example.web_giay.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    Category findOneByName(String name);
}
