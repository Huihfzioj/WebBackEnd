package com.example.gestionAlumni.DTO;

import com.example.gestionAlumni.Entities.Student;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;

public class InfoDTO {

    String speciality;

    int predictedGradYear=0;

    String skill;

    private String searchType;

    public InfoDTO() {}
    public InfoDTO(String speciality,int predictedGradYear,String skill,String searchType){
        this.speciality=speciality;
        this.predictedGradYear=predictedGradYear;
        this.skill=skill;
        this.searchType=searchType;
    }
    public InfoDTO(Student student) {
        this.speciality = student.getSpeciality();
        this.predictedGradYear = student.getPredictedGradYear();
        this.skill = student.getSkill();
        this.searchType = student.getSearchType();
    }

    public String getSearchType() {
        return searchType;
    }

    public void setPredictedGradYear(int predictedGradYear) {
        this.predictedGradYear = predictedGradYear;
    }

    public void setSearchType(String searchType) {
        this.searchType = searchType;
    }

    public String getSkill() {
        return skill;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public int getPredictedGradYear() {
        return predictedGradYear;
    }
}
