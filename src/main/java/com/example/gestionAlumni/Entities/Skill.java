package com.example.gestionAlumni.Entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "skills")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String proficiencyLevel;
    @ManyToMany(mappedBy = "skills")
    List<Alumni> alumnis;
    @ManyToMany(mappedBy = "skills")
    List<Student> students;
}