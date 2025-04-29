package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.Entities.Alumni;
import com.example.gestionAlumni.Repos.AdminRepository;
import com.example.gestionAlumni.Repos.AlumniRepository;
import com.example.gestionAlumni.Services.AdminService;
import com.example.gestionAlumni.Services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PutMapping("/approve/{id}")
    public ResponseEntity<Alumni> approve(@PathVariable Long id) {
        return ResponseEntity.ok(adminService.approveAlumni(id));
    }

    @DeleteMapping("/reject/{id}")
    public ResponseEntity<String> reject(@PathVariable Long id) {
        adminService.rejectAlumni(id);
        return ResponseEntity.ok("Alumni rejected and notified");
    }
}
