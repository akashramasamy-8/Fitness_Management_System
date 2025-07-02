package com.project.manage.Dto;


public class EnrollmentDTO {
    private Long enrollmentId;
    private String workoutPlanTitle;
    private String workoutPlanDescription;
    private String trainerName;
    private String progress;

    public EnrollmentDTO(Long enrollmentId, String workoutPlanTitle, String workoutPlanDescription, String trainerName, String progress) {
        this.enrollmentId = enrollmentId;
        this.workoutPlanTitle = workoutPlanTitle;
        this.workoutPlanDescription = workoutPlanDescription;
        this.trainerName = trainerName;
        this.progress = progress;
    }

    public Long getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(Long enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getWorkoutPlanTitle() {
        return workoutPlanTitle;
    }

    public void setWorkoutPlanTitle(String workoutPlanTitle) {
        this.workoutPlanTitle = workoutPlanTitle;
    }

    public String getWorkoutPlanDescription() {
        return workoutPlanDescription;
    }

    public void setWorkoutPlanDescription(String workoutPlanDescription) {
        this.workoutPlanDescription = workoutPlanDescription;
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }
}

