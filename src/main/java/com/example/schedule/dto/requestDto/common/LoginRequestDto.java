package com.example.schedule.dto.requestDto.common;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
@Valid
public class LoginRequestDto {
    @NotEmpty(message = "이메일은 비워져 있으면 안됩니다.")
    @Email(message = "이메일 형식이어야 합니다.")
    String email;
    @NotEmpty(message = "비밀번호는 비워져 있으면 안됩니다.")
    String password;
}
