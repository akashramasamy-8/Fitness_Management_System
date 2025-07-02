package com.project.manage.controller;

import com.project.manage.model.Exercise;
import com.project.manage.service.TrainerExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/trainer")
public class TrainerExerciseController {

    @Autowired
    private TrainerExerciseService trainerExerciseService;

    @PostMapping("/modules/{moduleId}/exercises")
    public ResponseEntity<String> addExercise(@PathVariable Long moduleId,
                                              @RequestBody Exercise exercise,
                                              Authentication authentication) {
        String trainerName = authentication.getName();
        trainerExerciseService.addExercise(trainerName, moduleId, exercise);
        return ResponseEntity.ok("Exercise added successfully");
    }

    @PutMapping("/exercises/{exerciseId}")
    public ResponseEntity<String> updateExercise(@PathVariable Long exerciseId,
                                                 @RequestBody Exercise exercise,
                                                 Authentication authentication) {
        String trainerName = authentication.getName();
        trainerExerciseService.updateExercise(trainerName, exerciseId, exercise);
        return ResponseEntity.ok("Exercise updated successfully");
    }

    @DeleteMapping("/exercises/{exerciseId}")
    public ResponseEntity<String> deleteExercise(@PathVariable Long exerciseId,
                                                 Authentication authentication) {
        String trainerName = authentication.getName();
        trainerExerciseService.deleteExercise(trainerName, exerciseId);
        return ResponseEntity.ok("Exercise deleted successfully");
    }
}
