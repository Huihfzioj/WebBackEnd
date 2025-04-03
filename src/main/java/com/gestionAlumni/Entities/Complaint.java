package com.gestionAlumni.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
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

	 @ManyToOne
	@JoinColumn(name="complainant_id",nullable = false)
	private User complainant;


}
