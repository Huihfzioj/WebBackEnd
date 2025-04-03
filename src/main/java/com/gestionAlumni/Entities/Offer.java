package com.gestionAlumni.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private Boolean status;

    @OneToOne
    @JoinColumn(name = "mentorship_request_id")
    private MentorshipRequest mentorshipRequest;

    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor validator;
}



