package com.example.gestionAlumni.Entities;



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



  
    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private InternshipRequest internshipRequest;

   
    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private MonitorRequest monitorrequest;

  
    @OneToOne(mappedBy = "offer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Application application;
}