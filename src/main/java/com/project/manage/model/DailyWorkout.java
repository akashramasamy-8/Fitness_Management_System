package com.project.manage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DailyWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long dailyWorkoutId;

    private int dayNumber;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private WorkoutPlan workoutPlan;

    @ManyToMany
    @JoinTable(
            name = "daily_workout_exercise",
            joinColumns = @JoinColumn(name = "daily_workout_id"),
            inverseJoinColumns = @JoinColumn(name = "exercise_id")
    )
    private List<Exercise> exerciseList;
}
