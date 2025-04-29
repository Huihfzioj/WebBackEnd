package com.example.gestionAlumni.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.gestionAlumni.Entities.Offer;
import com.example.gestionAlumni.Repos.OfferRepository;

@Service
public class OfferService {

    @Autowired
    private OfferRepository offerRepository;

    public List<Offer> getAllOffersSortedByDate() {
        return offerRepository.findAllByOrderByCreatedAtDesc();
    }

    public List<Offer> searchOffersByTitle(String title) {
        return offerRepository.findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(title);
    }

    public Offer getOfferById(Long id) {
        return offerRepository.findById(id).orElseThrow(() -> new RuntimeException("Offer not found"));
    }
}