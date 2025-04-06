package com.example.schedule.repository;

import com.example.schedule.dto.responseDto.SchedulePageResponseDto;
import com.example.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//,CustomScheduleRepository
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByAuthor_Id(Long authorId);

    @Query(
            value = "SELECT new com.example.schedule.dto.responseDto.SchedulePageResponseDto(" +
                    "s.title, s.task, COUNT(c.id), s.createdAt, s.updatedAt, s.author.authorName) " +
                    "FROM Schedule s LEFT JOIN Comment c ON c.schedule.id = s.id " +
                    "GROUP BY s.title, s.task, s.createdAt, s.updatedAt, s.author.authorName " +
                    "ORDER BY s.updatedAt DESC",

            countQuery = "SELECT COUNT(DISTINCT s.id) FROM Schedule s"
    )
    Page<SchedulePageResponseDto> findSchedulePageWithCommentCount(Pageable pageable);



}
