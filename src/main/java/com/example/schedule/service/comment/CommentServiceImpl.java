package com.example.schedule.service.comment;

import com.example.schedule.Dto.requestDto.common.CommentRequestDto;
import com.example.schedule.Dto.requestDto.update.CommetUpdateCommentRequestDto;
import com.example.schedule.Dto.responseDto.CommentResponseDto;
import com.example.schedule.entity.Author;
import com.example.schedule.entity.Comment;
import com.example.schedule.entity.Schedule;
import com.example.schedule.exception.CustomException;
import com.example.schedule.exception.ErrorCode;
import com.example.schedule.repository.AuthorRepository;
import com.example.schedule.repository.CommentRepository;
import com.example.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final ScheduleRepository scheduleRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto saveComment(Long authorId, CommentRequestDto commentRequestDto) {
        Schedule schedule=scheduleRepository.findById(commentRequestDto.getSchedule_id()).orElseThrow();
        Author author =authorRepository.findById(authorId).orElseThrow();
        Comment comment=new Comment(commentRequestDto.getComment(),author,schedule);

        return new CommentResponseDto(commentRepository.save(comment));
    }

    @Override
    public List<CommentResponseDto> findCommentBySchedule(Long scheduleId) {
        List<CommentResponseDto> commentResponseDtoList=new ArrayList<>();
        for (Comment comment:commentRepository.findAllBySchedule_Id(scheduleId)){
            commentResponseDtoList.add(new CommentResponseDto(comment));
        }
        return commentResponseDtoList;
    }
    @Transactional
    @Override
    public CommentResponseDto updateComment(Long authorId, Long id, CommetUpdateCommentRequestDto commetUpdateCommentRequestDto) {
        Comment comment=commentRepository.findById(id).orElseThrow();
        Author author=authorRepository.findById(authorId).orElseThrow();

        if (!comment.getAuthor().getPassword().equals(author.getPassword())){
            throw new CustomException(ErrorCode.Id,new String[]{"",""});
        }

        comment.updateContent(commetUpdateCommentRequestDto.getComment());
        comment.setUpdatedAt(LocalDateTime.now());

        return new CommentResponseDto(comment);
    }

    @Override
    public String deleteComment(Long id) {
        try {
            commentRepository.deleteById(id);
            return "삭제됐습니다.";
        } catch (Exception e) {
            return "삭제가 되지 않았습니다.";
        }
    }
}
