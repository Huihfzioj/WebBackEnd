package com.example.gestionAlumni.Services;

import com.example.gestionAlumni.Entities.Alumni;
import com.example.gestionAlumni.Repos.AlumniRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class AlumniService {
    @Autowired
    AlumniRepository alumniRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Alumni authenticate(String email, String rawPassword) {
        Alumni alumni = alumniRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(rawPassword, alumni.getPassword())) {
            throw new RuntimeException("Invalid alumni credentials");
        }

        return alumni;
    }
    public Alumni signup(Alumni alumni) {
        if (alumniRepository.findByEmail(alumni.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        alumni.setPassword(passwordEncoder.encode(alumni.getPassword()));
        return alumniRepository.save(alumni);
    }
}
