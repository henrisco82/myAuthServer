package com.henry.myauthserver.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @Value("${frontend.url:http://localhost:3000}")
    private String frontendUrl;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "redirect:" + frontendUrl; // Redirect to frontend application
    }
}
