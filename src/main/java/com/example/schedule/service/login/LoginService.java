package com.example.schedule.service.login;

import com.example.schedule.dto.requestDto.common.LoginRequestDto;
import com.example.schedule.dto.responseDto.LoginResponseDto;

public interface LoginService  {
    LoginResponseDto authorLogin(LoginRequestDto loginRequestDto);
}
