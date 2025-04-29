package com.example.gestionAlumni.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.gestionAlumni.Entities.Offer;
import com.example.gestionAlumni.Services.OfferService;

@RestController
@RequestMapping("/api/offers")
@CrossOrigin(origins = "*") // à adapter selon le frontend
public class OfferController {

    @Autowired
    private OfferService offerService;

    // Tous les offres, triées par date
    @GetMapping
    public List<Offer> getAllOffers() {
        return offerService.getAllOffersSortedByDate();
    }

    // Recherche par titre
    @GetMapping("/search")
    public List<Offer> searchOffers(@RequestParam String title) {
        return offerService.searchOffersByTitle(title);
    }

    // Récupérer une offre spécifique par ID
    @GetMapping("/{id}")
    public Offer getOfferById(@PathVariable Long id) {
        return offerService.getOfferById(id);
    }
}