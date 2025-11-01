package com.project.manage.Dto;

public class NotificationMessageDTO
{
    private Long trinerId;
    private Long workoutId;
    private String message;
    private String trainerEmail;

    public Long getTrinerId() {
        return trinerId;
    }

    public void setTrinerId(Long trinerId) {
        this.trinerId = trinerId;
    }

    public Long getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(Long workoutId) {
        this.workoutId = workoutId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTrainerEmail() {
        return trainerEmail;
    }

    public void setTrainerEmail(String trainerEmail) {
        this.trainerEmail = trainerEmail;
    }
}
