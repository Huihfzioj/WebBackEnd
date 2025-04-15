package com.example.gestionAlumni.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

@Entity
@Table(name = "forums")
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String topic;
    
    @Column(name = "creation_date")
    private LocalDateTime creationDate;



    @ManyToMany(mappedBy = "forums")
    private List<Alumni> alumniMembers ;

   
    @OneToMany(mappedBy = "forum", cascade = CascadeType.ALL)
    private List<Message> messages;
    
    @ManyToMany
    @JoinTable(
        name = "forum_students",
        joinColumns = @JoinColumn(name = "forum_id"),
        inverseJoinColumns = @JoinColumn(name = "student_id"))
    private List<Student> studentMembers;
}

