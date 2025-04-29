package com.example.gestionAlumni.Services;

import com.example.gestionAlumni.Entities.Student;
import com.example.gestionAlumni.Repos.StudentRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
    public Student completeProfile(Long id, String speciality, String searchType, int predictedGradYear, MultipartFile document) {
        // 1. Récupérer l'étudiant à partir de l'ID
        Student student = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student not found"));

        // 2. Mettre à jour les informations du profil
        student.setSpeciality(speciality);
        student.setPredictedGradYear(predictedGradYear);
        student.setSearchType(searchType);

        // 3. Ajouter et gérer le document
        if (document != null && !document.isEmpty()) {
            // Nom du fichier à stocker dans la base de données
            String documentName = document.getOriginalFilename();

            // Ici, tu peux sauvegarder le fichier sur ton disque ou dans un service de stockage
            // Exemple de sauvegarde du nom du fichier
            student.setDocumentName(documentName);

            // Si tu veux transférer le fichier physiquement, tu peux décommenter cette ligne :
            // String filePath = "path/to/storage/" + documentName;
            // document.transferTo(new File(filePath));
        }

        // 4. Sauvegarder l'étudiant mis à jour dans la base de données
        return studentRepository.save(student);
    }
}
