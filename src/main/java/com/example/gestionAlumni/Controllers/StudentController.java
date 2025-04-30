package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.DTO.StudentUpdateDTO;
import com.example.gestionAlumni.Entities.Student;
import com.example.gestionAlumni.Services.LoginRequest;
import com.example.gestionAlumni.Services.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    StudentService studentService;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Student student) {
        try {
            Student createdStudent = studentService.signup(student);
            return ResponseEntity.ok(createdStudent);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            Student student = studentService.authenticate(
                    loginRequest.getEmail(),
                    loginRequest.getPassword()
            );
            return ResponseEntity.ok(student);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/{id}/complete-profile")
    public ResponseEntity<Student> completeProfile(
            @PathVariable Long id,
            @RequestParam(required = false) String speciality,
            @RequestParam(required = false) String searchType,
            @RequestParam(required = false) Integer predictedGradYear,
            @RequestParam(required = false) String skills,
            @RequestParam(value = "document", required = false) MultipartFile document) {

        Student updatedStudent = studentService.completeProfile(id, speciality, searchType, predictedGradYear, skills, document);
        return ResponseEntity.ok(updatedStudent);
    }

    @PutMapping("/{id}/update-profile")
    public ResponseEntity<Student> updateProfile(
            @PathVariable Long id,
            @RequestBody Student updatedData) {
        Student updatedStudent = studentService.updateProfile(id, updatedData);
        return ResponseEntity.ok(updatedStudent);
    }
    @PostMapping("/{id}/upload-document")
    public ResponseEntity<String> uploadDocument(
            @PathVariable Long id,
            @RequestParam("name") String name,
            @RequestParam("document") MultipartFile document,
            @RequestParam("description") String description) {
        try {
            studentService.saveDocument(id, name, document, description);
            return ResponseEntity.ok("Document uploaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    //@GetMapping("/infos")
    //public
}
