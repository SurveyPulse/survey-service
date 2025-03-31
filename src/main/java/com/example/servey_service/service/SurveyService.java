package com.example.servey_service.service;

import com.example.global.exception.type.NotFoundException;
import com.example.servey_service.dto.QuestionRequest;
import com.example.servey_service.dto.SurveyRequest;
import com.example.servey_service.dto.SurveyResponse;
import com.example.servey_service.entity.Question;
import com.example.servey_service.entity.Survey;
import com.example.servey_service.entity.SurveyStatus;
import com.example.servey_service.exception.SurveyExceptionType;
import com.example.servey_service.repository.QuestionRepository;
import com.example.servey_service.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SurveyService {

    private final SurveyRepository surveyRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public SurveyResponse createSurvey(SurveyRequest request) {
        SurveyStatus status = request.status();

        Survey survey = Survey.builder()
                              .title(request.title())
                              .description(request.description())
                              .creatorUserId(request.creatorUserId())
                              .startTime(request.startTime())
                              .endTime(request.endTime())
                              .status(status)
                              .build();

        if (request.questions() != null) {
            for (QuestionRequest questionRequest : request.questions()) {
                Question question = Question.builder()
                                            .questionText(questionRequest.questionText())
                                            .build();
                question.addSurvey(survey);
                questionRepository.save(question);
            }
        }

        Survey saved = surveyRepository.save(survey);
        return SurveyResponse.from(saved);
    }

    @Transactional(readOnly = true)
    public SurveyResponse getSurveyById(Long surveyId) {
        Survey survey = surveyRepository.findById(surveyId)
                                        .orElseThrow(() -> new NotFoundException(SurveyExceptionType.NOT_FOUND_SURVEY));
        return SurveyResponse.from(survey);
    }

    @Transactional(readOnly = true)
    public Page<SurveyResponse> getAllSurveys(int page) {
        Pageable pageable = PageRequest.of(page, 20, Sort.by(Sort.Direction.DESC, "createdAt"));
        return surveyRepository.findAll(pageable)
                               .map(SurveyResponse::from);
    }

}
