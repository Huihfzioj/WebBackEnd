package com.example.gestionAlumni.Services;
import com.example.gestionAlumni.Entities.Alumni;
import com.example.gestionAlumni.Repos.AdminRepository;
import com.example.gestionAlumni.Repos.AlumniRepository;
import com.example.gestionAlumni.Repos.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class AdminService {
    @Autowired
    private AdminRepository adminrepo;

    @Autowired
    private AlumniRepository alumniRepository;

    @Autowired
    private EmailService emailService;

    public Alumni approveAlumni(Long alumniId) {
        Alumni alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() -> new RuntimeException("Alumni not found"));

        alumni.setVerified(true);
        alumniRepository.save(alumni);

        // Send acceptance email
        emailService.sendEmail(
                alumni.getEmail(),
                "Your Alumni Account is Approved",
                "Congratulations! Your alumni account has been approved. You can now log in."
        );

        return alumni;
    }

    public void rejectAlumni(Long alumniId) {
        Alumni alumni = alumniRepository.findById(alumniId)
                .orElseThrow(() -> new RuntimeException("Alumni not found"));

        alumniRepository.delete(alumni); // Or just leave it unverified, your choice

        // Send rejection email
        emailService.sendEmail(
                alumni.getEmail(),
                "Your Alumni Account was Rejected",
                "We're sorry, but your alumni account request has been rejected. Please contact admin for details."
        );
    }
}