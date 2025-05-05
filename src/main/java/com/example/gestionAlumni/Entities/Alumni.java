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

    String industry;

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

    public String getSpeciality() {
        return speciality;
    }

    public void setCurrentJob(String currentJob) {
        this.currentJob = currentJob;
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

    public String getCurrentCompany() {
        return currentCompany;
    }

    public String getCurrentJob() {
        return currentJob;
    }
    public String getIndustry(){
        return industry;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    @Override
    public Long getId() {
        return super.getId();
    }
}