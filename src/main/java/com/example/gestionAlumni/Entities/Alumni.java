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
public class Alumni extends User{

    @Column(nullable = false)
    int graduationYear;

    String currentCompany;

    String currentJob;

    Long salary;

    String speciality;

    @ManyToMany
    @JoinTable(name="alumni_skill",joinColumns = {@JoinColumn(name="alumni_id")},inverseJoinColumns = {@JoinColumn(name ="skill_id")})
    List<Skill> skills;

    @OneToMany
    List<Application> applications;

    @OneToMany
    List<MentorshipRequest> mentorshipRequests;

    @OneToMany
    List<InternshipRequest> internshipRequestsReceived;

    void acceptInternship(Long id){
        for (InternshipRequest intern : internshipRequestsReceived){

        }
    }
}
