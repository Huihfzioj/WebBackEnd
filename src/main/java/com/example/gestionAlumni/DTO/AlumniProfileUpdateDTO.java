package com.example.gestionAlumni.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AlumniProfileUpdateDTO {
    private String jobTitle;
    private String company;
    private Boolean openToMentoring;
    private Boolean openToCareerAdvice;
    private Boolean openToJobReferrals;

    public String getJobTitle() {
        return jobTitle;
    }

    public String getCompany() {
        return company;
    }

    public Boolean getOpenToCareerAdvice() {
        return openToCareerAdvice;
    }

    public Boolean getOpenToJobReferrals() {
        return openToJobReferrals;
    }

    public Boolean getOpenToMentoring() {
        return openToMentoring;
    }
}
