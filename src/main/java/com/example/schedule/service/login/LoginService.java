package com.example.schedule.service.login;

import com.example.schedule.Dto.requestDto.common.LoginRequestDto;
import com.example.schedule.Dto.responseDto.LoginResponseDto;

public interface LoginService  {
    LoginResponseDto authorLogin(LoginRequestDto loginRequestDto);
}
