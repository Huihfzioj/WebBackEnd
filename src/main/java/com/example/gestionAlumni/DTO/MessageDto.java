package com.example.gestionAlumni.DTO;

import java.time.LocalDateTime;

public record MessageDto(
        Long id,
        String content,
        LocalDateTime sentDate,
        UserDto sender,
        UserDto receiver,
        boolean isCurrentUser
) {}
