package com.example.gestion_alumini;

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
    @JoinColumn(name = "monitor_request_id")
    private MonitorRequest monitorRequest;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher validator;
}



