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
	
	public ProductModel createProduct(ProductModel productModel) {
		if (!productRepository.existsByProductId(productModel.getProductId())) {
            return productRepository.save(productModel);
        } else {
            return null;
        }
    }
	
	public boolean placeOrder(Integer productId, Integer quantity) {
        Optional<ProductModel> productOptional = productRepository.findByProductId(productId);

        if (productOptional.isPresent()) {
            ProductModel productModel = productOptional.get();
            if (productModel.getQuantity() >= quantity) {
            	productModel.setQuantity(productModel.getQuantity() - quantity);
                productRepository.save(productModel);
                return true;
            }
        }
        return false;
    }
	
	
}
