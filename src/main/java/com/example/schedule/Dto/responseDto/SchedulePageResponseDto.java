package com.example.schedule.Dto.responseDto;

import com.example.schedule.entity.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class SchedulePageResponseDto<T> {
    private String title;
    private String task;
    private int commentCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String authorName;

    public SchedulePageResponseDto(Schedule schedule) {

        this.title=schedule.getTitle();
        this.task=schedule.getTask();
        this.commentCount=schedule.getComments().size();
        this.createdAt=schedule.getCreatedAt();
        this.updatedAt=schedule.getUpdatedAt();
        this.authorName=schedule.getAuthor().getAuthorName();

    }
}
