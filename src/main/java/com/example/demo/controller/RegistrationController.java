package com.example.demo.controller;

import com.example.demo.model.ProductModel;
import com.example.demo.model.RegistrationModel;
import com.example.demo.service.ProductService;
import com.example.demo.service.RegistrationService;

import jakarta.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/register")
    public ResponseEntity<String> createParticipant(@RequestBody RegistrationModel registrationModel) {
		
		if (registrationService.emailDuplication(registrationModel.getEmail())) {
	        return ResponseEntity.badRequest().body("Email already taken");
	    }
		
		registrationService.createUser(registrationModel);
		return new ResponseEntity<>("User successfully registered", HttpStatus.CREATED);
    }
	
	@PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody RegistrationModel registrationModel) {
		if (registrationModel.getEmail() == null || registrationModel.getPassword() == null) {
	        return ResponseEntity.badRequest().body("Email and password must not be null");
	    }
		
        boolean isValidUser = registrationService.validateUser(registrationModel.getEmail(), registrationModel.getPassword());

        if (!isValidUser) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }

        return ResponseEntity.ok("Login successful");
    }
	
	@PostConstruct
    public void initData() {
        ProductModel product1 = new ProductModel();
        product1.setProduct_id(1);
        product1.setQuantity(9999);

        ProductModel product2 = new ProductModel();
        product2.setProduct_id(2);
        product2.setQuantity(9999);
        
        productService.createProduct(product1);
        productService.createProduct(product2);
    }
	
	@PostMapping("/order")
	public ResponseEntity<String> orderProduct(@RequestBody ProductModel productModel) {
	 
		boolean success = productService.placeOrder(productModel.getProductId(), productModel.getQuantity());
		if (!success) {
		    return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Failed to order this product due to unavailability of the stock");
		}
		
		return ResponseEntity.status(HttpStatus.CREATED).body("You have successfully ordered this product.");
	}

}
