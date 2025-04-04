package com.example.schedule.Dto.requestDto.common;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
@Valid
public class AuthorRequestDto {
    @NotEmpty(message = "이름은 비워져 있으면 안됩니다.")
    @Size(min = 2,message = "이름의 길이가 너무 짧습니다. ")
    String authorName;
    @NotEmpty(message = "이메일은 비워져 있으면 안됩니다.")
    @Email(message = "이메일 형식이어야 합니다.")
    String email;
//테스트 후에 마무리    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{5,}$", message = "비밀번호 형식이 올바르지 않습니다. 5자 이상, 대소문자 포함, 숫자 및 특수문자(@$!%*?&#) 포함")
    String password;
}
