package com.example.schedule.service.author;

import com.example.schedule.Dto.requestDto.common.AuthorRequestDto;
import com.example.schedule.Dto.requestDto.update.AuthorUpdatePasswordRequestDto;
import com.example.schedule.Dto.responseDto.AuthorResponseDto;

public interface AuthorService {

    AuthorResponseDto saveAuthor(AuthorRequestDto AuthorRequestDto);

    String findAuthorByEmail(String email);

    AuthorResponseDto findAuthorById(long id);

    AuthorResponseDto updateAuthor(long id,String name);

    String updateAuthorPassword(long id, AuthorUpdatePasswordRequestDto authorUpdatePasswordRequestDto);

    String deleteAuthor(long id);

}
