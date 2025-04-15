package com.example.gestionAlumni.Entities;



import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Alumni extends User{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long idalumni;
    @Column(nullable = false)
    int graduationYear;


    String currentCompany;

    String currentJob;

    Long salary;

  

    @OneToMany(mappedBy = "Alumni", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MonitorRequest>  MonitorRequests;

    @OneToMany(mappedBy = "Alumni", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Application>  applications;

    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "event_id")
	    private Event event;
}