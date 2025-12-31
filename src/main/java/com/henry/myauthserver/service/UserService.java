package com.henry.myauthserver.service;

import com.henry.myauthserver.dto.RegistrationRequest;
import com.henry.myauthserver.entity.AppUser;
import com.henry.myauthserver.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public AppUser registerUser(RegistrationRequest request) {
        // Check if the username exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        // Check if email exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Create new user
        AppUser user = new AppUser();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEnabled(true);
        user.setRole("USER");

        return userRepository.save(user);
    }

    public long getUserCount() {
        return userRepository.count();
    }
}

jdbc:postgresql://myauthserver_db_user:JLXNfztyd9q8lH524bhtT00gL8VT8guT@dpg-d59cjs3uibrs73b7rimg-a/myauthserver_db