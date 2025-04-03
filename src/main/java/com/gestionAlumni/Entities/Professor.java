package com.gestionAlumni.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "professor")
public class Professor extends User{
    String taughtSubject;

    @OneToMany(mappedBy = "professor")
    List<MentorshipRequest> mentorshipRequests;

    @OneToMany(mappedBy = "professor")
    List<Offer> offers;
}
