package com.example.gestionAlumni.Entities;

import com.example.gestionAlumni.Services.EmailService;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.*;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Administrator extends User {
    @ManyToMany
    @JoinTable(
            name = "admin_unverified_alumni",
            joinColumns = @JoinColumn(name = "admin_id"),
            inverseJoinColumns = @JoinColumn(name = "alumni_id")
    )
    private List<Alumni> unverifiedAlumni = new ArrayList<>();

    public void addUnverifiedAlumni(Alumni alumni) {
        this.unverifiedAlumni.add(alumni);
    }

    public void removeUnverifiedAlumni(Alumni alumni) {
        this.unverifiedAlumni.remove(alumni);
    }

    public void verifyAlumni(Alumni alumni, String applicationBaseUrl) {
        if (!unverifiedAlumni.contains(alumni)) {
            throw new IllegalArgumentException("Alumni not found in unverified list.");
        }

        // Generate a unique verification token
        String verificationToken = UUID.randomUUID().toString();
        alumni.setVerificationToken(verificationToken);

        // Send verification email
        sendVerificationEmail(alumni.getEmail(), verificationToken, applicationBaseUrl);

        // Move alumni to verified list (optional)
        unverifiedAlumni.remove(alumni);
    }

    // Helper method to send email
    private void sendVerificationEmail(String alumniEmail, String token, String baseUrl) {
        String subject = "Verify Your Alumni Account";
        String verificationLink = baseUrl + "/verify-account?token=" + token;
        String body = "Click the link below to verify your account:\n" + verificationLink;

        // Use JavaMail or a service like SendGrid to send the email
        EmailService.sendEmail(alumniEmail, subject, body);
    }

}
