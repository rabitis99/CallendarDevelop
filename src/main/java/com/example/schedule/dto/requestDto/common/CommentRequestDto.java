package com.example.schedule.dto.requestDto.common;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CommentRequestDto {
    Long schedule_id;
    @NotNull(message = "null값이 들어갈 수 없습니다")
    String comment;

}
