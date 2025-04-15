package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.Entities.Complaint;
import com.example.gestionAlumni.Entities.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplaintRepository extends JpaRepository<Complaint,Long> {

    List<Complaint> findByComplainant_Id(Long userId);

    List<Complaint> findByStatus(ComplaintStatus status);

    List<Complaint> findTop5ByComplainant_IdOrderByCreationDateDesc(Long userId);

    long countByStatus(ComplaintStatus status);
}
