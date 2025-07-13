package com.project.manage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WorkoutCompletion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int completionId;

    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "enrollmentId")
    private Enrollment enrollment;

    @ManyToOne
    @JoinColumn(name = "dailyWorkoutId")
    private DailyWorkout dailyWorkout;

    @ManyToOne
    @JoinColumn(name = "exerciseId")
    private Exercise exercise;

    private boolean completed;
}

