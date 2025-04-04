package com.example.schedule.Dto.responseDto;

import com.example.schedule.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    String comment;
    Long id;
    LocalDateTime creatAt;
    LocalDateTime updateAt;
    public CommentResponseDto(Comment comment){
        this.comment=comment.getComment();
        this.id=comment.getId();
        this.creatAt=comment.getCreatedAt();
        this.updateAt=comment.getUpdatedAt();
    }
}
