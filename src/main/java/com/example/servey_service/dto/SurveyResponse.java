package com.example.servey_service.dto;

import com.example.servey_service.entity.Survey;
import com.example.servey_service.entity.SurveyStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record SurveyResponse(
        Long surveyId,
        String title,
        String description,
        Long creatorUserId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        SurveyStatus status,
        List<QuestionResponse> questions
) {
    public static SurveyResponse from(Survey survey) {
        List<QuestionResponse> questionResponses = survey.getQuestions().stream()
                                                         .map(QuestionResponse::from)
                                                         .collect(Collectors.toList());
        return new SurveyResponse(
                survey.getSurveyId(),
                survey.getTitle(),
                survey.getDescription(),
                survey.getCreatorUserId(),
                survey.getStartTime(),
                survey.getEndTime(),
                survey.getStatus(),
                questionResponses
        );
    }
}
