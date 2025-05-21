package com.example.servey_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record QuestionRequest(
        @NotBlank(message = "질문 텍스트는 반드시 입력해야 합니다.")
        @Size(max = 200, message = "질문은 최대 200자까지 입력 가능합니다.")
        String questionText
) { }
