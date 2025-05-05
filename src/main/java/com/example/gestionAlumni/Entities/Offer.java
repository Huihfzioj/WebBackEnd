package com.example.gestionAlumni.Entities;


import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Offer extends Post{

    @Enumerated(EnumType.STRING)
    private OfferType offerType;

    private String position;

    private String company;
    @Enumerated(EnumType.STRING)
    private Duration duration;

    private Double proposedSalary=0.0;
    private Boolean status;
    @OneToOne
    @JoinColumn(name = "mentorship_request_id")
    private MentorshipRequest mentorshipRequest;

    public void setOfferType(OfferType offerType) {
        this.offerType = offerType;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public Double getProposedSalary() {
        return proposedSalary;
    }

    @Override
    public Long getId() {
        return super.getId();
    }

    public String getCompany() {
        return company;
    }

    public String getPosition() {
        return position;
    }

    public Duration getDuration() {
        return duration;
    }

    public Boolean getStatus() {
        return status;
    }

    @Override
    public Alumni getCreator() {
        return super.getCreator();
    }

    @Override
    public LocalDateTime getCreatedAt() {
        return super.getCreatedAt();
    }

    @Override
    public String getDescription() {
        return super.getDescription();
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public void setProposedSalary(Double proposedSalary) {
        this.proposedSalary = proposedSalary;
    }

    public OfferType getOfferType() {
        return offerType;
    }
}
