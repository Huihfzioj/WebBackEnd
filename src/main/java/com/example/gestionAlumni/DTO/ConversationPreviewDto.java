package com.example.gestionAlumni.DTO;

import java.time.LocalDateTime;

public record ConversationPreviewDto(
        Long id,
        UserDto otherUser,
        String lastMessageContent,
        LocalDateTime lastMessageDate,
        int unreadCount,
        String role
) {}