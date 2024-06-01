package com.example.WaaLab3.models.dto.responses;

import lombok.Data;
@Data
public class PostDto {
    private long id;
    private String title;
    private String content;

    private String author;
}