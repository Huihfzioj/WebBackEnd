package com.example.gestionAlumni.Controllers;

import com.example.gestionAlumni.DTO.PostDto;
import com.example.gestionAlumni.Entities.Alumni;
import com.example.gestionAlumni.Entities.Post;
import com.example.gestionAlumni.Repos.AlumniRepository;
import com.example.gestionAlumni.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private AlumniRepository alumniRepository;

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        return postService.getPostById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody PostDto postDTO) {
        Optional<Alumni> alumniOptional = alumniRepository.findById(postDTO.creatorId);
        if (alumniOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Post post = new Post();
        post.setDescription(postDTO.description);
        post.setCreatedAt(postDTO.createdAt);
        post.setCreator(alumniOptional.get());

        Post savedPost = postService.savePost(post);
        return ResponseEntity.ok(savedPost);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{postId}/description")
    public Post updatePostDescription(@PathVariable Long postId, @RequestBody String newDescription) {
        return postService.updateDescription(postId, newDescription);
    }
    @GetMapping("/user/{alumniId}")
    public List<Post> getPostsByAlumni(@PathVariable Long alumniId) {
        return postService.getPostsByAlumniId(alumniId);
    }
}

