package com.project.manage.service;

import com.project.manage.exception.ResourceNotFoundException;
import com.project.manage.exception.UnauthorizedAccessException;
import com.project.manage.model.Exercise;
import com.project.manage.model.WorkoutModule;
import com.project.manage.repository.ExerciseRepo;
import com.project.manage.repository.WorkoutModuleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TrainerExerciseService {
    @Autowired
    private ExerciseRepo exerciseRepo;

    @Autowired
    private WorkoutModuleRepo workoutModuleRepo;

    public void addExercise(String trainerName, Long moduleId, Exercise exercise) {
        WorkoutModule module = workoutModuleRepo.findById(moduleId)
                .orElseThrow(() -> new ResourceNotFoundException("Workout module not found"));

        if (!module.getWorkoutPlan().getTrainer().getName().equals(trainerName)) {
            throw new UnauthorizedAccessException("You do not own this module");
        }

        exercise.setWorkoutModule(module);
        exerciseRepo.save(exercise);
    }

    public void updateExercise(String trainerName, Long exerciseId, Exercise updatedExercise) {
        Exercise existing = exerciseRepo.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found"));

        if (!existing.getWorkoutModule().getWorkoutPlan().getTrainer().getName().equals(trainerName)) {
            throw new UnauthorizedAccessException("You do not own this exercise");
        }

        if (updatedExercise.getName() != null && !updatedExercise.getName().isBlank()) {
            existing.setName(updatedExercise.getName());
        }

        if (updatedExercise.getRepetitions() != null && !updatedExercise.getRepetitions().isBlank()) {
            existing.setRepetitions(updatedExercise.getRepetitions());
        }

        exerciseRepo.save(existing);
    }

    public void deleteExercise(String trainerName, Long exerciseId) {
        Exercise exercise = exerciseRepo.findById(exerciseId)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not found"));

        if (!exercise.getWorkoutModule().getWorkoutPlan().getTrainer().getName().equals(trainerName)) {
            throw new UnauthorizedAccessException("You do not own this exercise");
        }

        exerciseRepo.delete(exercise);
    }
}
