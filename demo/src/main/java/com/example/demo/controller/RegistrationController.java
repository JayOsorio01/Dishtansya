package com.example.demo.controller;

import com.example.demo.model.RegistrationModel;
import com.example.demo.service.RegistrationService;
import com.example.demo.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class RegistrationController {
	
	@Autowired
	private RegistrationService registrationService;
	
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
	
	

}
