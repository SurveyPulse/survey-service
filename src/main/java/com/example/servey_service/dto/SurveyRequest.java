package com.example.servey_service.dto;

import java.time.LocalDateTime;
import java.util.List;

public record SurveyRequest(
        String title,
        String description,
        Long creatorUserId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        List<QuestionRequest> questions
) { }
