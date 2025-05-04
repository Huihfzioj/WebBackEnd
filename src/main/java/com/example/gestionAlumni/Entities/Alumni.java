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

    int graduationYear;

    String currentCompany;

    String currentJob;

    Long salary;

    String speciality;

    String department;

    boolean verified=false;

    int cin;

    String verificationToken;

    @OneToMany
    List<Application> applications;

    @OneToMany
    List<MentorshipRequest> mentorshipRequests;

    @OneToMany
    List<InternshipRequest> internshipRequestsReceived;
    @OneToMany
    List<Post> posts;

    void acceptInternship(Long id){
        for (InternshipRequest intern : internshipRequestsReceived){

        }
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }

    public String getEmail() {
        return super.getEmail();
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public String getPassword() {
        return super.getPassword();
    }
    public boolean isVerified() {
        return super.isActive();
    }

    public boolean isActive() {
        return super.isActive();
    }

    public String getDepartment() {
        return department;
    }

    public int getGraduationYear() {
        return graduationYear;
    }
}