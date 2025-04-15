package com.example.gestionAlumni.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "events")

public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
   

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private EventType type;

    private String organizer = "Faculty"; // Valeur par défaut

    
    // Méthodes du diagramme
    public void cancelEvent() {
        this.endDate = LocalDateTime.now();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id") // Colonne de jointure dans la table Event
    private Administrator admin;


     @OneToMany(mappedBy = "Event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> listeStudents;

    @OneToMany(mappedBy = "Event", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Alumni> listeAlumnis;

 
    public enum EventType {
        CONFERENCE, WORKSHOP, NETWORKING
    }
}