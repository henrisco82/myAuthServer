package com.henry.myauthserver.controller;

import com.henry.myauthserver.dto.RegistrationRequest;
import com.henry.myauthserver.dto.RegistrationResponse;
import com.henry.myauthserver.entity.AppUser;
import com.henry.myauthserver.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegistrationRequest request) {
        try {
            AppUser user = userService.registerUser(request);
            RegistrationResponse response = new RegistrationResponse(
                    "User registered successfully",
                    user.getUsername()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/db-info")
    public ResponseEntity<?> getDatabaseInfo() {
        try {
            // This will help debug what database we're actually connecting to
            long userCount = userService.getUserCount();
            return ResponseEntity.ok(Map.of(
                "userCount", userCount,
                "database", "connected",
                "profile", System.getProperty("spring.profiles.active", "default")
            ));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponse("Database connection failed: " + e.getMessage()));
        }
    }

    // Error response class
    public static class ErrorResponse {
        private String error;

        public ErrorResponse(String error) {
            this.error = error;
        }

        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }
}