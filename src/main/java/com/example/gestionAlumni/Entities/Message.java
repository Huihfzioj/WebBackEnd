package com.example.gestionAlumni.Entities;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "message")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false, updatable = false)
    private LocalDateTime sentDate = LocalDateTime.now();

    // Relation : Expéditeur (User)
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Student sender;

    // Relation : Destinataire (User)
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private Student receiver;

     

   

    
}
