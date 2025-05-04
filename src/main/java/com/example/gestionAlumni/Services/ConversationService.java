package com.example.gestionAlumni.Services;

import com.example.gestionAlumni.DTO.*;
import com.example.gestionAlumni.Entities.Conversation;
import com.example.gestionAlumni.Entities.Message;
import com.example.gestionAlumni.Entities.User;
import com.example.gestionAlumni.Repos.ConversationRepository;
import com.example.gestionAlumni.Repos.MessageRepository;
import com.example.gestionAlumni.Repos.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class ConversationService {
    @Autowired
    private final ConversationRepository conversationRepo;
    @Autowired
    private final MessageRepository messageRepo;
    @Autowired
    private final UserRepository userRepo;

    public ConversationService(ConversationRepository conversationRepository,
                               MessageRepository messageRepository,
                               UserRepository userRepository) {
        this.conversationRepo = conversationRepository;
        this.messageRepo = messageRepository;
        this.userRepo = userRepository;
    }

    public ConversationDto getOrCreateConversation(Long user1Id, Long user2Id, Long currentUserId) {
        Conversation conversation = conversationRepo.findBetweenUsers(user1Id, user2Id)
                .orElseGet(() -> createNewConversation(user1Id, user2Id));
        return convertToDto(conversation, currentUserId);
    }

    public Page<ConversationPreviewDto> getUserConversations(Long userId, Long currentUserId, Pageable pageable) {
        Page<Conversation> conversations = conversationRepo.findConversationsByUserId(userId, pageable);

        return conversations.map(conversation -> {
            User otherParticipant = conversation.getUser1().getId().equals(userId)
                    ? conversation.getUser2()
                    : conversation.getUser1();

            Optional<Message> lastMessage = messageRepo
                    .findFirstByConversationIdOrderBySentDateDesc(conversation.getId());

            long unreadCount = messageRepo.countUnreadMessages(conversation.getId(), userId);

            return new ConversationPreviewDto(
                    conversation.getId(),
                    new UserDto(otherParticipant.getId(), otherParticipant.getName()),
                    lastMessage.map(Message::getContent).orElse(null),
                    lastMessage.map(Message::getSentDate).orElse(null),
                    (int)unreadCount,String.valueOf(otherParticipant.getClass())
            );
        });
    }

    public MessageDto sendMessage(Long senderId, MessageRequest request) {
        // senderId is passed explicitly
        Conversation conversation = conversationRepo.findBetweenUsers(
                        senderId, request.receiverId())
                .orElseGet(() -> createNewConversation(senderId, request.receiverId()));

        User sender = null;
        try {
            sender = userRepo.findById(senderId)
                    .orElseThrow(() -> new Exception("Sender not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        User receiver = null;
        try {
            receiver = userRepo.findById(request.receiverId())
                    .orElseThrow(() -> new Exception("Receiver not found"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Message message = new Message();
        message.setContent(request.content());
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setConversation(conversation);
        message.setSentDate(LocalDateTime.now());

        return convertToDto(messageRepo.save(message),senderId);
    }

    public Page<MessageDto> getMessages(Long conversationId, Long currentUserId, Pageable pageable) {
        messageRepo.markMessagesAsRead(conversationId, currentUserId);
        return messageRepo.findByConversationId(conversationId, pageable)
                .map(msg -> convertToDto(msg, currentUserId));
    }

    private ConversationDto convertToDto(Conversation conversation, Long currentUserId) {
        return new ConversationDto(
                conversation.getId(),
                convertToUserDto(conversation.getUser1()),
                convertToUserDto(conversation.getUser2()),
                conversation.getMessages().isEmpty() ? null :
                        convertToDto(conversation.getMessages().get(0), currentUserId),
                (int) messageRepo.countUnreadMessages(conversation.getId(), currentUserId)
        );
    }

    private MessageDto convertToDto(Message message, Long currentUserId) {
        return new MessageDto(
                message.getId(),
                message.getContent(),
                message.getSentDate(),
                convertToUserDto(message.getSender()),
                convertToUserDto(message.getReceiver()),
                message.getSender().getId().equals(currentUserId)
        );
    }
    private UserDto convertToUserDto(User user){
        return new UserDto(user.getId(), user.getName());
    }
    private Conversation createNewConversation(Long user1Id, Long user2Id) {
        User user1 = userRepo.findById(user1Id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + user1Id));
        User user2 = userRepo.findById(user2Id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + user2Id));

        Conversation conversation = new Conversation();
        conversation.setUser1(user1);
        conversation.setUser2(user2);

        return conversationRepo.save(conversation);
    }
    private ConversationPreviewDto convertToPreviewDto(Conversation conversation, Long currentUserId) {
        // Determine the other participant
        User otherParticipant = conversation.getUser1().getId().equals(currentUserId)
                ? conversation.getUser2()
                : conversation.getUser1();

        // Get the last message (if any)
        Message lastMessage = conversation.getMessages().isEmpty() ? null
                : conversation.getMessages().get(conversation.getMessages().size() - 1);

        // Count unread messages
        int unreadCount = (int) messageRepo.countUnreadMessages(conversation.getId(), currentUserId);
        UserDto user=new UserDto(otherParticipant.getId(),otherParticipant.getName());
        ConversationPreviewDto dto = new ConversationPreviewDto(conversation.getId(),user,lastMessage != null ? lastMessage.getContent() : null,lastMessage != null ? lastMessage.getSentDate() : null,unreadCount,String.valueOf(user.getClass()));
        return dto;
    }
}
