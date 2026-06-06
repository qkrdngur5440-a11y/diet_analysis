package com.example.diet_analysis.controller;

import com.example.diet_analysis.model.DailyStats;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;

@RestController
public class HomeController {

    private final ExerciseController exerciseController;

    public HomeController(ExerciseController exerciseController) {
        this.exerciseController = exerciseController;
    }

    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/index.html");
    }

    // 특정 날짜의 일일 통계 조회 (목표칼로리, 섭취, 소모, 순칼로리)
    @GetMapping("/daily-stats/{date}")
    public DailyStats getDailyStats(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);

        int goalCalories = 2000;        // 일일 목표 칼로리

        // 1. 섭취 칼로리 연동 (diet_input 마이크로서비스 http 호출)
        int consumedCalories = 0;
        RestTemplate restTemplate = new RestTemplate();
        try {
            String url = "http://localhost:8080/api/diets/" + date;
            Map<String, Object> response = restTemplate.getForObject(url, Map.class);
            if (response != null && response.containsKey("records")) {
                List<Map<String, Object>> records = (List<Map<String, Object>>) response.get("records");
                if (records != null) {
                    for (Map<String, Object> record : records) {
                        if (record != null && record.containsKey("analysisResult")) {
                            Map<String, Object> ar = (Map<String, Object>) record.get("analysisResult");
                            if (ar != null && ar.containsKey("totalCalories")) {
                                consumedCalories += ((Number) ar.get("totalCalories")).intValue();
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            consumedCalories = 0;
        }

        // 2. 소모 칼로리 연동 (로컬 ExerciseController 인메모리 DB에서 합산)
        int burnedCalories = 0;
        try {
            Integer totalBurned = exerciseController.getTotalBurnedCaloriesByDate(date).getBody();
            if (totalBurned != null) {
                burnedCalories = totalBurned;
            }
        } catch (Exception e) {
            burnedCalories = 0;
        }

        return new DailyStats(localDate, goalCalories, consumedCalories, burnedCalories);
    }
}
