package com.example.schedule.dto.requestDto.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    String comment;
    Long id;
    LocalDateTime creatAt;
    LocalDateTime updateAt;


}
