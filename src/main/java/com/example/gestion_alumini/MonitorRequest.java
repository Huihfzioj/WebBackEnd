package com.example.gestion_alumini;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "monitor_requests")
public class MonitorRequest {
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
    @JoinColumn(name = "teacher_id")
    private Teacher reviewer;

    @OneToOne(mappedBy = "monitorRequest")
    private Offer generatedOffer;
}

