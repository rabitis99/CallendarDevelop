package com.example.schedule.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SchedulePageResponseDto {
    private String title;
    private String task;
    private long commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String authorName;

}
