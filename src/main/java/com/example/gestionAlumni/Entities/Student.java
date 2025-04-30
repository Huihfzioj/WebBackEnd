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

    @Column(columnDefinition = "INT DEFAULT 0")
    int predictedGradYear=0;

    String skill;

    private String searchType;

    @Lob
    private byte[] document;

    private String documentName;

    private String description;

    @OneToMany(mappedBy = "student")
    List<Application> applications;

    @OneToMany(mappedBy = "sender")
    List<InternshipRequest> internshipRequests;

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }

    public byte[] getDocument() {
        return document;
    }

    public Float getAverage() {
        return average;
    }

    public int getPredictedGradYear() {
        return predictedGradYear;
    }

    public String getDocumentName() {
        return documentName;
    }

    public void setAverage(Float average) {
        this.average = average;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }

    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public void setPredictedGradYear(int predictedGradYear) {
        this.predictedGradYear = predictedGradYear;
    }

    public String getSearchType() {
        return searchType;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}