package com.example.gestionAlumni.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne
    @JoinColumn(name = "user1_id", nullable = false)
    User user1;

    @ManyToOne
    @JoinColumn(name = "user2_id", nullable = false)
    User user2;

    @OneToMany(mappedBy = "conversation", cascade = CascadeType.ALL)
    List<Message> messages;

    public Long getId() {
        return id;
    }

    public User getUser1() {
        return user1;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser2() {
        return user2;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }


    public Object getParticipants() {
        List<Long> list=new ArrayList<>();
        list.add(user1.getId());
        list.add(user2.getId());
        return list;
    }
}
