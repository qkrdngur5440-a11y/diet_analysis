package com.example.diet_analysis.model;

import java.time.LocalDate;

public class DailyStats {
    private LocalDate date;
    private int goalCalories;          // 일일 목표 칼로리
    private int consumedCalories;      // 섭취 칼로리
    private int burnedCalories;        // 소모 칼로리
    private int netCalories;           // 순 칼로리 (섭취 - 소모)
    private String feedbackMessage;    // 피드백 메시지

    public DailyStats() {}

    public DailyStats(LocalDate date, int goalCalories, int consumedCalories, int burnedCalories) {
        this.date = date;
        this.goalCalories = goalCalories;
        this.consumedCalories = consumedCalories;
        this.burnedCalories = burnedCalories;
        this.netCalories = consumedCalories - burnedCalories;
        this.feedbackMessage = generateFeedback();
    }

    private String generateFeedback() {
        if (netCalories > goalCalories) {
            return "칼로리 섭취가 목표를 초과했습니다. 운동량을 늘려보세요.";
        } else if (netCalories < goalCalories * 0.8) {
            return "칼로리 섭취가 충분하지 않습니다. 더 많이 섭취하세요.";
        }
        return "일일 칼로리 관리가 잘 됩니다!";
    }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public int getGoalCalories() { return goalCalories; }
    public void setGoalCalories(int goalCalories) { this.goalCalories = goalCalories; }

    public int getConsumedCalories() { return consumedCalories; }
    public void setConsumedCalories(int consumedCalories) { this.consumedCalories = consumedCalories; }

    public int getBurnedCalories() { return burnedCalories; }
    public void setBurnedCalories(int burnedCalories) { this.burnedCalories = burnedCalories; }

    public int getNetCalories() { return netCalories; }
    public void setNetCalories(int netCalories) { this.netCalories = netCalories; }

    public String getFeedbackMessage() { return feedbackMessage; }
    public void setFeedbackMessage(String feedbackMessage) { this.feedbackMessage = feedbackMessage; }
}
