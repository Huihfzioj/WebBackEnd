package com.example.gestionAlumni.Services;

import com.example.gestionAlumni.DTO.AlumniStatisticsDTO;
import com.example.gestionAlumni.DTO.YearSpecialityCountDTO;
import com.example.gestionAlumni.Repos.AlumniRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AlumniStatisticsService {

    @Autowired
    private AlumniRepository alumniRepository;

    public AlumniStatisticsDTO getStatsForStudents() {
        AlumniStatisticsDTO dto = new AlumniStatisticsDTO();

        dto.setTopSpecialities(convertToList(alumniRepository.countBySpeciality()));
        dto.setTopCompanies(convertToList(alumniRepository.countByCompany()));
        dto.setJobTitleCounts(convertToMap(alumniRepository.countByJobTitle()));
        dto.setAverageSalaryBySpeciality(convertToSalaryMap(alumniRepository.averageSalaryBySpeciality()));
        dto.setAlumniCountByYear(convertToYearMap(alumniRepository.alumniCountByYear()));
        dto.setVerifiedAlumniCount(alumniRepository.countByVerifiedTrue());
        dto.setTotalAlumniCount(alumniRepository.count());
        dto.setAlumniCountByYearAndSpeciality(
                convertToYearSpecialityDTO(alumniRepository.countByYearAndSpeciality())
        );

        return dto;
    }
    private List<String> convertToList(List<Object[]> rawList) {
        return rawList.stream()
                .map(obj -> (String) obj[0])
                .collect(Collectors.toList());
    }

    private Map<String, Long> convertToMap(List<Object[]> rawList) {
        return rawList.stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Long) obj[1]
                ));
    }

    private Map<String, Double> convertToSalaryMap(List<Object[]> rawList) {
        return rawList.stream()
                .collect(Collectors.toMap(
                        obj -> (String) obj[0],
                        obj -> (Double) obj[1]
                ));
    }

    private Map<Integer, Long> convertToYearMap(List<Object[]> rawList) {
        return rawList.stream()
                .collect(Collectors.toMap(
                        obj -> (Integer) obj[0],
                        obj -> (Long) obj[1]
                ));
    }
    private List<YearSpecialityCountDTO> convertToYearSpecialityDTO(List<Object[]> rawList) {
        return rawList.stream()
                .map(obj -> new YearSpecialityCountDTO(
                        (Integer) obj[0],
                        (String) obj[1],
                        (Long) obj[2]
                ))
                .collect(Collectors.toList());
    }
    }
