package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.Entities.Conversation;
import com.example.gestionAlumni.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    // Finds a conversation between two specific users (order doesn't matter)
    default Optional<Conversation> findBetweenUsers(Long user1Id, Long user2Id) {
        return findByUsers(user1Id, user2Id);
    }

    // The actual implementation using custom query
    @Query("SELECT c FROM Conversation c WHERE " +
            "(c.user1.id = :user1Id AND c.user2.id = :user2Id) OR " +
            "(c.user1.id = :user2Id AND c.user2.id = :user1Id)")
    Optional<Conversation> findByUsers(@Param("user1Id") Long user1Id,
                                       @Param("user2Id") Long user2Id);

    // Finds all conversations for a specific user (using custom query)
    @Query("SELECT c FROM Conversation c WHERE " +
            "c.user1.id = :userId OR c.user2.id = :userId " +
            "ORDER BY (SELECT MAX(m.sentDate) FROM c.messages m) DESC")
    Page<Conversation> findConversationsByUserId(@Param("userId") Long userId,
                                                 Pageable pageable);
}
