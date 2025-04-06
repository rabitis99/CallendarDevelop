package com.example.schedule.controller;

import com.example.schedule.dto.requestDto.common.AuthorRequestDto;
import com.example.schedule.dto.requestDto.update.AuthorUpdatePasswordRequestDto;
import com.example.schedule.dto.requestDto.update.AuthorUpdateRequestNameDto;
import com.example.schedule.dto.responseDto.AuthorResponseDto;
import com.example.schedule.exception.CustomException;
import com.example.schedule.exception.ErrorCode;
import com.example.schedule.login.Const;
import com.example.schedule.service.author.AuthorService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController()
@RequestMapping("/authors")
@RequiredArgsConstructor
@Validated
public class AuthorController {
    private final AuthorService authorService;
    //생성(회원가입)-level2
    @PostMapping()
    ResponseEntity<AuthorResponseDto>saveAuthor(@Valid @RequestBody  AuthorRequestDto authorRequestDto){

        return new ResponseEntity(authorService.saveAuthor(authorRequestDto),HttpStatus.CREATED);
    }
    //회원가입(이메일 조회)
    @GetMapping()
    ResponseEntity<String>findAuthorByEmail(@RequestParam String email){
        return new ResponseEntity<>(authorService.findAuthorByEmail(email),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<AuthorResponseDto>findAuthorById(@PathVariable Long id, HttpServletRequest httpServletRequest) {
        Long authorId=(Long)httpServletRequest.getSession(false).getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(authorService.findAuthorById(id),HttpStatus.OK);
    }

    @PatchMapping ("/{id}/name")
    ResponseEntity<AuthorResponseDto>updateAuthorName(@PathVariable @PositiveOrZero(message = "음수나 0값은 입력하시면 안됩니다.") Long id,
                                                  @Valid @RequestBody AuthorUpdateRequestNameDto authorUpdateRequestNameDto,
                                                      HttpServletRequest httpServletRequest) {

        Long authorId=(Long)httpServletRequest.getSession(false).getAttribute(Const.LOGIN_USER);
        if (!Objects.equals(id, authorId)){
            throw new CustomException(ErrorCode.UNAUTHORIZED_ID,new String[]{"다른 아이디로 로그인을 하시거나 Path를 확인해주세요","/authors/"+authorId+"/name"});
        }

        String name=authorUpdateRequestNameDto.getAuthorName();
        return new ResponseEntity<>(authorService.updateAuthor(id,name),HttpStatus.OK);
    }
    @PatchMapping ("/{id}/password")
    ResponseEntity<String>updateAuthorPassword(@PathVariable @PositiveOrZero(message = "음수나 0값은 입력하시면 안됩니다.") Long id,
                                               @Valid @RequestBody AuthorUpdatePasswordRequestDto authorUpdatePasswordRequestDto,
                                               HttpServletRequest httpServletRequest) {
        Long authorId=(Long)httpServletRequest.getSession(false).getAttribute(Const.LOGIN_USER);
        if (!Objects.equals(id, authorId)){
            throw new CustomException(ErrorCode.UNAUTHORIZED_ID,new String[]{"다른 아이디로 로그인을 하시거나 Path를 확인해주세요","/authors/"+authorId+"/password"});
        }
        return new ResponseEntity<>(authorService.updateAuthorPassword(id,authorUpdatePasswordRequestDto),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<String>deleteAuthor(@PathVariable Long id,HttpServletRequest httpServletRequest){

        Long authorId=(Long)httpServletRequest.getSession(false).getAttribute(Const.LOGIN_USER);

        if (!Objects.equals(id, authorId)){
            throw new CustomException(ErrorCode.UNAUTHORIZED_ID,new String[]{"다른 아이디로 로그인을 하시거나 Path를 확인해주세요","/authors/"+authorId});
        }

        return new ResponseEntity<>(authorService.deleteAuthor(id),HttpStatus.OK);
    }


}
