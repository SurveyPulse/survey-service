package com.example.servey_service.dto.response;

import com.example.servey_service.entity.Survey;
import com.example.servey_service.entity.SurveyStatus;

import java.time.LocalDateTime;

public record SurveyWithoutQuestionResponse(
        Long surveyId,
        String title,
        String description,
        String creatorUsername,
        LocalDateTime startTime,
        LocalDateTime endTime,
        SurveyStatus status
) {
    public static SurveyWithoutQuestionResponse from(Survey survey, String creatorUsername) {
        return new SurveyWithoutQuestionResponse(
                survey.getSurveyId(),
                survey.getTitle(),
                survey.getDescription(),
                creatorUsername,
                survey.getStartTime(),
                survey.getEndTime(),
                survey.getStatus()
        );
    }
}
