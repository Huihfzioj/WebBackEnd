package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.Repos.AlumniRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/dashboard")
public class DashboardController {
    @Autowired
    private final AlumniRepository alumniRepository;
    public DashboardController(AlumniRepository alumniRepository){
        this.alumniRepository=alumniRepository;
    }

    @GetMapping("/top-employers")
    public List<Map<String, Object>> getTopEmployers() {
        List<Object[]> results = alumniRepository.findTopEmployers();
        List<Map<String, Object>> employers = new ArrayList<>();

        for (Object[] row : results) {
            String company = (String) row[0];
            Long count = (Long) row[1];

            Map<String, Object> map = new HashMap<>();
            map.put("companyName", company);
            map.put("hires", count);
            employers.add(map);
        }

        return employers;
    }

    @GetMapping("/top-skills")
    public List<Map<String, Object>> getTopSkills() {
        List<Object[]> results = alumniRepository.findTopSkills();
        Long total = results.stream().map(row -> (Long) row[1]).reduce(0L, Long::sum);
        List<Map<String, Object>> skills = new ArrayList<>();

        for (Object[] row : results) {
            String skill = (String) row[0];
            Long count = (Long) row[1];
            int percentage = (int) ((count * 100.0) / total);

            Map<String, Object> map = new HashMap<>();
            map.put("name", skill);
            map.put("percentage", percentage);

            skills.add(map);
        }

        return skills;
    }

    @GetMapping("/industry-stats")
    public List<Map<String, Object>> getIndustryStats() {
        List<Object[]> results = alumniRepository.findIndustryStats();
        Long total = results.stream().map(row -> (Long) row[1]).reduce(0L, Long::sum);
        List<Map<String, Object>> stats = new ArrayList<>();

        for (Object[] row : results) {
            String industry = (String) row[0];
            Long count = (Long) row[1];
            int percentage = (int) ((count * 100.0) / total);

            Map<String, Object> map = new HashMap<>();
            map.put("name", industry);
            map.put("percentage", percentage);

            stats.add(map);
        }

        return stats;
    }
}
