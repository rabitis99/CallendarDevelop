package com.example.schedule.controller;


import com.example.schedule.dto.requestDto.common.ScheduleRequestDto;
import com.example.schedule.dto.responseDto.SchedulePageResponseDto;
import com.example.schedule.dto.responseDto.ScheduleResponseDto;
import com.example.schedule.login.Const;
import com.example.schedule.service.schedule.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//RequestMapping("/schedules") 확인철저히 할것
@RestController()
@RequestMapping("/schedules")
@RequiredArgsConstructor
@Validated
public class ScheduleController {
    private final ScheduleService scheduleService;
    @PostMapping
    ResponseEntity<ScheduleResponseDto> createSchedule(@Valid @RequestBody ScheduleRequestDto scheduleRequestDto, HttpServletRequest httpServletRequest) {

        HttpSession session=httpServletRequest.getSession();
        Long id=(Long)session.getAttribute(Const.LOGIN_USER);
        scheduleRequestDto.updateId(id);

        return new ResponseEntity<>(scheduleService.saveSchedule(scheduleRequestDto), HttpStatus.OK);
    }
    @GetMapping
    ResponseEntity<List<ScheduleResponseDto>>findSchedule (){

        return new ResponseEntity<>(scheduleService.findScheduleAll(),HttpStatus.OK);
    }
    @GetMapping("/{authorId}")
    ResponseEntity<List<ScheduleResponseDto>>findScheduleByAuthorId (
            @PathVariable Long authorId){

        return new ResponseEntity<>(scheduleService.findScheduleByAuthorId(authorId),HttpStatus.OK);
    }
    @GetMapping({"/{authorId}/{id}"})
    ResponseEntity<ScheduleResponseDto>findScheduleById (
            @PathVariable Long authorId,
            @PathVariable Long id){

        return new ResponseEntity<>(scheduleService.findScheduleById(id),HttpStatus.OK);
    }
    @PutMapping({"/{authorId}/{id}"})
    ResponseEntity<ScheduleResponseDto>updateScheduleTitle (
            @PathVariable Long authorId,
            @PathVariable Long id,
            @RequestBody ScheduleRequestDto scheduleRequestDto){

        return new ResponseEntity<>(scheduleService.updateSchedule(id,scheduleRequestDto),HttpStatus.OK);
    }
    @DeleteMapping("/{authorId}/{id}")
    ResponseEntity<String>deleteScheduleTitle (
            @PathVariable Long authorId,
            @PathVariable Long id){

        return new ResponseEntity<>(scheduleService.deleteSchedule(id),HttpStatus.OK);
    }

    @GetMapping({"/all"})
    public ResponseEntity<Page<SchedulePageResponseDto>> getSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size

    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("updatedAt").descending());
        Page<SchedulePageResponseDto> schedules = scheduleService.getSchedules(pageable);
        return new ResponseEntity<>(schedules,HttpStatus.OK);
    }



}
