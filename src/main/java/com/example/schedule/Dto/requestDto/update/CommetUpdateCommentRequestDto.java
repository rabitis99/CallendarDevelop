package com.example.schedule.Dto.requestDto.update;

import jakarta.validation.Valid;
import lombok.Getter;

@Getter
@Valid
public class CommetUpdateCommentRequestDto {
    String comment;
}
