package com.project.manage.Dto;

import java.util.List;

public class DailyWorkoutResponseDTO {
    private int dayNumber;
    private List<ExerciseDTO> exercises;

    public DailyWorkoutResponseDTO(int dayNumber, List<ExerciseDTO> exercises) {
        this.dayNumber = dayNumber;
        this.exercises = exercises;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public List<ExerciseDTO> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDTO> exercises) {
        this.exercises = exercises;
    }
}
