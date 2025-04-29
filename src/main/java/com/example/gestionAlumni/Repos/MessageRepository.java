package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.Entities.Conversation;
import com.example.gestionAlumni.Entities.Message;
import com.example.gestionAlumni.Entities.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findBySenderAndReceiverOrderBySentDateAsc(User sender, User receiver);
    List<Message> findByConversationOrderBySentDateAsc(Conversation conversation);

}