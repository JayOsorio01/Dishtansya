package com.example.demo.service;

import com.example.demo.repository.RegistrationRepository;
import com.example.demo.model.RegistrationModel;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
	
	@Autowired
	private RegistrationRepository registrationRepository;

	public boolean emailDuplication(String email) {
	    return registrationRepository.existsByEmail(email);
	}
	
	public RegistrationModel createUser(RegistrationModel registrationModel) {
        return registrationRepository.save(registrationModel);
    }
	
	public boolean validateUser(String email, String password) {
        Optional<RegistrationModel> userOptional = registrationRepository.findByEmail(email);
        return userOptional.isPresent() && userOptional.get().getPassword().equals(password);
    }
	
	

}
