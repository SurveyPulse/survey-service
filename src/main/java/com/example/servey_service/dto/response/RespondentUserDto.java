package com.example.servey_service.dto.response;

public record RespondentUserDto(
        Long userId,
        String username,
        String userRole
) {
}
