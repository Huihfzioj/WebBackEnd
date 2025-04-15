package com.example.gestionAlumni.Entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Application {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @Column(nullable = false, updatable = false)
	    private LocalDateTime submissionDate ;

	    @Enumerated(EnumType.STRING)
	    @Column(nullable = false)
	    private ApplicationStatus status;
        

	    // Enum des statuts possibles
	    public enum ApplicationStatus {
	        PENDING,
	        ACCEPTED,
	        REJECTED
	    }
	    // Relation optionnelle avec un étudiant (exemple d'extension)
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "student_id")
	    private Student std;

		@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "alumni_id")
	    private Alumni alumni;


		@OneToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

}