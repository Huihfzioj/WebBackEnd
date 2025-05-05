package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.DTO.AlumniDto;
import com.example.gestionAlumni.DTO.AlumniInfoDto;
import com.example.gestionAlumni.DTO.AlumniProfileUpdateDTO;
import com.example.gestionAlumni.Entities.Alumni;
import com.example.gestionAlumni.Services.AlumniService;
import com.example.gestionAlumni.Services.LoginRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alumni")
public class AlumniController {
    private final AlumniService alumniService;

    @Autowired
    public AlumniController(AlumniService alumniService) {
        this.alumniService = alumniService;
    }

    // Verify account via token (clicked from email)
    @GetMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam String token) {
        try {
            alumniService.verifyAlumni(token);
            return ResponseEntity.ok("Account verified successfully!");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Signup: Now automatically sends verification email
    @PostMapping("/signup")
    public ResponseEntity<Alumni> signup(@RequestBody Alumni alumni) {
        Alumni savedAlumni = alumniService.signup(alumni);
        return ResponseEntity.ok(savedAlumni);
    }

    // Login: Rejects unverified alumni
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Alumni alumni = alumniService.authenticate(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );
            return ResponseEntity.ok(alumni);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}/info")
    public ResponseEntity<AlumniInfoDto> getAlumniInfo(@PathVariable Long id) {
        AlumniInfoDto info = alumniService.getAlumniInfo(id);
        return ResponseEntity.ok(info);
    }
    @PutMapping("/{id}/profile")
    public void updateProfile(@PathVariable Long id, @RequestBody AlumniProfileUpdateDTO dto) {
        alumniService.updateAlumniProfile(id, dto);
    }
    @GetMapping("suggestions")
    public List<AlumniDto> getAlumniSuggestions(@RequestParam Long currentUserId) {
        return alumniService.getSuggestedAlumni(currentUserId);
    }
}