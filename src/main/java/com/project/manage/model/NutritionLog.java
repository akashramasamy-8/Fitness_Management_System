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
public class NutritionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long nutritionId;

    private String ingredient;

    private double calories;
    private double protein;
    private double fat;
    private double carbs;
    private LocalDate entryDate;

    @ManyToOne
    @JoinColumn(name="UserId",nullable = false)
    private User user;

}
