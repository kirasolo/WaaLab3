package com.example.WaaLab3.models;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;


import java.util.ArrayList;
import java.util.List;
@Data
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "users")
@JsonPropertyOrder({ "id", "name", "posts" })
public class User implements IUser{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    private String name;
    private String email;
    private String password;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private List<Post> posts = new ArrayList<>();


    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable
    private List<Role> roles;

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



    public void addPost(Post post) {
        this.posts.add(post);
    }
}