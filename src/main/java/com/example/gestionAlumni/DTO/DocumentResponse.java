package com.example.gestionAlumni.DTO;

public record DocumentResponse(
        String fileName,
        String description,
        String fileType,
        String date,
        String downloadUrl
) {}
