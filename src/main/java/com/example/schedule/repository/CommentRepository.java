package com.example.schedule.repository;

import com.example.schedule.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllBySchedule_Id(Long scheduleId); 
}
