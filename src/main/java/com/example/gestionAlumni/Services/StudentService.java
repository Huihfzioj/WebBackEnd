package com.example.gestionAlumni.Services;

import com.example.gestionAlumni.Entities.Alumni;
import com.example.gestionAlumni.Entities.Student;
import com.example.gestionAlumni.Repos.StudentRepository;
import lombok.AccessLevel;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Student authenticate(String email, String rawPassword) {
        Student student = studentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!passwordEncoder.matches(rawPassword, student.getPassword())) {
            throw new RuntimeException("Invalid alumni credentials");
        }

        return student;
    }
    public Student signup(Student student) {
        if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }
}
