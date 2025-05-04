package com.example.gestionAlumni.DTO;

public record ConversationDto(
        Long id,
        UserDto user1,
        UserDto user2,
        MessageDto lastMessage,
        int unreadCount
) {}
