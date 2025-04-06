package com.example.schedule.service.author;

import com.example.schedule.dto.requestDto.common.AuthorRequestDto;
import com.example.schedule.dto.requestDto.update.AuthorUpdatePasswordRequestDto;
import com.example.schedule.dto.responseDto.AuthorResponseDto;
import com.example.schedule.config.PasswordEncoder;
import com.example.schedule.entity.Author;
import com.example.schedule.exception.CustomException;
import com.example.schedule.exception.ErrorCode;
import com.example.schedule.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;
    private final PasswordEncoder passwordEncoder;
    //생성
    @Override
    public AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto){
        //이메일 중복 확인
        if (authorRepository.existsByEmail(authorRequestDto.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_SAVED_EMAIL);
        }

        String encodedPassword=passwordEncoder.encode(authorRequestDto.getPassword());

        Author author=new Author(authorRequestDto.getAuthorName(),authorRequestDto.getEmail(), encodedPassword);

        authorRepository.save(author);
        // 코드 형식을 변화시킨 이유: 나중에 추가 시키기 편리(확장성)
        return new AuthorResponseDto(
                author.getId(),
                author.getAuthorName(),
                author.getEmail(),
                author.getCreatedAt(),
                author.getUpdatedAt()
        );
    }
    //가입확인
    @Override
    public String findAuthorByEmail(String email) {
        if (authorRepository.existsByEmail(email)){
            return "조회하신 "+ email +"로 가입하셨습니다.";
        }
        else {
            return "해당 이메일로 회원가입 가능하십니다";
        }
    }

    @Override
    public AuthorResponseDto findAuthorById(long id) {

        Author author=authorRepository.findById(id).orElseThrow(
                ()->new CustomException(ErrorCode.INVALID_ID));

        return new AuthorResponseDto(
                author.getId(),
                author.getAuthorName(),
                author.getEmail(),
                author.getCreatedAt(),
                author.getUpdatedAt()
        );
    }

    @Transactional
    @Override
    public AuthorResponseDto updateAuthor(long id, String name) {
        Author author=authorRepository.findById(id).orElseThrow(
                ()->new CustomException(ErrorCode.INVALID_ID));

        author.updateAuthorName(name);

        return new AuthorResponseDto(
                author.getId(),
                author.getAuthorName(),
                author.getEmail(),
                author.getCreatedAt(),
                author.getUpdatedAt()
        );
    }
    @Transactional
    @Override
    public String updateAuthorPassword(long id, AuthorUpdatePasswordRequestDto authorUpdatePasswordRequestDto) {
        Author author=authorRepository.findById(id).orElseThrow(
                ()->new CustomException(ErrorCode.INVALID_ID));

        if (passwordEncoder.matches(authorUpdatePasswordRequestDto.getOldPassword(),author.getPassword())){
            author.setPassword(passwordEncoder.encode(authorUpdatePasswordRequestDto.getNewPassword()));
            return "비밀번호가 변경됐습니다.";
        } else {
            throw new CustomException(ErrorCode.INVALID_PASSWORD,new String[]{"비밀번호를 확인해주세요"});
        }

    }
    @Override
    public String deleteAuthor(long id) {
        try {
            authorRepository.deleteById(id);
            return "삭제가 됐습니다.";
        } catch (Exception e) {
            return "삭제가 안됐습니다.";
        }
    }
}
