package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.Entities.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {

    List<Message> findBySender_Id(Long senderId);

    List<Message> findByReceiver_Id(Long receiverId);

    @Query("SELECT m FROM Message m WHERE " +
            "(m.sender.id = :user1Id AND m.receiver.id = :user2Id) OR " +
            "(m.sender.id = :user2Id AND m.receiver.id = :user1Id) " +
            "ORDER BY m.sentDate ASC")
    List<Message> findConversationBetweenUsers(Long user1Id, Long user2Id);

    List<Message> findTop10ByReceiver_IdOrderBySentDateDesc(Long receiverId);

    List<Message> findBySentDateAfter(LocalDateTime date);

    long countBySender_Id(Long senderId);

    long countByReceiver_Id(Long receiverId);
}
