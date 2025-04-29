package com.example.gestionAlumni.Services;
import com.example.gestionAlumni.Entities.Alumni;

import com.example.gestionAlumni.Entities.Alumni;
import com.example.gestionAlumni.Repos.AlumniRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class AlumniService {
    @Autowired
    private AlumniRepository alumniRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailService emailService; // Inject the EmailService

    // Signup: Generate token and send verification email
    public Alumni signup(Alumni alumni) {
        if (alumniRepository.findByEmail(alumni.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        alumni.setPassword(passwordEncoder.encode(alumni.getPassword()));
        alumni.setVerified(false); // Waiting for admin approval
        alumni.setVerificationToken(null); // No token needed anymore

        return alumniRepository.save(alumni);
    }

    // Login: Allow only verified alumni
    public Alumni authenticate(String email, String rawPassword) {
        Alumni alumni = alumniRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(rawPassword, alumni.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!alumni.isVerified()) {
            throw new RuntimeException("Account not verified. Check your email.");
        }

        return alumni;
    }

    // Verify alumni using token
    public Alumni verifyAlumni(String token) {
        Alumni alumni = alumniRepository.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid token"));

        alumni.setVerified(true);
        alumni.setVerificationToken(null); // Clear token after verification
        return alumniRepository.save(alumni);
    }
}