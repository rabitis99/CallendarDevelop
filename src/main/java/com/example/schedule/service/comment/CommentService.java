package com.example.schedule.service.comment;

import com.example.schedule.dto.requestDto.common.CommentRequestDto;
import com.example.schedule.dto.requestDto.common.CommentResponseDto;
import com.example.schedule.dto.requestDto.update.CommentUpdateCommentRequestDto;

import java.util.List;

public interface CommentService {

    CommentResponseDto saveComment(Long savedAuthorId,Long scheduleId,Long authorId, CommentRequestDto commentRequestDto);

    List<CommentResponseDto> findCommentBySchedule(Long scheduleId);

    CommentResponseDto updateComment(Long authorId, Long id, CommentUpdateCommentRequestDto commetUpdateCommentRequestDto);

    String deleteComment(Long id);

}
