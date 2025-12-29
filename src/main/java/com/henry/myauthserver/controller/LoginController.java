package com.henry.myauthserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Value("${oauth2.redirect-uri:http://localhost:3000}")
    private String redirectUri;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "redirect:" + redirectUri; // Redirect to frontend application
    }
}
