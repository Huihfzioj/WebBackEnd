package com.example.gestionAlumni.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @Column(length = 280, nullable = false)
    String content;
    LocalDateTime sentDate;
    @ManyToOne
    @JoinColumn(name="sender_id",nullable = false)
    User sender;
    @ManyToOne
    @JoinColumn(name="receiver_id",nullable = false)
    User receiver;
}
