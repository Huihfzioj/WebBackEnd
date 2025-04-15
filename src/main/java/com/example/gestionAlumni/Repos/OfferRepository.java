package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.Entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfferRepository extends JpaRepository<Offer,Long> {

    Optional<Offer> findByMentorshipRequest_Id(Long mentorshipRequestId);

    List<Offer> findByStatus(Boolean status);

    long countByStatus(Boolean status);
}
