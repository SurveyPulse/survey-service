package com.example.servey_service.dto.response;

import com.example.servey_service.entity.Survey;
import com.example.servey_service.entity.SurveyStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record SurveyResponse(
        Long surveyId,
        String title,
        String description,
        String creatorUsername,
        LocalDateTime startTime,
        LocalDateTime endTime,
        SurveyStatus status,
        List<QuestionResponse> questions
) {
    public static SurveyResponse from(Survey survey, String creatorUsername) {
        List<QuestionResponse> questionResponses = survey.getQuestions().stream()
                                                         .map(QuestionResponse::from)
                                                         .collect(Collectors.toList());
        return new SurveyResponse(
                survey.getSurveyId(),
                survey.getTitle(),
                survey.getDescription(),
                creatorUsername,
                survey.getStartTime(),
                survey.getEndTime(),
                survey.getStatus(),
                questionResponses
        );
    }
}
