package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.DTO.DocumentResponse;
import com.example.gestionAlumni.DTO.InfoDTO;
import com.example.gestionAlumni.DTO.StudentUpdateDTO;
import com.example.gestionAlumni.Entities.Student;
import com.example.gestionAlumni.Services.LoginRequest;
import com.example.gestionAlumni.Services.StudentService;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
            @RequestParam("description") String description,
            @RequestParam("date") String dateString) {
        try {
            // Parse the ISO string manually (compatible with `toISOString()` from JS)
            SimpleDateFormat isoFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            Date date = isoFormat.parse(dateString);

            studentService.saveDocument(id, name, document, description, date);
            return ResponseEntity.ok("Document uploaded successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: " + e.getMessage());
        }
    }
    @GetMapping("/{id}/info")
    public ResponseEntity<InfoDTO> getStudentInfo(@PathVariable Long id) {
        InfoDTO info = studentService.getStudentInfo(id);
        return ResponseEntity.ok(info);
    }
    @GetMapping("/{id}/documents")
    public ResponseEntity<List<DocumentResponse>> getDocuments(@PathVariable Long id) {
        Student student = studentService.findById(id).orElseThrow();

        if (student.getDocument() == null) {
            return ResponseEntity.ok(Collections.emptyList());
        }

        String downloadUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/student/")
                .path(id.toString())
                .path("/download-document")
                .toUriString();

        String fileType = student.getDocumentName().toLowerCase().endsWith(".pdf") ? "PDF" : "FILE";

        String formattedDate = new SimpleDateFormat("MMM dd, yyyy").format(student.getDateUploadDoc());

        DocumentResponse response = new DocumentResponse(
                student.getDocumentName(),
                student.getDescription(),
                fileType,
                formattedDate,
                downloadUrl
        );

        return ResponseEntity.ok(List.of(response));
    }
    @GetMapping("/{id}/download-document")
    public ResponseEntity<byte[]> downloadDocument(@PathVariable Long id) {
        Student student = studentService.findById(id).orElseThrow();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + student.getDocumentName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(student.getDocument());
    }
}
