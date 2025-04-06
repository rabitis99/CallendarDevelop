package com.example.schedule.service.login;

import com.example.schedule.dto.requestDto.common.LoginRequestDto;
import com.example.schedule.dto.responseDto.LoginResponseDto;
import com.example.schedule.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
//로그인은 유저관리랑 분리하고 싶었습니다. 같은 레퍼지토리 라도 다르게 분리
public class LoginServiceImpl implements LoginService{
    private final AuthorRepository authorRepository;
    @Override
    public LoginResponseDto authorLogin(LoginRequestDto loginRequestDto) {
        String email=loginRequestDto.getEmail();
        String password=loginRequestDto.getPassword();

        return authorRepository.checkPassword(email,password);
    }
}
