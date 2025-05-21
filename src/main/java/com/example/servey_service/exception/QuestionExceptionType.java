package com.example.servey_service.exception;

import com.example.global.exception.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum QuestionExceptionType implements ExceptionType {

    NOT_FOUND_QUESTION(7010, "질문을 찾을 수 없습니다."),
    INVALID_QUESTION_FORMAT(7020, "유효하지 않은 질문 형식입니다."),
    UNAUTHORIZED_QUESTION_MODIFICATION(7030, "질문 수정 권한이 없습니다.");

    private final int statusCode;
    private final String message;
}
