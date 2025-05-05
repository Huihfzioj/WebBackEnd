package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.DTO.IndustryStatDto;
import com.example.gestionAlumni.DTO.TopEmployerDto;
import com.example.gestionAlumni.Entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlumniRepository extends JpaRepository<Alumni,Long> {

    Optional<Alumni> findByEmail(String email);
    Optional<Alumni> findByVerificationToken(String token);

    @Query("SELECT a.currentCompany, COUNT(a) FROM Alumni a WHERE a.currentCompany IS NOT NULL GROUP BY a.currentCompany ORDER BY COUNT(a) DESC")
    List<Object[]> findTopEmployers();

    @Query("SELECT a.speciality, COUNT(a) FROM Alumni a WHERE a.speciality IS NOT NULL GROUP BY a.speciality ORDER BY COUNT(a) DESC")
    List<Object[]> findTopSkills();

    @Query("SELECT a.currentJob, COUNT(a) FROM Alumni a WHERE a.currentJob IS NOT NULL GROUP BY a.currentJob")
    List<Object[]> findIndustryStats();

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
    @Query("SELECT a FROM Alumni a WHERE a.industry = :industry")
    List<Alumni> findSuggestedAlumni(@Param("industry") String industry);

}