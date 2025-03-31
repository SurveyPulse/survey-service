package com.example.servey_service.dto;

import com.example.servey_service.entity.SurveyStatus;

import java.time.LocalDateTime;
import java.util.List;

public record SurveyRequest(
        String title,
        String description,
        Long creatorUserId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        SurveyStatus status,
        List<QuestionRequest> questions
) { }
