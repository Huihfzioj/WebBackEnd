package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.DTO.AlumniStatisticsDTO;
import com.example.gestionAlumni.Services.AlumniStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    private final AlumniStatisticsService statisticsService;

    public StatisticsController(AlumniStatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }
    @GetMapping("/admin")
    public ResponseEntity<AlumniStatisticsDTO> getFullStatistics() {
        AlumniStatisticsDTO stats = statisticsService.getStatsForStudents(); // optional, if implemented
        return ResponseEntity.ok(stats);
    }
}
