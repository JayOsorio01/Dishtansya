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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Optional;

@SpringBootTest
@AutoConfigureMockMvc
class DishtansyaApplicationLoginTest {

	@Autowired
    private MockMvc mockMvc;

    @Autowired
    private RegistrationRepository registrationRepository;

    @BeforeEach
    public void setup() {
    	registrationRepository.deleteAll();
    }

    @Test
    public void loginFailed () throws Exception {

        String existingEmail = "backend@multisyscorp.com";
        String password = "test123";
        RegistrationModel existingUser = new RegistrationModel();
        existingUser.setEmail(existingEmail);
        existingUser.setPassword(password);
        registrationRepository.save(existingUser);

        String requestBody = """
                {
                    "email": "backend123123@multisyscorp.com",
            		"password": "test121231233"
                }
            """;

            mockMvc.perform(post("/login")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestBody))
                    .andExpect(status().isUnauthorized())
                    .andExpect(content().string("Invalid credentials"));
            
        
    }

    @Test
    public void whenEmailDoesNotExist_shouldRegisterUserSuccessfully() throws Exception {
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

    	mockMvc.perform(post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string("Login successful"));
    }

}
