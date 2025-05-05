package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.DTO.PostOfferDto;
import com.example.gestionAlumni.Entities.Post;
import com.example.gestionAlumni.Repos.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/{userId}")
    public List<PostOfferDto> getFeedForUser(@PathVariable Long userId) {
        List<Post> allPosts = postRepository.findAll();
        return allPosts.stream()
                .filter(post -> !post.getCreator().getId().equals(userId))
                .map(PostOfferDto::new)
                .collect(Collectors.toList());
    }
}
