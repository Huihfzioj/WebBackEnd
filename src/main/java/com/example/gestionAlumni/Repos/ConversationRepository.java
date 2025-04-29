package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.Entities.Conversation;
import com.example.gestionAlumni.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {

    @Query("SELECT c FROM Conversation c WHERE " +
           "(c.user1 = :user1 AND c.user2 = :user2) OR " +
           "(c.user1 = :user2 AND c.user2 = :user1)")
    Optional<Conversation> findByUsers(User user1, User user2);
}
