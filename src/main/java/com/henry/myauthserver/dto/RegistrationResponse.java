package com.henry.myauthserver.dto;

public class RegistrationResponse {
    private String message;
    private String username;

    public RegistrationResponse(String message, String username) {
        this.message = message;
        this.username = username;
    }

    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}