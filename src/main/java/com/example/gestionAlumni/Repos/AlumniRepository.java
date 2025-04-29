package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumniRepository extends JpaRepository<Alumni,Long> {

    Optional<Alumni> findByEmail(String email);
    Optional<Alumni> findByVerificationToken(String token);

    Boolean existsByEmail(String email);

    List<Alumni> findByGraduationYear(int graduationYear);

    List<Alumni> findByCurrentCompany(String company);

    List<Alumni> findByCurrentJob(String currentJob);

    List<Alumni> findBySalaryGreaterThanEqual(Long salary);

    List<Alumni> findBySalaryLessThanEqual(Long salary);

    List<Alumni> findBySalary(Long salary);

    List<Alumni> findBySpeciality(String speciality);

    List<Alumni> findByGraduationYearAndCurrentCompany(int graduationYear, String company);

    @Query("SELECT a.applications FROM Alumni a WHERE a.id = :alumniId")
    List<Application> findApplicationsByAlumniId(Long alumniId);

    @Query("SELECT a.mentorshipRequests FROM Alumni a WHERE a.id = :alumniId")
    List<MentorshipRequest> findMentorshipRequestsByAlumniId(Long alumniId);

    @Query("SELECT a.internshipRequestsReceived FROM Alumni a WHERE a.id = :alumniId")
    List<InternshipRequest> findInternshipRequestsByAlumniId(Long alumniId);
}
