package com.example.demo.repository;

import com.example.demo.model.ProductModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
	boolean existsByProductId(Integer productId);
	Optional<ProductModel> findByProductId(Integer productId);
}
