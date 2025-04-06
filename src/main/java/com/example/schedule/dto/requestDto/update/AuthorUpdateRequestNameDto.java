package com.example.schedule.dto.requestDto.update;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthorUpdateRequestNameDto {
    @NotEmpty(message = "이름은 비워져 있으면 안됩니다.")
    @Size(min = 2,message = "이름의 길이가 너무 짧습니다. ")
    String authorName;
}
