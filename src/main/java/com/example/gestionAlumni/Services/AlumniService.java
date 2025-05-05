package com.example.gestionAlumni.Services;

import com.example.gestionAlumni.DTO.AlumniDto;
import com.example.gestionAlumni.DTO.AlumniInfoDto;
import com.example.gestionAlumni.Entities.Alumni;
import com.example.gestionAlumni.Entities.Student;
import com.example.gestionAlumni.Repos.AlumniRepository;
import com.example.gestionAlumni.Repos.StudentRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private StudentRepository studentRepository;

    // Signup: Generate token and send verification email
    public Alumni signup(Alumni alumni) {
        if (alumniRepository.findByEmail(alumni.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        alumni.setPassword(passwordEncoder.encode(alumni.getPassword()));
        alumni.setVerified(false); // Waiting for admin approval

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
        return alumniRepository.save(alumni);
    }
    public AlumniInfoDto getAlumniInfo(Long id) {
        Alumni alumni = alumniRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumni not found with ID: " + id));

        return new AlumniInfoDto(
                alumni.getFirstName(),
                alumni.getLastName(),
                alumni.getDepartment(),
                alumni.getGraduationYear(),
                alumni.getCurrentJob(),
                alumni.getCurrentCompany(),
                alumni.getIndustry()
        );
    }
    public List<AlumniDto> getSuggestedAlumni(Long currentUserId) {
        // Logic to fetch alumni based on the user's field, industry, or other criteria
        Student student = studentRepository.findById(currentUserId).get();
        List<Alumni> suggestedAlumni = alumniRepository.findSuggestedAlumni(student.getSpeciality());
        List<AlumniDto> alumnis = new ArrayList<>();
        for (Alumni al : suggestedAlumni) {
            AlumniDto l = new AlumniDto(al.getId(), al.getFirstName(), al.getLastName());
            return alumnis;
        }
        return alumnis;
    }
}