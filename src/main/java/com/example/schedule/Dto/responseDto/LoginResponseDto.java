package com.example.schedule.Dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginResponseDto {
    //이름,이메일 담을 수도 있지만, 현재는 필요가 없기에 추가X
    /*
    우측 상단에 로그인 정보
     */
    String message;
    Long id;
    boolean loginCheck;
}
