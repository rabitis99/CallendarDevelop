package com.example.schedule.repository;

import com.example.schedule.Dto.responseDto.LoginResponseDto;
import com.example.schedule.config.PasswordEncoder;
import com.example.schedule.entity.Author;
import com.example.schedule.exception.CustomException;
import com.example.schedule.exception.ErrorCode;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
@RequiredArgsConstructor
public class LoginCheckedRepositoryImpl implements LoginCheckedRepository {
    // 이 부분 블로그 참고(암기필수)
    private final EntityManager entityManager;
    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponseDto checkPassword(String email, String password) {
        //sqpl문 공부
        Author storedAuthor =entityManager.createQuery("SELECT A FROM Author A where A.email=:email",Author.class)
                .setParameter("email",email)
                .getSingleResult();


        if (!passwordEncoder.matches(password,storedAuthor.getPassword())){
            throw new CustomException(ErrorCode.Password, new String[]{"home/login", "잘못된 입력입니다"});
        }else {
            Long storedAuthorId=storedAuthor.getId();
            LoginResponseDto loginResponseDto =new LoginResponseDto("로그인에 성공했습니다.",storedAuthorId,true);

        return loginResponseDto;
        }


    }
}

