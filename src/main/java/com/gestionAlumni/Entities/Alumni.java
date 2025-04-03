package com.gestionAlumni.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name="alumni")
public class Alumni extends User{

    @Column(nullable = false)
    int graduationYear;

    String currentCompany;

    String currentJob;

    Long salary;

    String Speciality;

    @ManyToMany
    @JoinTable(name="alumni_skill",joinColumns = {@JoinColumn(name="alumni_id")},inverseJoinColumns = {@JoinColumn(name ="skill_id")})
    List<Skill> skills;

    @ManyToMany
    @JoinTable(name="alumni_forum",joinColumns = {@JoinColumn(name = "alumni_id")},inverseJoinColumns = {@JoinColumn(name="forum_id")})
    List<Forum> joinedForums;

    @OneToMany(mappedBy = "alumni")
    List<Application> applications;

    @OneToMany(mappedBy = "alumni")
    List<MentorshipRequest> mentorshipRequests;

    @OneToMany(mappedBy = "alumni")
    List<InternshipRequest> internshipRequestsReceived;
}
