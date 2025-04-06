package com.example.schedule.repository;

import com.example.schedule.dto.responseDto.LoginResponseDto;

public interface LoginCheckedRepository {
    LoginResponseDto checkPassword(String email, String password);


}

