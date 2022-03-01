package com.dafiti.challenge.repository;

import com.dafiti.challenge.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>{
    
}
