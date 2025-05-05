package com.example.gestionAlumni.DTO;

public class AlumniInfoDto {
    private String firstName;
    private String lastName;
    private String department;
    private int graduationYear;
    private String jobTitle;
    private String company;
    private String industry;

    public AlumniInfoDto(String firstName, String lastName, String department, int graduationYear,String jobTitle,String company,String industry) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.department = department;
        this.graduationYear = graduationYear;
        this.jobTitle=jobTitle;
        this.company=company;
        this.industry=industry;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getIndustry() {
        return industry;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
}
