package com.example.WaaLab3.services;


import com.example.WaaLab3.models.Comment;
import com.example.WaaLab3.models.Post;
import com.example.WaaLab3.models.dto.responses.PostDto;
import com.example.WaaLab3.repositories.CommentRepo;
import com.example.WaaLab3.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostsService {

    @Autowired
    private PostRepository postRepository;


    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;

    public List<Post> fetchAll() {
        return postRepository.findAll();
    }

    public PostDto fetchById(long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.map(value -> modelMapper.map(value, PostDto.class)).orElse(null);
    }

    public Post save(Post post) {
        return postRepository.save(post);
    }

    public Post addComment(Comment comment, long id){

        Optional<Post> postOptional = postRepository.findById(id);
        if (postOptional.isPresent()) {
            Post post = postOptional.get();
            comment = commentRepo.save(comment);
            post.addComment(comment);
            return postRepository.save(post);
        } else {
            throw new RuntimeException("Post not found with id: " + id);
        }
    }

    public void delete(long id) {
        postRepository.deleteById(id);
    }

    public Post update(long id, Post updatedPost) {
        return postRepository.findById(id)
                .map(post -> {
                    post.SetTitle(updatedPost.getTitle());
                    post.SetContent(updatedPost.getContent());
                    post.SetAuthor(updatedPost.getAuthor());
                    return postRepository.save(post);
                }).orElseGet(() -> {
                    updatedPost.SetId(id);
                    return postRepository.save(updatedPost);
                });
    }

    public List<PostDto> getFiltered(String author, String title) {
        List<Post> posts;

        if (!author.isEmpty() && !title.isEmpty()) {
            posts = postRepository.findByAuthorAndTitle(author, title);
        } else if (!author.isEmpty()) {
            posts = postRepository.findByAuthor(author);
        } else if (!title.isEmpty()) {
            posts = postRepository.findByTitle(title);
        } else {
            posts = postRepository.findAll();
        }

        return posts.stream()
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }


    public List<PostDto> getContains(String text) {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .filter(post -> post.getAuthor().contains(text))
                .map(post -> modelMapper.map(post, PostDto.class))
                .collect(Collectors.toList());
    }
}
