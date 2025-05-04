package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.DTO.ConversationDto;
import com.example.gestionAlumni.DTO.ConversationPreviewDto;
import com.example.gestionAlumni.DTO.MessageDto;
import com.example.gestionAlumni.DTO.MessageRequest;
import com.example.gestionAlumni.Services.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {
    @Autowired
    private final ConversationService conversationService;
    public ConversationController(ConversationService con){
        conversationService=con;
    }

    @GetMapping("/between/{user1Id}/{user2Id}")
    public ConversationDto getConversation(
            @PathVariable Long user1Id,
            @PathVariable Long user2Id,
            @RequestParam Long currentUserId) {
        return conversationService.getOrCreateConversation(user1Id, user2Id, currentUserId);
    }

    @GetMapping("/user/{userId}")
    public Page<ConversationPreviewDto> getUserConversations(
            @PathVariable Long userId,
            @RequestParam Long currentUserId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size) {
        Pageable pageable = PageRequest.of(page, size);

        return conversationService.getUserConversations(userId, currentUserId, pageable);
    }

    @PostMapping("/messages")
    @ResponseStatus(HttpStatus.CREATED)
    public MessageDto sendMessage(
            @RequestParam Long senderId,
            @RequestBody MessageRequest request) {
        return conversationService.sendMessage(senderId, request);
    }

    @GetMapping("/{conversationId}/messages")
    public Page<MessageDto> getMessages(
            @PathVariable Long conversationId,
            @RequestParam Long currentUserId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size) {
        return conversationService.getMessages(
                conversationId, currentUserId, PageRequest.of(page, size)
        );
    }
}