package com.example.gestionAlumni.DTO;

public class YearSpecialityCountDTO {
    private int graduationYear;
    private String speciality;
    private long count;

    public YearSpecialityCountDTO(int graduationYear, String speciality, long count) {
        this.graduationYear = graduationYear;
        this.speciality = speciality;
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }
}
