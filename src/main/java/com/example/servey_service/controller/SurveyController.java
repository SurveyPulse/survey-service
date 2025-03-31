package com.example.servey_service.controller;

import com.example.servey_service.dto.SurveyRequest;
import com.example.servey_service.dto.SurveyResponse;
import com.example.servey_service.service.SurveyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/survey")
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

}
