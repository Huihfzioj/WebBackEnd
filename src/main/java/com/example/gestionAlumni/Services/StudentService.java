package com.example.gestionAlumni.Services;

import com.example.gestionAlumni.DTO.InfoDTO;
import com.example.gestionAlumni.Entities.Student;
import com.example.gestionAlumni.Repos.StudentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Méthode pour authentifier un étudiant
    public Student authenticate(String email, String rawPassword) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(rawPassword, student.getPassword())) {
            throw new RuntimeException("Invalid alumni credentials");
        }

        return student;
    }

    // Méthode d'inscription d'un nouvel étudiant
    public Student signup(Student student) {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    // Méthode pour compléter le profil de l'étudiant
    public Student completeProfile(Long id, String speciality, String searchType, Integer predictedGradYear, String skills, MultipartFile document) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        if (speciality != null && !speciality.isBlank()) {
            student.setSpeciality(speciality);
        }
        if (searchType != null && !searchType.isBlank()) {
            student.setSearchType(searchType);
        }
        if (predictedGradYear != null && predictedGradYear > 0) {
            student.setPredictedGradYear(predictedGradYear);
        }
        if (skills != null && !skills.isBlank()) {
            student.setSkill(skills);
        }
        if (document != null && !document.isEmpty()) {
            try {
                student.setDocument(document.getBytes());
            } catch (IOException e) {
                throw new RuntimeException("Failed to read document", e);
            }
        }

        return studentRepository.save(student);
    }
    public Student updateProfile(Long id, Student updatedData) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setSkill(updatedData.getSkill());
        student.setSpeciality(updatedData.getSpeciality());
        student.setSearchType(updatedData.getSearchType());
        student.setPredictedGradYear(updatedData.getPredictedGradYear());
        student.setSkill(updatedData.getSkill());
        return studentRepository.save(student);
    }
    public void saveDocument(Long studentId, String name, MultipartFile document, String description, Date date) throws IOException {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        // Compress the file into a zip
        byte[] zippedBytes = zipFile(document.getOriginalFilename(), document.getBytes());

        student.setDocument(zippedBytes); // Save compressed bytes
        student.setDocumentName(name + ".zip");
        student.setDescription(description);
        student.setDateUploadDoc(date);
        studentRepository.save(student);
    }
    private byte[] zipFile(String fileName, byte[] fileData) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ZipOutputStream zos = new ZipOutputStream(baos)) {
            ZipEntry entry = new ZipEntry(fileName);
            zos.putNextEntry(entry);
            zos.write(fileData);
            zos.closeEntry();
        }
        return baos.toByteArray();
    }
    public InfoDTO getStudentInfo(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        return new InfoDTO(student);
    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }
}
