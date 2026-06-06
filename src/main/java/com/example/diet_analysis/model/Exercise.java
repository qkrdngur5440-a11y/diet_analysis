package com.example.diet_analysis.model;

import java.time.LocalDate;

public class Exercise {
    private String exerciseName;      // 운동 종류 (예: 걷기, 달리기, 수영 등)
    private int duration;             // 운동 시간 (분)
    private int caloriesBurned;       // 소모된 칼로리
    private LocalDate date;           // 운동 날짜
    private String intensity;         // 운동 강도 (낮음/중간/높음)

    public Exercise() {}

    public Exercise(String exerciseName, int duration, int caloriesBurned, LocalDate date, String intensity) {
        this.exerciseName = exerciseName;
        this.duration = duration;
        this.caloriesBurned = caloriesBurned;
        this.date = date;
        this.intensity = intensity;
    }

    public String getExerciseName() { return exerciseName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public int getCaloriesBurned() { return caloriesBurned; }
    public void setCaloriesBurned(int caloriesBurned) { this.caloriesBurned = caloriesBurned; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getIntensity() { return intensity; }
    public void setIntensity(String intensity) { this.intensity = intensity; }
}
