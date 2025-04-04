package com.example.schedule.repository;

import com.example.schedule.entity.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
//,CustomScheduleRepository
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule,Long>{
    //아래 방법이 나을지 jqpl가 나을까?
    List<Schedule> findByAuthor_Id(Long authorId);
//    default Schedule findByIdElseThrow(Long id){
//        return findById(id).orElseThrow(()->new RuntimeException("없어요"));
//    }
    Page<Schedule> findAllByOrderByUpdatedAtDesc(Pageable pageable);
    /*
    Page<>->postMan
        "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
            "sorted": false,
            "empty": true,
            "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 2,
    "totalPages": 1,
    "first": true,
    "numberOfElements": 2,
    "size": 10,
    "number": 0,
    "sort": {
        "sorted": false,
        "empty": true,
        "unsorted": true
    },
    "empty": false
    이렇게 나오지만 활용이 어렵->  Page 란 무엇인지 공부를 한 후 수정 필요
    Page<Schedule> findAllByOrderByUpdatedAtDesc(Pageable pageable);


     */
}
