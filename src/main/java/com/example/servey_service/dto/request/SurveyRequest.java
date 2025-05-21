package com.example.servey_service.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.List;

public record SurveyRequest(
        @NotBlank(message = "제목은 필수입니다.")
        @Size(max = 100, message = "제목은 최대 100자까지 입력 가능합니다.")
        String title,

        @NotBlank(message = "설명은 필수입니다.")
        @Size(max = 500, message = "설명은 최대 500자까지 입력 가능합니다.")
        String description,

        @NotNull(message = "생성자 ID는 필수입니다.")
        Long creatorUserId,

        @NotNull(message = "시작 시간은 필수입니다.")
        @FutureOrPresent(message = "시작 시간은 현재 또는 미래여야 합니다.")
        LocalDateTime startTime,

        @NotNull(message = "종료 시간은 필수입니다.")
        @Future(message = "종료 시간은 반드시 미래여야 합니다.")
        LocalDateTime endTime,

        @NotEmpty(message = "질문은 최소 하나 이상 필요합니다.")
        @Valid
        List<QuestionRequest> questions
) { }
