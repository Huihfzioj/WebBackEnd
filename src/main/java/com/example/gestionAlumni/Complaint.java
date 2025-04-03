package com.example.gestionAlumni;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor



public class Complaint {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, columnDefinition = "TEXT")
	    private String content;

	    @Column(nullable = false, updatable = false)
	    private LocalDateTime creationDate = LocalDateTime.now();

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private ComplaintStatus status = ComplaintStatus.OPEN;

	
	

}
