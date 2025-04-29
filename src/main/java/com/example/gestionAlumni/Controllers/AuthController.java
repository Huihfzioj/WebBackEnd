package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.Repos.AlumniRepository;
import com.example.gestionAlumni.Repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/user-type")
    public ResponseEntity<String> getUserType(@RequestParam String email) {
        if (alumniRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.ok("alumni");
        } else if (studentRepository.findByEmail(email).isPresent()) {
            return ResponseEntity.ok("student");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}
