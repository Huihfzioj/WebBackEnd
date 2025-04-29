package com.example.gestionAlumni.Controllers;


import com.example.gestionAlumni.DTO.StudentUpdateDTO;
import com.example.gestionAlumni.Entities.Student;
import com.example.gestionAlumni.Services.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

	@Autowired
    private StudentService studentService;

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
