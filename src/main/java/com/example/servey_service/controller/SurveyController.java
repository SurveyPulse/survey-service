package com.example.servey_service.controller;

import com.example.servey_service.dto.SurveyAddUrlResponse;
import com.example.servey_service.dto.SurveyRequest;
import com.example.servey_service.dto.SurveyResponse;
import com.example.servey_service.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<Page<SurveyResponse>> getAllSurveys(int page) {
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
    public ResponseEntity<SurveyResponse> closeSurvey(@PathVariable Long surveyId) {
        return ResponseEntity.ok(surveyService.closeSurvey(surveyId));
    }
}
