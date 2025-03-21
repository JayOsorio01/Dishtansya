package com.example.demo.controller;

import com.example.demo.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping("/order")
    public ResponseEntity<String> orderProduct(@RequestParam("product_id") int productId, @RequestParam("quantity") int quantity) {
       
        boolean success = productService.placeOrder(productId, quantity);
        if (!success) {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE)
                    .body("Failed to order this product due to unavailability of the stock");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("You have successfully ordered this product.");
    }
}
