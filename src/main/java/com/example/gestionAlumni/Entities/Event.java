package com.example.gestionAlumni.Entities;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;
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

    @ManyToMany
    @JoinTable(
        name = "event_participants",
        joinColumns = @JoinColumn(name = "event_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<User> participants = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "admin_id")
    private Administrator admin;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User host;

    // Méthodes du diagramme
    public void cancelEvent() {
        this.endDate = LocalDateTime.now();
    }
}