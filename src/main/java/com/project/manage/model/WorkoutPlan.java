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
public class WorkoutPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long workoutId;
    private String title;
    private String description;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User trainer;

    @OneToMany(mappedBy = "workoutPlan")
    private List<WorkoutModule> modules;
}
