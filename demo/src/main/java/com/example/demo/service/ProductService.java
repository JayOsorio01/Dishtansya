package com.example.demo.service;

import com.example.demo.repository.ProductRepository;
import com.example.demo.model.ProductModel;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public ProductService() {
		ProductModel product = new ProductModel(1L, 9999L);
		productRepository.save(product);
    }
	
	public boolean placeOrder(Integer product_id, Integer quantity) {
        Optional<ProductModel> productOptional = productRepository.findByProduct(product_id);

        if (productOptional.isPresent()) {
            ProductModel product = productOptional.get();
            if (product.getQuantity() >= quantity) {
                product.setQuantity(product.getQuantity() - quantity);
                productRepository.save(product);
                return true;
            }
        }
        return false;
    }
	
	
}
