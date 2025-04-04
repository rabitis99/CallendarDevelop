package com.example.schedule.Dto.requestDto.update;

import lombok.Getter;

@Getter
public class AuthorUpdatePasswordRequestDto {
    String oldPassword;
    String newPassword;
}
