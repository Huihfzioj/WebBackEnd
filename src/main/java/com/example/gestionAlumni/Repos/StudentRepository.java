package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.DTO.InfoDTO;
import com.example.gestionAlumni.Entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    Optional<Student> findByEmail(String email);

    List<Student> findBySpeciality(String speciality);

    List<Student> findByPredictedGradYear(int predictedGradYear);

    List<Student> findByAverageGreaterThan(Float minAverage);
    @Query("SELECT new com.example.gestionAlumni.DTO.InfoDTO(s.speciality, s.predictedGradYear, s.skill, s.searchType) " +
            "FROM Student s WHERE s.id = :id")
    InfoDTO findStudentInfoById(@Param("id") Long id);

}