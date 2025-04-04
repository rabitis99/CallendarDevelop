package com.example.schedule.Dto.responseDto;

import com.example.schedule.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final String task;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    private final String authorName;
    private final String authorEmail;

    public ScheduleResponseDto(Schedule schedule) {
        this.authorName=schedule.getAuthor().getAuthorName();
        this.authorEmail=schedule.getAuthor().getEmail();
        this.title=schedule.getTitle();
        this.createdAt=schedule.getCreatedAt();
        this.updatedAt=schedule.getUpdatedAt();
        this.task=schedule.getTask();
    }

}
