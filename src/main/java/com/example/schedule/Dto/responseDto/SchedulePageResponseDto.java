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

    public static SchedulePageResponseDto fromEntity(Schedule schedule){
        return new SchedulePageResponseDto(
                schedule.getTitle(),
                schedule.getTask(),
                schedule.getComments().size(),
                schedule.getCreatedAt(),
                schedule.getUpdatedAt(),
                schedule.getAuthor().getAuthorName()
        );
    }
}
