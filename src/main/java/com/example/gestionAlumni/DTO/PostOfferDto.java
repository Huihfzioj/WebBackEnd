package com.example.gestionAlumni.DTO;

import com.example.gestionAlumni.Entities.Duration;
import com.example.gestionAlumni.Entities.Offer;
import com.example.gestionAlumni.Entities.Post;

import java.time.LocalDateTime;

public class PostOfferDto {
    public Long id;
    public String description;
    public LocalDateTime createdAt;
    public String type; // "Post" or "Offer"
    public String author;
    public String company;
    public String position;
    public Duration duration;
    public String creatorCompany;

    public PostOfferDto(Post post) {
        this.id = post.getId();
        this.description = post.getDescription();
        this.createdAt = post.getCreatedAt();
        this.author = post.getCreator().getFullName();
        this.creatorCompany=post.getCreator().getCurrentCompany();
        this.type = (post instanceof Offer) ? "Offer" : "Post";

        if (post instanceof Offer) {
            Offer offer = (Offer) post;
            this.company = offer.getCompany();
            this.position = offer.getPosition();
            this.duration=offer.getDuration();
        }
    }
}
