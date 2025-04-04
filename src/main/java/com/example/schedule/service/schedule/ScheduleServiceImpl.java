package com.example.schedule.service.schedule;

import com.example.schedule.Dto.requestDto.common.ScheduleRequestDto;
import com.example.schedule.Dto.responseDto.SchedulePageResponseDto;
import com.example.schedule.Dto.responseDto.ScheduleResponseDto;
import com.example.schedule.entity.Author;
import com.example.schedule.entity.Schedule;
import com.example.schedule.exception.CustomException;
import com.example.schedule.exception.ErrorCode;
import com.example.schedule.repository.AuthorRepository;
import com.example.schedule.repository.CommentRepository;
import com.example.schedule.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    private final AuthorRepository authorRepository;
    private final CommentRepository commentRepository;
    @Override
    public ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule=new Schedule(scheduleRequestDto);

        Author author=authorRepository.findById(scheduleRequestDto.getId()).orElseThrow(()->new RuntimeException("잘못된 아이디"));
        schedule.updateAuthor(author);

        return new ScheduleResponseDto(scheduleRepository.save(schedule));
    }

    @Override
    public ScheduleResponseDto findScheduleById(long id) {

        Schedule schedule=scheduleRepository.findById(id).orElseThrow(()->new CustomException(ErrorCode.Id, new String[]{"vc", " "}));

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleAll() {
        List<ScheduleResponseDto> scheduleResponseDtoList=new ArrayList<>();

        for (Schedule schedule:scheduleRepository.findAll()){
            scheduleResponseDtoList.add(new ScheduleResponseDto(schedule));
        }

        return scheduleResponseDtoList;
    }

    @Override
    //@Transactional 변경 감지를 통해 자동으로 업데이트
    @Transactional
    public ScheduleResponseDto updateSchedule(long id,ScheduleRequestDto scheduleRequestDto) {

        Schedule schedule=scheduleRepository.findById(id).orElseThrow((()->new RuntimeException("수고링")));

        //단축 필요성
        if (scheduleRequestDto.getTask()!=null&& !scheduleRequestDto.getTask().isEmpty()){
            schedule.updateTask(scheduleRequestDto.getTask());
        }
        if (scheduleRequestDto.getTitle()!=null && !scheduleRequestDto.getTitle().isEmpty()){
            schedule.updateTitle(scheduleRequestDto.getTitle());
        }
        schedule.setUpdatedAt(LocalDateTime.now());

        return new ScheduleResponseDto(schedule);
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByAuthorId(long AuthorId) {
        List<ScheduleResponseDto> scheduleResponseDtoList=new ArrayList<>();

        for (Schedule schedule:scheduleRepository.findByAuthor_Id(AuthorId)){
            scheduleResponseDtoList.add(new ScheduleResponseDto(schedule));
        }

        scheduleRepository.findByAuthor_Id(AuthorId);
        return scheduleResponseDtoList;
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
        Page<Schedule> schedules =scheduleRepository.findAllByOrderByUpdatedAtDesc(pageable);

        return schedules.map(SchedulePageResponseDto::fromEntity);
    }
}
