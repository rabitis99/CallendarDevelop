package com.example.schedule.Dto.responseDto;

import com.example.schedule.entity.Author;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter

public class AuthorResponseDto {
    Long id;
    String name;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
    public AuthorResponseDto(Author author){
        this.id=author.getId();
        this.name=author.getAuthorName();
        this.email=author.getEmail();
        this.createdAt=author.getCreatedAt();
        this.updatedAt=author.getUpdatedAt();
    }
}
