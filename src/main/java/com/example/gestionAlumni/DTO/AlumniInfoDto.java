package com.example.gestionAlumni.DTO;

public class AlumniInfoDto {
    private String firstName;
    private String lastName;
    private String department;
    private int graduationYear;

    // Constructors
    public AlumniInfoDto() {
    }

    public AlumniInfoDto(String firstName, String lastName, String department, int graduationYear) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.graduationYear = graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
