package com.example.gestionAlumni.DTO;

public class ConversationStartRequest {
    private Long currentUserId;
    private Long alumniId;

    public Long getAlumniId() {
        return alumniId;
    }

    public Long getCurrentUserId() {
        return currentUserId;
    }

    public void setAlumniId(Long alumniId) {
        this.alumniId = alumniId;
    }

    public void setCurrentUserId(Long currentUserId) {
        this.currentUserId = currentUserId;
    }
}
