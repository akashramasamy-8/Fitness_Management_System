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
public class WorkoutModule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long moduleId;

    private String name;

    @ManyToOne
    @JoinColumn(name = "workoutId")
    private WorkoutPlan workoutPlan;

    @OneToMany(mappedBy = "workoutModule")
    private List<Exercise> exercise;
}
