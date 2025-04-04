package com.example.schedule.service.schedule;

import com.example.schedule.Dto.requestDto.common.ScheduleRequestDto;
import com.example.schedule.Dto.responseDto.SchedulePageResponseDto;
import com.example.schedule.Dto.responseDto.ScheduleResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleService {
    ScheduleResponseDto saveSchedule(ScheduleRequestDto scheduleRequestDto);

    ScheduleResponseDto findScheduleById(long id);

    List<ScheduleResponseDto> findScheduleAll();

    ScheduleResponseDto updateSchedule(long id,ScheduleRequestDto scheduleRequestDto);

    List<ScheduleResponseDto> findScheduleByAuthorId(long AuthorId);

    String deleteSchedule(long id);

    Page<SchedulePageResponseDto> getSchedules(Pageable pageable) ;

}
