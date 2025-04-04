package com.example.schedule.controller;

import com.example.schedule.Dto.requestDto.common.CommentRequestDto;
import com.example.schedule.Dto.requestDto.update.CommetUpdateCommentRequestDto;
import com.example.schedule.Dto.responseDto.CommentResponseDto;
import com.example.schedule.login.Const;
import com.example.schedule.service.comment.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    @PostMapping("/{scheduleId}/{id}")
    public ResponseEntity<CommentResponseDto> saveComment(@Valid @RequestBody CommentRequestDto commentRequestDto,
                                                          HttpServletRequest httpServletRequest){
        Long authorId =(Long) httpServletRequest.getSession(false).getAttribute(Const.LOGIN_USER);

        return new ResponseEntity<>(commentService.saveComment(authorId,commentRequestDto),HttpStatus.CREATED);
    }
    @GetMapping("/{scheduleId}")
    public ResponseEntity<List<CommentResponseDto>> findComment(@PathVariable Long scheduleId){

        return new ResponseEntity<>(commentService.findCommentBySchedule(scheduleId),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CommentResponseDto> updateComment(@PathVariable Long id,
                                                            @PathVariable Long scheduleId,
                                                            @RequestBody CommetUpdateCommentRequestDto commetUpdateCommentRequestDto,
                                                            HttpServletRequest httpServletRequest){
        Long authorId=(Long) httpServletRequest.getSession(false).getAttribute(Const.LOGIN_USER);


        return new ResponseEntity<>(commentService.updateComment(authorId,id,commetUpdateCommentRequestDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable Long id, @PathVariable Long scheduleId){

        return new ResponseEntity<>(commentService.deleteComment(id),HttpStatus.OK);
    }


}
