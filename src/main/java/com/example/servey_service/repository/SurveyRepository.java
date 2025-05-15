package com.example.servey_service.repository;

import com.example.servey_service.entity.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {

    Page<Survey> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}
