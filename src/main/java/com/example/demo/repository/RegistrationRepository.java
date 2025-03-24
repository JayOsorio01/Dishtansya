package com.example.demo.repository;

import com.example.demo.model.RegistrationModel;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationModel, Long> {
	boolean existsByEmail(String email);
	Optional<RegistrationModel> findByEmail(String email);
}
