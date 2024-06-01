package com.example.WaaLab3.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements IUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Post> posts = new ArrayList<>();

    @Override
    public long getId() {
        return Id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setId(long id) {
        this.Id = id;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public void addPost(Post post) {
        this.posts.add(post);
    }
}