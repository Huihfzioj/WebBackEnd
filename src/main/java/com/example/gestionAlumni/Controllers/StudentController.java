package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.Entities.Student;
import com.example.gestionAlumni.LoginRequest;
import com.example.gestionAlumni.Services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PutMapping("/{id}/complete-profile")
    public ResponseEntity<Student> completeProfile(@PathVariable Long id,
                                                 @RequestParam String speciality,
                                                 @RequestParam String searchType,
                                                 @RequestParam int predictedGradYear,
                                                 @RequestParam("document") MultipartFile document) {
        Student updatedStudent = studentService.completeProfile(id, speciality, searchType, predictedGradYear, document);
        return ResponseEntity.ok(updatedStudent);
    }
}
