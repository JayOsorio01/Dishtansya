package com.example.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import com.example.demo.model.RegistrationModel;
import com.example.demo.repository.RegistrationRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DishtansyaApplicationTests {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private RegistrationRepository registrationRepository;

    @BeforeEach
    public void setup() {
    	registrationRepository.deleteAll();
    }

    @Test
    public void whenEmailExists_shouldReturnEmailAlreadyTaken() throws Exception {

        String existingEmail = "backend@multisyscorp.com";
        String password = "test123";
        RegistrationModel existingUser = new RegistrationModel();
        existingUser.setEmail(existingEmail);
        existingUser.setPassword(password);
        registrationRepository.save(existingUser);

        String requestBody = """
            {
                "email": "backend@multisyscorp.com",
        		"password": "test123"
            }
        """;

        // Act & Assert
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email already taken"));
    }

    @Test
    public void whenEmailDoesNotExist_shouldRegisterUserSuccessfully() throws Exception {

        String requestBody = """
            {
                "email": "backend@multisyscorp.com",
        		"password": "test123"
            }
        """;

        // Act & Assert
        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(content().string("User successfully registered"));
    }

}
