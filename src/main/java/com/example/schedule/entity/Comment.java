package com.example.schedule.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "longtext")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;
    //일정작성자랑 댓글작성자가 다를 경우를 대비
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Author_id")
    private Author author;

    public Comment(String comment,Author author,Schedule schedule) {
        this.comment=comment;
        this.author=author;
        this.schedule=schedule;
    }

    public void updateContent(String comment) {

        this.comment=comment;
    }
}
