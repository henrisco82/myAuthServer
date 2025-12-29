package com.henry.myauthserver.controller;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.ModelAndView;

import static org.junit.jupiter.api.Assertions.*;

class LoginControllerTest {

    private final LoginController loginController = new LoginController();

    @Test
    void login_ReturnsLoginView() {
        // When
        String result = loginController.login();

        // Then
        assertEquals("login", result);
    }

    @Test
    void home_ReturnsRedirectToRoot() {
        // When
        String result = loginController.home();

        // Then
        assertEquals("redirect:/", result);
    }
}
