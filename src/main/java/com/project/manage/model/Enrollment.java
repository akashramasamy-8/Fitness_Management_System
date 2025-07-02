package com.project.manage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long enrollmentId;
    private LocalDateTime enrollmentDate;
    @Enumerated(EnumType.STRING)
    private Progress progressStatus;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User client;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private WorkoutPlan workoutPlan;


}
