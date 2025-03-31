package com.example.servey_service.entity;

import com.example.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "questions")
public class Question extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long questionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "survey_id", nullable = false)
    private Survey survey;

    @Column(nullable = false, length = 500)
    private String questionText;

    // 예: MULTIPLE_CHOICE, TEXT, SCALE 등 )  * enum도 고려
    @Column(nullable = false)
    private String questionType;

    @Builder
    public Question(String questionText, String questionType) {
        this.questionText = questionText;
        this.questionType = questionType;
    }

    public void addSurvey(Survey survey) {
        this.survey = survey;
        survey.getQuestions().add(this);
    }

}
