package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.Entities.Alumni;
import com.example.gestionAlumni.LoginRequest;
import com.example.gestionAlumni.Services.AlumniService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alumni")
public class AlumniController {

    @Autowired
    private final AlumniService alumniService;

    public AlumniController(AlumniService alumniService) {
        this.alumniService = alumniService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Alumni> signup(@RequestBody Alumni alumni) {
        Alumni savedAlumni = alumniService.signup(alumni);
        return ResponseEntity.ok(savedAlumni);
    }
    @PostMapping("/login")
    public ResponseEntity<Alumni> login(@RequestBody LoginRequest loginRequest) {
        Alumni alumni = alumniService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        return ResponseEntity.ok(alumni);
    }
}
