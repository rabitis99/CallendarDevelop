package com.example.schedule.service.schedule;

import com.example.schedule.dto.requestDto.common.ScheduleRequestDto;
import com.example.schedule.dto.responseDto.SchedulePageResponseDto;
import com.example.schedule.dto.responseDto.ScheduleResponseDto;
import com.example.schedule.entity.Author;
import com.example.schedule.entity.Schedule;
import com.example.schedule.exception.CustomException;
import com.example.schedule.exception.ErrorCode;
import com.example.schedule.repository.AuthorRepository;
import com.example.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final AuthorRepository authorRepository;

    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule=new Schedule(scheduleRequestDto);

        Author author=authorRepository.findById(scheduleRequestDto.getId()).orElseThrow(()->new RuntimeException("잘못된 아이디"));

        schedule.updateAuthor(author);

        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(
                schedule.getTask(),
                schedule.getTitle(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getAuthor().getAuthorName(),
                schedule.getAuthor().getEmail());
    }

    @Override
    public ScheduleResponseDto findScheduleById(long id) {

        Schedule schedule=scheduleRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.INVALID_ID, new String[]{"잘못된 입력입니다."}));

        return new ScheduleResponseDto(
                schedule.getTask(),
                schedule.getTitle(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getAuthor().getAuthorName(),
                schedule.getAuthor().getEmail());
    }

    @Override
    public List<ScheduleResponseDto> findScheduleAll() {

        return scheduleRepository.findAll()
                .stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getTask(),
                        schedule.getTitle(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt(),
                        schedule.getAuthor().getAuthorName(),
                        schedule.getAuthor().getEmail()))
                .toList();

    }

    @Override
    //@Transactional 변경 감지를 통해 자동으로 업데이트
    @Transactional
    public ScheduleResponseDto updateSchedule(long id,ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule=scheduleRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.INVALID_ID, new String[]{"잘못된 입력입니다."}));

        //단축 필요성
        if (scheduleRequestDto.getTask()!=null&& !scheduleRequestDto.getTask().isEmpty()){
            schedule.updateTask(scheduleRequestDto.getTask());
        }
        if (scheduleRequestDto.getTitle()!=null && !scheduleRequestDto.getTitle().isEmpty()){
            schedule.updateTitle(scheduleRequestDto.getTitle());
        }
        schedule.setUpdatedAt(LocalDateTime.now());

        return new ScheduleResponseDto(
                schedule.getTask(),
                schedule.getTitle(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getAuthor().getAuthorName(),
                schedule.getAuthor().getEmail());
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByAuthorId(long AuthorId) {

        return scheduleRepository.findByAuthor_Id(AuthorId).stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getTask(),
                        schedule.getTitle(),
                        schedule.getCreatedAt(),
                        schedule.getUpdatedAt(),
                        schedule.getAuthor().getAuthorName(),
                        schedule.getAuthor().getEmail()))
                .toList();

    }

    @Override
    public String deleteSchedule(long id) {
        //jpa 트랙젹션의 이해-> comit은 맨마지막에 발생
        try {
            scheduleRepository.deleteById(id);
            return "삭제되었습니다.";
        } catch (Exception e) {
            return "삭제가 되지 않았습니다";
        }

    }
    @Override
    public Page<SchedulePageResponseDto> getSchedules(Pageable pageable) {

        return scheduleRepository.findSchedulePageWithCommentCount(pageable);
    }
}
