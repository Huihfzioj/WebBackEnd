package com.gestionAlumni.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "monitor_requests")
public class MentorshipRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String details;
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "alumni_id")
    private Alumni proposer;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor reviewer;

    @OneToOne(mappedBy = "mentorshipRequest")
    private Offer generatedOffer;
}

