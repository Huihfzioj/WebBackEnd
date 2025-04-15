package com.example.gestionAlumni.Entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String firstName;

    @Column(nullable = false)
    String lastName;

    @Column(nullable = false, unique = true)
    String email;

    @Column(nullable = false)
    String password;

    @Column(columnDefinition = "boolean default true")
    boolean active=true;

    @OneToMany(mappedBy = "receiver")
    List<Message> ReceivedMessages;

    @OneToMany(mappedBy = "sender")
    List<Message> SentMessages;

    @OneToMany(mappedBy = "complainant", cascade = CascadeType.ALL)
    private List<Complaint> complaints = new ArrayList<>();

    @OneToMany(mappedBy = "host")
    List<Event> hostedEvents;

    @ManyToMany
    List<Event> events;
}
