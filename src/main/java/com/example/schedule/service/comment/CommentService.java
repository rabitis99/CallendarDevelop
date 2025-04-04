package com.example.schedule.service.comment;

import com.example.schedule.Dto.requestDto.common.CommentRequestDto;
import com.example.schedule.Dto.requestDto.update.CommetUpdateCommentRequestDto;
import com.example.schedule.Dto.responseDto.CommentResponseDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto saveComment(Long authorId, CommentRequestDto commentRequestDto);

    List<CommentResponseDto> findCommentBySchedule(Long scheduleId);

    CommentResponseDto updateComment(Long authorId,Long id,CommetUpdateCommentRequestDto commetUpdateCommentRequestDto);

    String deleteComment(Long id);

}
