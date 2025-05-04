package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.Entities.Conversation;
import com.example.gestionAlumni.Entities.Message;
import com.example.gestionAlumni.Entities.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    Optional<Message> findFirstByConversationIdOrderBySentDateDesc(Long conversationId);
    List<Message> findBySenderAndReceiverOrderBySentDateAsc(User sender, User receiver);
    List<Message> findByConversationOrderBySentDateAsc(Conversation conversation);
    List<Message> findBySender_Id(Long senderId);

    List<Message> findByReceiver_Id(Long receiverId);
    List<Message> findTop10ByReceiver_IdOrderBySentDateDesc(Long receiverId);

    List<Message> findBySentDateAfter(LocalDateTime date);
    // Find messages in conversation with pagination
    Page<Message> findByConversationIdOrderBySentDateDesc(Long conversationId, Pageable pageable);

    // Mark messages as read
    @Modifying
    @Query("UPDATE Message m SET m.read = true WHERE m.conversation.id = :conversationId AND m.receiver.id = :userId AND m.read = false")
    void markMessagesAsRead(@Param("conversationId") Long conversationId, @Param("userId") Long userId);
    // Get last message in conversation
    @Query("SELECT m FROM Message m WHERE m.conversation.id = :conversationId ORDER BY m.sentDate DESC")
    Page<Message> findByConversationId(@Param("conversationId") Long conversationId, Pageable pageable);
    @Query("SELECT COUNT(m) FROM Message m WHERE m.conversation.id = :conversationId " +
            "AND m.sender.id != :userId AND m.read = false")
    long countUnreadMessages(@Param("conversationId") Long conversationId,
                             @Param("userId") Long userId);
}