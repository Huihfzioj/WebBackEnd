package com.gestionAlumni.Entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.logging.log4j.message.Message;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@MappedSuperclass
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

    @OneToMany(mappedBy = "user")
    List<Message> ReceivedMessages;

    @OneToMany(mappedBy="user")
    List<Message> SentMessages;

    @OneToMany(mappedBy = "complainant", cascade = CascadeType.ALL)
    private List<Complaint> complaints = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Event> hostedEvents;

    @ManyToMany(mappedBy = "user")
    List<Event> events;
}
