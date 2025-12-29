package com.henry.myauthserver.dto;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationRequestTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void validRegistrationRequest_NoValidationErrors() {
        // Given
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("validuser");
        request.setEmail("valid@example.com");
        request.setPassword("validpassword123");

        // When
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    void invalidUsername_Null_ValidationError() {
        // Given
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername(null);
        request.setEmail("valid@example.com");
        request.setPassword("validpassword123");

        // When
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Username is required", violations.iterator().next().getMessage());
    }

    @Test
    void invalidUsername_Blank_ValidationError() {
        // Given
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("   "); // Blank but not empty
        request.setEmail("valid@example.com");
        request.setPassword("validpassword123");

        // When
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertTrue(violations.size() >= 1);
        // Check that at least one violation mentions username
        boolean hasUsernameViolation = violations.stream()
                .anyMatch(v -> v.getMessage().contains("Username"));
        assertTrue(hasUsernameViolation);
    }

    @Test
    void invalidUsername_TooShort_ValidationError() {
        // Given
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("ab"); // Less than 3 characters
        request.setEmail("valid@example.com");
        request.setPassword("validpassword123");

        // When
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Username must be between 3 and 50 characters", violations.iterator().next().getMessage());
    }

    @Test
    void invalidUsername_TooLong_ValidationError() {
        // Given
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("a".repeat(51)); // More than 50 characters
        request.setEmail("valid@example.com");
        request.setPassword("validpassword123");

        // When
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Username must be between 3 and 50 characters", violations.iterator().next().getMessage());
    }

    @Test
    void invalidEmail_Null_ValidationError() {
        // Given
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("validuser");
        request.setEmail(null);
        request.setPassword("validpassword123");

        // When
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Email is required", violations.iterator().next().getMessage());
    }

    @Test
    void invalidEmail_InvalidFormat_ValidationError() {
        // Given
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("validuser");
        request.setEmail("invalid-email");
        request.setPassword("validpassword123");

        // When
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Email must be valid", violations.iterator().next().getMessage());
    }

    @Test
    void invalidPassword_Null_ValidationError() {
        // Given
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("validuser");
        request.setEmail("valid@example.com");
        request.setPassword(null);

        // When
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Password is required", violations.iterator().next().getMessage());
    }

    @Test
    void invalidPassword_TooShort_ValidationError() {
        // Given
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("validuser");
        request.setEmail("valid@example.com");
        request.setPassword("12345"); // Less than 6 characters

        // When
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        assertEquals("Password must be at least 6 characters", violations.iterator().next().getMessage());
    }

    @Test
    void multipleValidationErrors_AllReported() {
        // Given
        RegistrationRequest request = new RegistrationRequest();
        request.setUsername("ab"); // Too short
        request.setEmail("invalid-email"); // Invalid format
        request.setPassword("123"); // Too short

        // When
        Set<ConstraintViolation<RegistrationRequest>> violations = validator.validate(request);

        // Then
        assertFalse(violations.isEmpty());
        assertEquals(3, violations.size());
    }

    @Test
    void gettersAndSetters_WorkCorrectly() {
        // Given
        RegistrationRequest request = new RegistrationRequest();

        // When
        request.setUsername("testuser");
        request.setEmail("test@example.com");
        request.setPassword("password123");

        // Then
        assertEquals("testuser", request.getUsername());
        assertEquals("test@example.com", request.getEmail());
        assertEquals("password123", request.getPassword());
    }
}
