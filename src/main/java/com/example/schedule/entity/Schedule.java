package com.example.schedule.entity;


import com.example.schedule.Dto.requestDto.common.ScheduleRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name="schedule")
public class Schedule extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(columnDefinition = "longtext",nullable = false)//longtext 데이트타입 설정
    private String task;
    @Column(length = 100,nullable = false)
    private String title;
    @OneToMany(mappedBy = "schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public Schedule(ScheduleRequestDto scheduleRequestDto){
        this.task=scheduleRequestDto.getTask();
        this.title=scheduleRequestDto.getTitle();

    }

    //세터는 자제-> null이 들어와도 체크불가
    public void updateTask(String task){
        if (task == null){
            throw new RuntimeException("task는 null이 될 수 없습니다.");
        }
        this.task=task;
    }
    public void updateTitle(String title){
        this.title=title;
    }
    public void updateAuthor(Author author){
        this.author=author;
    }
}
