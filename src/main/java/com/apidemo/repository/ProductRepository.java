package com.apidemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apidemo.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{

}
