package com.example.gestionAlumni.DTO;

import org.antlr.v4.runtime.misc.NotNull;

public record MessageRequest(
        Long conversationId,
        Long senderId,
        Long receiverId,
        String content
) {}
