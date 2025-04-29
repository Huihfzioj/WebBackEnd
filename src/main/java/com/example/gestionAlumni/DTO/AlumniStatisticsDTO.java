package com.example.gestionAlumni.DTO;

import java.util.List;
import java.util.Map;

public class AlumniStatisticsDTO {
    private List<String> topSpecialities;
    private List<String> topCompanies;
    private Map<String, Long> jobTitleCounts;
    private Map<String, Double> averageSalaryBySpeciality;
    private Map<Integer, Long> alumniCountByYear;
    private long verifiedAlumniCount;
    private long totalAlumniCount;
    private List<YearSpecialityCountDTO> alumniCountByYearAndSpeciality;

    public List<YearSpecialityCountDTO> getAlumniCountByYearAndSpeciality() {
        return alumniCountByYearAndSpeciality;
    }

    public void setAlumniCountByYearAndSpeciality(List<YearSpecialityCountDTO> alumniCountByYearAndSpeciality) {
        this.alumniCountByYearAndSpeciality = alumniCountByYearAndSpeciality;
    }

    public List<String> getTopCompanies() {
        return topCompanies;
    }

    public long getTotalAlumniCount() {
        return totalAlumniCount;
    }

    public List<String> getTopSpecialities() {
        return topSpecialities;
    }

    public long getVerifiedAlumniCount() {
        return verifiedAlumniCount;
    }

    public Map<Integer, Long> getAlumniCountByYear() {
        return alumniCountByYear;
    }

    public void setAlumniCountByYear(Map<Integer, Long> alumniCountByYear) {
        this.alumniCountByYear = alumniCountByYear;
    }

    public Map<String, Double> getAverageSalaryBySpeciality() {
        return averageSalaryBySpeciality;
    }

    public Map<String, Long> getJobTitleCounts() {
        return jobTitleCounts;
    }

    public void setAverageSalaryBySpeciality(Map<String, Double> averageSalaryBySpeciality) {
        this.averageSalaryBySpeciality = averageSalaryBySpeciality;
    }

    public void setJobTitleCounts(Map<String, Long> jobTitleCounts) {
        this.jobTitleCounts = jobTitleCounts;
    }

    public void setTopCompanies(List<String> topCompanies) {
        this.topCompanies = topCompanies;
    }

    public void setTotalAlumniCount(long totalAlumniCount) {
        this.totalAlumniCount = totalAlumniCount;
    }

    public void setVerifiedAlumniCount(long verifiedAlumniCount) {
        this.verifiedAlumniCount = verifiedAlumniCount;
    }

    public void setTopSpecialities(List<String> topSpecialities) {
        this.topSpecialities = topSpecialities;
    }
}
