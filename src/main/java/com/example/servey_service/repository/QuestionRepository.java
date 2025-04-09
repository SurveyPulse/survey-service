package com.example.servey_service.repository;

import com.example.servey_service.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    @Query("select q from Question q join fetch q.survey s where s.surveyId = :surveyId")
    List<Question> findBySurveysIdWithSurvey(@Param("surveyId") Long surveyId);

    @Query("select q from Question q join fetch q.survey s where s.surveyId = :surveyId and q.questionId = :questionId")
    Optional<Question> findBySurveyIdAndQuestionId(@Param("surveyId") Long surveyId,
                                                   @Param("questionId") Long questionId);

}
