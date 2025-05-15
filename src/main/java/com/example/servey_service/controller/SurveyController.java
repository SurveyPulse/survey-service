package com.example.servey_service.controller;

import com.example.servey_service.dto.response.QuestionWithSurveyDto;
import com.example.servey_service.dto.response.SurveyAddUrlResponse;
import com.example.servey_service.dto.request.SurveyRequest;
import com.example.servey_service.dto.response.SurveyResponse;
import com.example.servey_service.dto.response.SurveyWithoutQuestionResponse;
import com.example.servey_service.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @PostMapping
    public ResponseEntity<SurveyResponse> createSurvey(@RequestBody SurveyRequest request) {
        SurveyResponse response = surveyService.createSurvey(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{surveyId}")
    public ResponseEntity<SurveyResponse> getSurvey(@PathVariable Long surveyId) {
        return ResponseEntity.ok(surveyService.getSurveyById(surveyId));
    }

    @GetMapping
    public ResponseEntity<Page<SurveyWithoutQuestionResponse>> getAllSurveys(@RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(surveyService.getAllSurveys(page));
    }

    @DeleteMapping("/{surveyId}")
    public ResponseEntity<Void> deleteSurvey(@PathVariable Long surveyId) {
        surveyService.deleteSurvey(surveyId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{surveyId}/deploy")
    public ResponseEntity<SurveyAddUrlResponse> deploySurvey(@PathVariable Long surveyId) {
        return ResponseEntity.ok(surveyService.deploySurvey(surveyId));
    }

    @PostMapping("/{surveyId}/close")
    public ResponseEntity<Void> closeSurvey(@PathVariable Long surveyId) {
        surveyService.closeSurvey(surveyId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{surveyId}/questions")
    public ResponseEntity<List<QuestionWithSurveyDto>> getQuestionsWithSurvey(@PathVariable Long surveyId) {
        return ResponseEntity.ok(surveyService.getQuestionsWithSurveyDto(surveyId));
    }

    @GetMapping("/{surveyId}/{questionId}")
    public ResponseEntity<QuestionWithSurveyDto> getQuestionsWithSurvey(@PathVariable Long surveyId, @PathVariable Long questionId) {
        return ResponseEntity.ok(surveyService.getQuestionWithSurveyDto(surveyId, questionId));
    }

    @GetMapping("/search")
    public ResponseEntity<Page<SurveyWithoutQuestionResponse>> searchByTitle(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page) {
        return ResponseEntity.ok(surveyService.searchSurveysByTitle(title, page));
    }

    @GetMapping("/active")
    public ResponseEntity<Page<SurveyWithoutQuestionResponse>> getActiveSurveys(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "20") int size) {
        return ResponseEntity.ok(surveyService.getActiveSurveys(page, size, LocalDateTime.now()));
    }

}
