package com.example.servey_service.dto.response;

import com.example.servey_service.entity.Question;

import java.time.LocalDateTime;

public record QuestionWithSurveyDto(
        Long questionId,
        String questionText,
        Long surveyId,
        String title,
        String description,
        Long creatorUserId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String status
) {
    public static QuestionWithSurveyDto from(Question question) {
        return new QuestionWithSurveyDto(
                question.getQuestionId(),
                question.getQuestionText(),
                question.getSurvey().getSurveyId(),
                question.getSurvey().getTitle(),
                question.getSurvey().getDescription(),
                question.getSurvey().getCreatorUserId(),
                question.getSurvey().getStartTime(),
                question.getSurvey().getEndTime(),
                question.getSurvey().getStatus().name()
        );
    }
}
