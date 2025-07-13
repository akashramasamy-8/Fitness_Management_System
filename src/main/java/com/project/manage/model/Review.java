package com.project.manage.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long reviewId;
    @Column(nullable = false)
    private double rating;

    private String comment;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User client;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private WorkoutPlan workoutPlan;
}
