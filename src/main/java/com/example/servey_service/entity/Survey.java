package com.example.servey_service.entity;

import com.example.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "surveys")
public class Survey extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    // 사용자 서비스와의 JOIN 없이 단순히 생성자 ID를 저장합니다.
    @Column(nullable = false)
    private Long creatorUserId;

    @Column
    private LocalDateTime startTime;

    @Column
    private LocalDateTime endTime;

    // 설문의 진행 상태를 나타내는 필드 (예: 공개, 종료)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SurveyStatus status;

    @Builder
    public Survey(String title, String description, Long creatorUserId, LocalDateTime startTime, LocalDateTime endTime, SurveyStatus status) {
        this.title = title;
        this.description = description;
        this.creatorUserId = creatorUserId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }
}
