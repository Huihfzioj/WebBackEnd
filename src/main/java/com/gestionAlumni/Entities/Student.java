package com.gestionAlumni.Entities;

import jakarta.persistence.*;
import jakarta.websocket.OnClose;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Student extends User{

    Float average;

    String speciality;

    int predictedGradYear;

    @ManyToMany
    @JoinTable(name = "student_forum",joinColumns = {@JoinColumn(name = "student_id")},inverseJoinColumns = {@JoinColumn(name = "forum_id")})
    List<Forum> forums;

    @ManyToMany
    @JoinTable(name = "student_skill",joinColumns = {@JoinColumn(name = "student_id")},inverseJoinColumns = {@JoinColumn(name = "skill_id")})
    List<Skill> skills;

    @OneToMany(mappedBy = "student")
    List<Application> applications;

    @OneToMany(mappedBy = "student")
    List<InternshipRequest> internshipRequests;

}
