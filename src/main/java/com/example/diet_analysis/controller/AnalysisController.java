package com.example.diet_analysis.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.diet_analysis.model.AnalysisResult;
import com.example.diet_analysis.model.DietAnalysisResponse;
import com.example.diet_analysis.model.EstimatedFood;

import java.util.List;

@RestController
@RequestMapping("/api/analysis")
public class AnalysisController {

    @PostMapping(value = "/photo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<DietAnalysisResponse> analyzePhoto(@RequestPart("image") MultipartFile image) {
        // NOTE: replace this mock logic with real AI/model calls.
        List<EstimatedFood> foods = List.of(
                new EstimatedFood("김치찌개", 1, "그릇"),
                new EstimatedFood("쌀밥", 1, "공기"),
                new EstimatedFood("계란말이", 1, "접시")
        );
        AnalysisResult ar = new AnalysisResult(650, 80, 25, 20, "단백질 섭취가 부족합니다.");
        DietAnalysisResponse resp = new DietAnalysisResponse(foods, ar);
        return ResponseEntity.ok(resp);
    }
}
