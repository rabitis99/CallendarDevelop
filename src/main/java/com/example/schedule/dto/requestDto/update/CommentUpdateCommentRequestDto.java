package com.example.schedule.dto.requestDto.update;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
@Valid
public class CommentUpdateCommentRequestDto {
    @NotBlank(message = "null 이나 빈칸이 들어갈 수 없습니다.")
    String comment;
}
