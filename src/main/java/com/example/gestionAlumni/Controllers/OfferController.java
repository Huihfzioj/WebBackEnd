package com.example.gestionAlumni.Controllers;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;

import com.example.gestionAlumni.DTO.OfferDTO;
import com.example.gestionAlumni.Entities.Alumni;
import com.example.gestionAlumni.Entities.OfferType;
import com.example.gestionAlumni.Repos.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.gestionAlumni.Entities.Offer;
import com.example.gestionAlumni.Services.OfferService;

@RestController
@RequestMapping("/api/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;
    @Autowired
    private AlumniRepository alumniRepository;

    @GetMapping
    public List<Offer> getAllOffers() {
        return offerService.getAllOffers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> getOfferById(@PathVariable Long id) {
        return offerService.getOfferById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Offer> createOffer(@RequestBody OfferDTO dto) {
        Alumni alumni = alumniRepository.findById(dto.creatorId)
                .orElseThrow(() -> new RuntimeException("Alumni not found"));

        Offer offer = new Offer();
        offer.setOfferType(OfferType.valueOf(dto.offerType.toUpperCase()));
        offer.setPosition(dto.position);
        offer.setCompany(dto.company);
        try {
            offer.setDuration(com.example.gestionAlumni.Entities.Duration.valueOf(dto.duration.toUpperCase()));
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Invalid duration value: " + dto.duration);
        }// Adjust if needed
        offer.setProposedSalary(dto.proposedSalary);
        offer.setStatus(dto.status);
        offer.setDescription(dto.description);
        OffsetDateTime dateTime = OffsetDateTime.parse(dto.createdAt);
        LocalDateTime localDateTime = dateTime.toLocalDateTime();
        offer.setCreatedAt(localDateTime);
        offer.setCreator(alumni);

        Offer saved = offerService.save(offer);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer(@PathVariable Long id) {
        offerService.deleteOffer(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{offerId}/description")
    public Offer updateOfferDescription(@PathVariable Long offerId, @RequestBody String newDescription) {
        return offerService.updateDescription(offerId, newDescription);
    }
}
