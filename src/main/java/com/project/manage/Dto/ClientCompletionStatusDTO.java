package com.project.manage.Dto;

public class ClientCompletionStatusDTO {
    private String clientUsername;
    private long planId;
    private int dayNumber;
    private int totalExercises;
    private int completedExercises;
    private String status;

    public ClientCompletionStatusDTO(String clientUsername, long planId, int dayNumber, int totalExercises, int completedExercises, String status) {
        this.clientUsername = clientUsername;
        this.planId = planId;
        this.dayNumber = dayNumber;
        this.totalExercises = totalExercises;
        this.completedExercises = completedExercises;
        this.status = status;
    }

    public String getClientUsername() {
        return clientUsername;
    }

    public void setClientUsername(String clientUsername) {
        this.clientUsername = clientUsername;
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

    public int getTotalExercises() {
        return totalExercises;
    }

    public void setTotalExercises(int totalExercises) {
        this.totalExercises = totalExercises;
    }

    public int getCompletedExercises() {
        return completedExercises;
    }

    public void setCompletedExercises(int completedExercises) {
        this.completedExercises = completedExercises;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
