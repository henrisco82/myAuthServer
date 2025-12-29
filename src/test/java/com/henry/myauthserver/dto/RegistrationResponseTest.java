package com.henry.myauthserver.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationResponseTest {

    @Test
    void constructor_SetsFieldsCorrectly() {
        // Given
        String message = "User registered successfully";
        String username = "testuser";

        // When
        RegistrationResponse response = new RegistrationResponse(message, username);

        // Then
        assertEquals(message, response.getMessage());
        assertEquals(username, response.getUsername());
    }

    @Test
    void gettersAndSetters_WorkCorrectly() {
        // Given
        RegistrationResponse response = new RegistrationResponse("initial message", "initial user");

        // When
        response.setMessage("updated message");
        response.setUsername("updated user");

        // Then
        assertEquals("updated message", response.getMessage());
        assertEquals("updated user", response.getUsername());
    }

    @Test
    void toString_Works() {
        // Given
        RegistrationResponse response = new RegistrationResponse("Success", "john");

        // When
        String toString = response.toString();

        // Then
        assertNotNull(toString);
        assertFalse(toString.isEmpty());
    }
}
