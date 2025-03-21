package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ProductModel {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long product_id;
    
    private String name;
    
    private Long quantity;
    
    public ProductModel(Long product_id, Long quantity) {
        this.product_id = product_id;
        this.quantity = quantity;
    }

	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	

}
