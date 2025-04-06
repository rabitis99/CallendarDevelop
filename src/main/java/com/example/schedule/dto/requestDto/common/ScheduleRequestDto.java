package com.example.schedule.dto.requestDto.common;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
@Valid
public class ScheduleRequestDto {
    @NotNull(message = "id는 알아서 입력됩니다.")
    Long id;
    @NotEmpty(message = "할일은 비워져 있으면 안됩니다")
    String task;
    @NotEmpty(message = "제목은 비워져 있으면 안됩니다.")
    String title;
    public void updateId(Long id){
        this.id=id;
    }
}
