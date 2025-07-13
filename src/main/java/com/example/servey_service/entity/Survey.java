package com.example.servey_service.entity;

import com.example.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(
        name = "surveys",
        indexes = {
                @Index(
                        name = "idx_surveys_start_end",
                        columnList = "startTime, endTime"
                )
        }
)
public class Survey extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long surveyId;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private Long creatorUserId;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SurveyStatus status;

    @OneToMany(mappedBy = "survey")
    private List<Question> questions = new ArrayList<>();

    @Builder
    public Survey(String title, String description, Long creatorUserId, LocalDateTime startTime, LocalDateTime endTime, SurveyStatus status) {
        this.title = title;
        this.description = description;
        this.creatorUserId = creatorUserId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public void changeSurveyStatus(SurveyStatus changeStatus) {
        this.status = changeStatus;
    }
}
