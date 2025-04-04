package com.example.schedule.repository;

import com.example.schedule.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Long>,LoginCheckedRepository {
    boolean existsByEmail(String email);

}
