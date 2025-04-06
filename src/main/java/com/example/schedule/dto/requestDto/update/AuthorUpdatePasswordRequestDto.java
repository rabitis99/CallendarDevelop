package com.example.schedule.dto.requestDto.update;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class AuthorUpdatePasswordRequestDto {
    @NotBlank(message = "password를 입력해주세요")
    String oldPassword;
    @NotBlank(message = "null 이나 빈칸이 들어갈 수 없습니다.")
    String newPassword;
}
