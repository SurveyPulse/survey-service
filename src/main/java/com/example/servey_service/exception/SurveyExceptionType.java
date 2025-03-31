package com.example.servey_service.exception;

import com.example.global.exception.ExceptionType;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SurveyExceptionType implements ExceptionType {

    NOT_FOUND_SURVEY(6001, "설문조사를 찾을 수 없습니다."),
    INVALID_SURVEY_DATA(6002, "유효하지 않은 설문 데이터입니다."),
    DUPLICATED_SURVEY_TITLE(6003, "중복된 설문 제목입니다.");

    private final int statusCode;
    private final String message;
}
