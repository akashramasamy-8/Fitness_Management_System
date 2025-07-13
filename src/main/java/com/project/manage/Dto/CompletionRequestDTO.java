package com.project.manage.Dto;

import java.util.List;

public class CompletionRequestDTO {
    private long planId;
    private int dayNumber;
    private boolean completed;
    private List<Long> exerciseIds;

    public List<Long> getExerciseIds() {
        return exerciseIds;
    }

    public void setExerciseIds(List<Long> exerciseIds) {
        this.exerciseIds = exerciseIds;
    }

    public CompletionRequestDTO(long planId, int dayNumber, boolean completed) {
        this.planId = planId;
        this.dayNumber = dayNumber;
        this.completed = completed;
    }

    public long getPlanId() {
        return planId;
    }

    public void setPlanId(long planId) {
        this.planId = planId;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
