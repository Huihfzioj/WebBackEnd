package com.example.gestionAlumni;

// Le package contenant les classes de votre application.

import com.example.gestionAlumni.Entities.Conversation; // Représente une conversation entre deux utilisateurs.
import com.example.gestionAlumni.Entities.Message; // Représente un message.
import com.example.gestionAlumni.Entities.User; // Représente un utilisateur.

import com.example.gestionAlumni.Repos.ConversationRepository; // Pour gérer les conversations.
import com.example.gestionAlumni.Repos.MessageRepository; // Pour gérer les messages.
import com.example.gestionAlumni.Repos.UserRepository; // Pour gérer les utilisateurs.
import com.example.gestionAlumni.Services.MessageService; // Logique métier de gestion des messages.

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.apache.log4j.Logger; // 🔴 Logger ajouté ici

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessageServiceTest {

    private static final Logger logger = Logger.getLogger(MessageServiceTest.class); // 🔴 Logger

    @Mock
    private MessageRepository messageRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ConversationRepository conversationRepository;

    @InjectMocks
    private MessageService messageService;

    private User sender;
    private User receiver;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        sender = new User();
        sender.setId(1L);

        receiver = new User();
        receiver.setId(2L);

        logger.info("✅ Initialisation des utilisateurs pour les tests.");
    }

    @Test
    void testSendMessage_CreatesNewConversation_AndSavesMessage() {
        logger.info("🚀 Début du test : testSendMessage_CreatesNewConversation_AndSavesMessage");

        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(sender));
        when(userRepository.findById(2L)).thenReturn(Optional.of(receiver));
        when(conversationRepository.findByUsers(sender, receiver)).thenReturn(Optional.empty());

        Conversation newConversation = new Conversation();
        newConversation.setUser1(sender);
        newConversation.setUser2(receiver);

        when(conversationRepository.save(any(Conversation.class))).thenReturn(newConversation);
        when(messageRepository.save(any(Message.class))).thenAnswer(i -> i.getArgument(0));

        // Act
        Message result = null;
        try {
            result = messageService.sendMessage(1L, 2L, "Hello");
            logger.debug("📦 Message sauvegardé : " + result.getContent());
        } catch (Exception e) {
            logger.error("❌ Erreur lors de l'envoi du message", e);
            fail("Erreur inattendue");
        }

        // Assert
        assertNotNull(result);
        assertEquals("Hello", result.getContent());
        assertEquals(sender, result.getSender());
        assertEquals(receiver, result.getReceiver());
        assertNotNull(result.getSentDate());
        assertEquals(newConversation, result.getConversation());

        verify(messageRepository, times(1)).save(any(Message.class));

        logger.info("✅ Test terminé avec succès.");
    }
}
