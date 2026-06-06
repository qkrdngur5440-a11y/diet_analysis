package com.example.diet_analysis.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.diet_analysis.model.Exercise;
import com.example.diet_analysis.model.DailyStats;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors; 
 
@RestController   
@RequestMapping("/api/exercise")
public class ExerciseController {
    // NOTE: In production, replace this with a database (JPA/Hibernate)
    private static final List<Exercise> exerciseDatabase = new ArrayList<>();

    // 운동 기록 추가
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Exercise> addExercise(@RequestBody Exercise exercise) {
        exerciseDatabase.add(exercise);
        return ResponseEntity.ok(exercise);
    }

    // 특정 날짜의 운동 기록 조회
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Exercise>> getExercisesByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Exercise> exercises = exerciseDatabase.stream()
                .filter(e -> e.getDate().equals(localDate))
                .collect(Collectors.toList());
        return ResponseEntity.ok(exercises);
    }

    // 특정 날짜의 총 소모 칼로리 조회
    @GetMapping("/calories/date/{date}")
    public ResponseEntity<Integer> getTotalBurnedCaloriesByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        int totalBurned = exerciseDatabase.stream()
                .filter(e -> e.getDate().equals(localDate))
                .mapToInt(Exercise::getCaloriesBurned)
                .sum();
        return ResponseEntity.ok(totalBurned);
    }

    // 모든 운동 기록 조회
    @GetMapping
    public ResponseEntity<List<Exercise>> getAllExercises() {
        return ResponseEntity.ok(exerciseDatabase);
    }

    // 운동 기록 삭제 (운동 이름 + 날짜로 삭제)
    @DeleteMapping
    public ResponseEntity<String> deleteExercise(@RequestParam String exerciseName, @RequestParam String date) {
        LocalDate localDate = LocalDate.parse(date);
        boolean removed = exerciseDatabase.removeIf(e -> 
            e.getExerciseName().equals(exerciseName) && e.getDate().equals(localDate)
        );
        if (removed) {
            return ResponseEntity.ok("운동 기록이 삭제되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("해당 운동 기록을 찾을 수 없습니다.");
        }
    }
}
