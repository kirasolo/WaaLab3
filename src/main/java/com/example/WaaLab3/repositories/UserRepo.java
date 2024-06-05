package com.example.WaaLab3.repositories;

import com.example.WaaLab3.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    public User findByEmail(String email);
}