package com.example.WaaLab3.repositories;


import com.example.WaaLab3.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByAuthorAndTitle(String author, String title);
    List<Post> findByAuthor(String author);
    List<Post> findByTitle(String title);
}
