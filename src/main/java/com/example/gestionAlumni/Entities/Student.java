package com.example.gestionAlumni.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Student extends User{

    Float average;

    String speciality;

    int predictedGradYear;

    @ManyToMany
    @JoinTable(name = "student_skill",joinColumns = {@JoinColumn(name = "student_id")},inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    List<Skill> skills;

    @OneToMany(mappedBy = "student")
    List<Application> applications;

    @OneToMany(mappedBy = "sender")
    List<InternshipRequest> internshipRequests;

}
