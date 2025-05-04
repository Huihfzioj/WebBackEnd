package com.example.gestionAlumni.Services;

import com.example.gestionAlumni.Entities.Conversation;
import com.example.gestionAlumni.Entities.Message;
import com.example.gestionAlumni.Entities.User;
import com.example.gestionAlumni.Repos.ConversationRepository;
import com.example.gestionAlumni.Repos.MessageRepository;
import com.example.gestionAlumni.Repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ConversationRepository conversationRepository;

    public Message sendMessage(Long senderId, Long receiverId, String content) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        // Check if a conversation exists between the two users
        Optional<Conversation> conversationOpt = conversationRepository
                .findByUsers(sender.getId(), receiver.getId());

        Conversation conversation;
        if (conversationOpt.isPresent()) {
            conversation = conversationOpt.get();
        } else {
            conversation = new Conversation();
            conversation.setUser1(sender);
            conversation.setUser2(receiver);
            conversation = conversationRepository.save(conversation);
        }

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(content);
        message.setSentDate(LocalDateTime.now());
        message.setConversation(conversation);

        return messageRepository.save(message);
    }

    public List<Message> getMessagesBetweenUsers(Long senderId, Long receiverId) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("Sender not found"));
        User receiver = userRepository.findById(receiverId)
                .orElseThrow(() -> new RuntimeException("Receiver not found"));

        return messageRepository.findBySenderAndReceiverOrderBySentDateAsc(sender, receiver);
    }
}
