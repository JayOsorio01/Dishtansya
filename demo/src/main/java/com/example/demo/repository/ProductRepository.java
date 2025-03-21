package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Long> {
	Optional<ProductModel> findByProduct(int product_id);
}
