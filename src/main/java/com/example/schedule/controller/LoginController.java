package com.example.schedule.controller;


import com.example.schedule.Dto.requestDto.common.LoginRequestDto;
import com.example.schedule.Dto.responseDto.LoginResponseDto;
import com.example.schedule.service.login.LoginService;
import com.example.schedule.login.Const;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
@Validated
public class LoginController {
        private final LoginService loginService;
        @PostMapping("/login")
        //HttpServletRequest httpServletRequest 구조 외우기
        public ResponseEntity<LoginResponseDto> authorLogin(@Valid @RequestBody LoginRequestDto loginRequestDto, HttpServletRequest httpServletRequest) {

            LoginResponseDto loginResponseDto=loginService.authorLogin(loginRequestDto);

            HttpSession session=httpServletRequest.getSession();

            session.setAttribute(Const.LOGIN_USER,loginResponseDto.getId());


            return new ResponseEntity<>(loginResponseDto,HttpStatus.OK);
        }
        @PostMapping("/logout")
        public ResponseEntity<String> authorLogout(HttpServletRequest httpServletRequest){

            HttpSession session=httpServletRequest.getSession(false);

            if (session !=null){
                session.invalidate();
            }
            return new ResponseEntity<>("로그아웃 됐습니다",HttpStatus.OK);
        }
}
