package com.dafiti.challenge.repository;

import com.dafiti.challenge.model.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    
}
