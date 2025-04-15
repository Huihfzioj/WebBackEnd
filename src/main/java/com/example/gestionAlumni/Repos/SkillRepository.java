package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.Entities.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {

    Skill findByName(String name);

    List<Skill> findByNameContainingIgnoreCase(String keyword);

    List<Skill> findByProficiencyLevel(String proficiencyLevel);

    List<Skill> findByNameAndProficiencyLevel(String name, String proficiencyLevel);

    @Query("SELECT s FROM Skill s JOIN s.alumnis a WHERE a.id = :alumniId")
    List<Skill> findByAlumniId(Long alumniId);

    @Query("SELECT s FROM Skill s JOIN s.students st WHERE st.id = :studentId")
    List<Skill> findByStudentId(Long studentId);

    @Query("SELECT COUNT(a) FROM Skill s JOIN s.alumnis a WHERE s.id = :skillId")
    long countAlumniWithSkill(Long skillId);

    @Query("SELECT COUNT(st) FROM Skill s JOIN s.students st WHERE s.id = :skillId")
    long countStudentsWithSkill(Long skillId);
}
