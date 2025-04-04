package com.example.schedule.service.author;

import com.example.schedule.Dto.requestDto.common.AuthorRequestDto;
import com.example.schedule.Dto.requestDto.update.AuthorUpdatePasswordRequestDto;
import com.example.schedule.Dto.responseDto.AuthorResponseDto;
import com.example.schedule.entity.Author;
import com.example.schedule.config.PasswordEncoder;
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

    @Override
    public AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto) {
        if (authorRepository.existsByEmail(authorRequestDto.getEmail())){
            throw new CustomException(ErrorCode.DuplicateEmail);
        }
        Author author=new Author(authorRequestDto);

        String encodedPassword=passwordEncoder.encode(author.getPassword());

        author.setPassword(encodedPassword);

        return new AuthorResponseDto(authorRepository.save(author));
    }

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

        Author author=authorRepository.findById(id).orElseThrow(()->new RuntimeException("안돼요"));

        return new AuthorResponseDto(author);
    }

    @Override
    @Transactional
    public AuthorResponseDto updateAuthor(long id, String name) {

        Author author=authorRepository.findById(id).orElseThrow(()->new RuntimeException("안돼요"));

        author.updateAuthorName(name);

        return new AuthorResponseDto(author);
    }
    @Transactional
    @Override
    public String updateAuthorPassword(long id, AuthorUpdatePasswordRequestDto authorUpdatePasswordRequestDto) {
        Author author=authorRepository.findById(id).orElseThrow();
        if (passwordEncoder.matches(authorUpdatePasswordRequestDto.getOldPassword(),author.getPassword())){
            author.setPassword(passwordEncoder.encode(authorUpdatePasswordRequestDto.getNewPassword()));
            return "비밀번호가 변경됐습니다.";
        } else {
            return "비밀번호가 일치하지 않습니다.";
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
