package com.example.gestionAlumni.Entities;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "internship_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class InternshipRequest {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    
    @Column(nullable = false)
    private InternshipType type;

    
    @Column(nullable = false)
    private String status ;

    @Column(nullable = false, updatable = false)
    private String submissionDate ;

    // Enum pour le type de stage
    public enum InternshipType {
        SUMMER_INTERNSHIP, 
        FINAL_YEAR_PROJECT
    }
    // Enum pour le statut de la demande
    public enum RequestStatus {
        PENDING,
        APPROVED,
        REJECTED // Ajout recommandé pour une gestion complète
    }
    
 


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id") // Colonne de jointure dans la table Complaint
    private Student std;

    @OneToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;
    
}