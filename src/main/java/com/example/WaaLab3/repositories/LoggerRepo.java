package com.example.WaaLab3.repositories;

import com.example.WaaLab3.aspect.LoggerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.logging.Logger;

public interface LoggerRepo extends JpaRepository<LoggerEntity, Long> {
}
