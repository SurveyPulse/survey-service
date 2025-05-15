package com.example.servey_service.repository;

import com.example.servey_service.entity.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    Page<Survey> findByTitleContainingIgnoreCase(String title, Pageable pageable);

    @Query(value = "SELECT s FROM Survey s WHERE s.startTime <= :now AND s.endTime >= :now")
    Page<Survey> findActiveSurveys(@Param("now") LocalDateTime now, Pageable pageable);

}
