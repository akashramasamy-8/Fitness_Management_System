package com.project.manage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long notificationId;
    private String message;

    private boolean notified=false;
    private LocalDateTime createdAt=LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "workoutId")
    private WorkoutPlan workoutPlan;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User trainer;
}
