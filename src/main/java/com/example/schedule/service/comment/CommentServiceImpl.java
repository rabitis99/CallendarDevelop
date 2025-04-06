package com.example.schedule.service.comment;

import com.example.schedule.dto.requestDto.common.CommentRequestDto;
import com.example.schedule.dto.requestDto.common.CommentResponseDto;
import com.example.schedule.dto.requestDto.update.CommentUpdateCommentRequestDto;
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
import java.util.List;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService{

    private final ScheduleRepository scheduleRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;

    @Override
    public CommentResponseDto saveComment(Long savedAuthorId, Long scheduleId, Long authorId, CommentRequestDto commentRequestDto) {

        if(!authorRepository.existsById(authorId)){
            throw new CustomException(ErrorCode.INVALID_ID);
        };

        Schedule schedule=scheduleRepository.findById(scheduleId).orElseThrow(
                ()->new CustomException(ErrorCode.INVALID_ID));

        Author author =authorRepository.findById(authorId).orElseThrow(
                ()->new CustomException(ErrorCode.INVALID_ID));

        Comment comment=new Comment(commentRequestDto.getComment(),author,schedule);
        commentRepository.save(comment);
        return new CommentResponseDto(
                 comment.getComment()
                ,comment.getId()
                ,comment.getCreatedAt()
                ,comment.getUpdatedAt());
    }

    @Override
    public List<CommentResponseDto> findCommentBySchedule(Long scheduleId) {


        return commentRepository.findAllBySchedule_Id(scheduleId)
                .stream()
                .map(comment -> new CommentResponseDto(
                         comment.getComment()
                        ,comment.getId()
                        ,comment.getCreatedAt()
                        ,comment.getUpdatedAt()))
                .toList();
    }
    @Transactional
    @Override
    public CommentResponseDto updateComment(Long authorId, Long id, CommentUpdateCommentRequestDto commetUpdateCommentRequestDto) {

        Comment comment=commentRepository.findById(id).orElseThrow(
                ()->new CustomException(ErrorCode.INVALID_ID));

        Author author=authorRepository.findById(authorId).orElseThrow(
                ()->new CustomException(ErrorCode.INVALID_ID));

        if (!comment.getAuthor().getPassword().equals(author.getPassword())){
            throw new CustomException(ErrorCode.INVALID_PASSWORD,new String[]{"잘못된 비밀번호입니다"});
        }

        comment.updateContent(commetUpdateCommentRequestDto.getComment());
        comment.setUpdatedAt(LocalDateTime.now());

        return new CommentResponseDto(
                 comment.getComment()
                ,comment.getId()
                ,comment.getCreatedAt()
                ,comment.getUpdatedAt());
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
