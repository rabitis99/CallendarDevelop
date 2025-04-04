package com.example.schedule.repository;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.example.schedule.Dto.responseDto.LoginResponseDto;
import org.springframework.stereotype.Component;

public interface LoginCheckedRepository {
    LoginResponseDto checkPassword(String email, String password);


}

