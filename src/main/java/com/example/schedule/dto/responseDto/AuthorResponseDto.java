package com.example.schedule.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
@Getter
@AllArgsConstructor
public class AuthorResponseDto {
    Long id;
    String name;
    String email;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

}
