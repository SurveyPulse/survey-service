package com.example.servey_service.dto.response;

import com.example.servey_service.entity.Question;

public record QuestionResponse(
        Long questionId,
        String questionText
) {
    public static QuestionResponse from(Question question) {
        return new QuestionResponse(
                question.getQuestionId(),
                question.getQuestionText()
        );
    }
}
