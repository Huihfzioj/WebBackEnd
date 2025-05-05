package com.example.gestionAlumni.Repos;

import com.example.gestionAlumni.Entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByCreatorId(Long alumniId);
}
