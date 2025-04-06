package com.example.schedule.service.author;

import com.example.schedule.dto.requestDto.common.AuthorRequestDto;
import com.example.schedule.dto.requestDto.update.AuthorUpdatePasswordRequestDto;
import com.example.schedule.dto.responseDto.AuthorResponseDto;

public interface AuthorService {
    AuthorResponseDto saveAuthor(AuthorRequestDto authorRequestDto);

    String findAuthorByEmail(String email);

    AuthorResponseDto findAuthorById(long id);

    AuthorResponseDto updateAuthor(long id,String name);

    String updateAuthorPassword(long id, AuthorUpdatePasswordRequestDto authorUpdatePasswordRequestDto);

    String deleteAuthor(long id);


}
