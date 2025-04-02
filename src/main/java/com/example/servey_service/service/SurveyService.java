package com.example.servey_service.service;

import com.example.global.exception.type.NotFoundException;
import com.example.servey_service.dto.request.QuestionRequest;
import com.example.servey_service.dto.response.QuestionWithSurveyDto;
import com.example.servey_service.dto.response.SurveyAddUrlResponse;
import com.example.servey_service.dto.request.SurveyRequest;
import com.example.servey_service.dto.response.SurveyResponse;
import com.example.servey_service.entity.Question;
import com.example.servey_service.entity.Survey;
import com.example.servey_service.entity.SurveyStatus;
import com.example.servey_service.exception.SurveyExceptionType;
import com.example.servey_service.repository.QuestionRepository;
import com.example.servey_service.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;

    @Value("${survey.base-url}")
    private String baseUrl;

    @Transactional
    public SurveyResponse createSurvey(SurveyRequest request) {

        Survey survey = Survey.builder()
                              .title(request.title())
                              .description(request.description())
                              .creatorUserId(request.creatorUserId())
                              .startTime(request.startTime())
                              .endTime(request.endTime())
                              .status(SurveyStatus.DRAFT)
                              .build();

        // 먼저 Survey 엔티티를 영속화
        Survey savedSurvey = surveyRepository.save(survey);

        if (request.questions() != null) {
            for (QuestionRequest questionRequest : request.questions()) {
                Question question = Question.builder()
                                            .questionText(questionRequest.questionText())
                                            .build();
                // 영속화된 Survey를 연결
                question.addSurvey(savedSurvey);
                questionRepository.save(question);
            }
        }

        return SurveyResponse.from(savedSurvey);
    }

    public SurveyResponse getSurveyById(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                                        .orElseThrow(() -> new NotFoundException(SurveyExceptionType.NOT_FOUND_SURVEY));
        return SurveyResponse.from(survey);
    }

    public Page<SurveyResponse> getAllSurveys(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
        return surveyRepository.findAll(pageable)
                               .map(SurveyResponse::from);
    }

    @Transactional
    public void deleteSurvey(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                                        .orElseThrow(() -> new NotFoundException(SurveyExceptionType.NOT_FOUND_SURVEY));
        surveyRepository.delete(survey);
    }

    @Transactional
    public SurveyResponse closeSurvey(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                                        .orElseThrow(() -> new NotFoundException(SurveyExceptionType.NOT_FOUND_SURVEY));
        survey.changeSurveyStatus(SurveyStatus.CLOSED);
        Survey updated = surveyRepository.save(survey);

        return SurveyResponse.from(updated);
    }

    @Transactional
    public SurveyAddUrlResponse deploySurvey(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                                        .orElseThrow(() -> new NotFoundException(SurveyExceptionType.NOT_FOUND_SURVEY));
        // 배포 시 상태를 OPEN으로 변경
        survey.changeSurveyStatus(SurveyStatus.OPEN);
        Survey deploy = surveyRepository.save(survey);

        String surveyUrl = baseUrl + "/" + deploy.getSurveyId();

        // Kafka 이벤트 발행 로직은 생략
        return SurveyAddUrlResponse.fromAddUrl(deploy, surveyUrl);
    }

    public List<QuestionWithSurveyDto> getQuestionWithSurveyDto(Long surveyId) {
        List<Question> questions = questionRepository.findBySurveyIdWithSurvey(surveyId);
        return questions.stream()
                        .map(QuestionWithSurveyDto::from)
                        .collect(Collectors.toList());
    }

}
