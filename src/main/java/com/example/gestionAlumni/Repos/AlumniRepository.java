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

    @Query("SELECT a.speciality, COUNT(a) FROM Alumni a GROUP BY a.speciality ORDER BY COUNT(a) DESC")
    List<Object[]> countBySpeciality();

    @Query("SELECT a.currentCompany, COUNT(a) FROM Alumni a WHERE a.currentCompany IS NOT NULL GROUP BY a.currentCompany ORDER BY COUNT(a) DESC")
    List<Object[]> countByCompany();

    @Query("SELECT a.currentJob, COUNT(a) FROM Alumni a WHERE a.currentJob IS NOT NULL GROUP BY a.currentJob")
    List<Object[]> countByJobTitle();

    @Query("SELECT a.speciality, AVG(a.salary) FROM Alumni a WHERE a.salary IS NOT NULL GROUP BY a.speciality")
    List<Object[]> averageSalaryBySpeciality();

    @Query("SELECT a.graduationYear, COUNT(a) FROM Alumni a GROUP BY a.graduationYear ORDER BY a.graduationYear DESC")
    List<Object[]> alumniCountByYear();

    long countByVerifiedTrue();

    @Query("SELECT a.graduationYear, a.speciality, COUNT(a) FROM Alumni a GROUP BY a.graduationYear, a.speciality")
    List<Object[]> countByYearAndSpeciality();

}
