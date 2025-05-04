package com.example.gestionAlumni.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="conversation_id", nullable = false)
    Conversation conversation;

    @Column(name = "is_read", nullable = false)
    private boolean read;

    public void setSender(User sender) {
        this.sender = sender;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setSentDate(LocalDateTime sentDate) {
        this.sentDate = sentDate;
    }

    public void setConversation(Conversation conversation) {
        this.conversation = conversation;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isRead() {
        return read;
    }

    public Conversation getConversation() {
        return conversation;
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getSentDate() {
        return sentDate;
    }

    public String getContent() {
        return content;
    }

    public User getReceiver() {
        return receiver;
    }

    public User getSender() {
        return sender;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}